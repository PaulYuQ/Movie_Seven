/**
 * @author 小秋秋
 * @time 2020-09-11
 */

interface IPagination {
    options: IPaginationOptions
    element: HTMLElement | null
    lis: HTMLLIElement[]
    home: HTMLLIElement | null
    last: HTMLLIElement | null
    prev: HTMLLIElement | null
    next: HTMLLIElement | null
    input: HTMLInputElement | null
    pageNum: number
    init: (options: IPaginationOptions) => void
    render: () => void
    getBetween: () => IBetween
    generateArray: (start: number, end: number) => number[]
    createElement: (tag: string, classList?: string | string[]) => HTMLElement
    handleClick: (index: number) => void
    validate: (options: IPaginationOptions) => boolean | undefined
    setOptions: (options: IPaginationOptions) => void
}

interface IBetween {
    min: number
    max: number
}

type Type = 1 | 2

interface IPaginationOptions {
    element: string | ''
    type: Type
    pageIndex: number
    pageSize: number
    pageCount: number
    total: number
    prevText: string | ''
    nextText: string | ''
    jumper: boolean
    singlePageHide: boolean
    disabled: boolean
    currentChange: (index: number) => void
    [key: string]: any
}

class Pagination implements IPagination {
    
    options: IPaginationOptions = {
        // 容器
        element: '',

        // 样式类型
        type: 1,

        // 当前页
        pageIndex: 1,

        // 每页显示数量
        pageSize: 0,

        // 按钮数量
        pageCount: 9,

        // 总条数
        total: 0,

        // 上一页文字
        prevText: '',

        // 下一页文字
        nextText: '',

        // 输入框跳转
        jumper: false,

        // 单页隐藏
        singlePageHide: true,

        // 是否禁用
        disabled: false,

        /**
         * @description 按钮事件回调 
         * @param index [number] 当前页码
         */
        currentChange: (index: number) => {},
    };

    element = null;

    lis = [];

    home = null;

    last = null;

    prev = null;

    next = null;

    input = null;

    pageNum = 0;

    constructor(options: IPaginationOptions) {
        if (this.validate(options)) {
            this.init(options);
        }
    }

    init(options: IPaginationOptions): void {
        this.setOptions(options);
        this.render();
    }

    render(this: IPagination): void {
        let 
        _this = this, 
        li,
        active;
        // 总页数
        this.pageNum = Math.ceil(this.options.total / this.options.pageSize);
        // 清空元素
        (this as any).element.innerHTML = '';
        // 单页隐藏
        if (this.pageNum === 1 && this.options.singlePageHide) return;
        // 最大页码
        if (this.options.pageIndex > this.pageNum) this.options.pageIndex = this.pageNum;
        // 最小页码
        if (this.options.pageIndex <= 0) this.options.pageIndex = 1;
        // 主体容器
        let container = this.createElement('div', '_page_container');
        // 页码容器
        let ul = this.createElement('ul', ['_pages', `_pages_${this.options.type}`]);
        // 禁用上一页
        let prev_disabled = this.options.pageIndex <= 1 ? ['_disabled_c'] : [];
        // 手势禁止
        if (this.options.pageIndex <= 1 && this.options.disabled) prev_disabled.push('_disabled');
        // 首页
        if (this.options.type <= 1) {
            this.home = this.createElement('li', ['_home', ...prev_disabled]) as HTMLLIElement;
            this.home.innerText = '首页';
            this.home.addEventListener('click', () => {
                if (this.options.pageIndex > 1) {
                    this.handleClick(1);
                }
            });
            ul.appendChild(this.home);
        }
        // 上一页
        this.prev = this.createElement('li', ['_prev_', ...prev_disabled, ...(this.options.prevText ? ['_prev'] : ['_iconfont', 'iconzuo'])]) as HTMLLIElement;
        // 上一页文字
        this.prev.innerText = this.options.prevText || '';
        // 上一页事件
        this.prev.addEventListener('click', () => {
            if (this.options.pageIndex - 1 > 0) {
                this.handleClick(this.options.pageIndex - 1);
            }
        });
        ul.appendChild(this.prev);
        // 区间值
        let between = this.getBetween();
        for(let i = 1; i <= this.pageNum; i++) {
            if (i >= between.min && i <= between.max) {
                active = i === this.options.pageIndex ? [`_active_${this.options.type}`] : [];
                // 手势禁止
                if (i === this.options.pageIndex && this.options.disabled) active.push('_disabled');
                li = this.createElement('li', [`_pages_li_${this.options.type}`, ...active]) as HTMLLIElement;
                li.innerText = i.toString();
                li.setAttribute('data-index', i.toString());
                li.addEventListener('click', function(this: HTMLElement){
                    if ((this as any).dataset.index != _this.options.pageIndex) {
                        _this.handleClick(Number(this.dataset.index));
                    }
                });
                this.lis?.push(li);
                ul.appendChild(li);
            }
        }
        // 禁用下一页
        let next_disabled = this.options.pageIndex >= this.pageNum ? ['_disabled_c'] : [];
        // 手势禁止
        if (this.options.pageIndex >= this.pageNum && this.options.disabled) next_disabled.push('_disabled');
        // 下一页
        this.next = this.createElement('li', ['_next_', ...next_disabled, ...(this.options.nextText ? ['_next'] : ['_iconfont', 'iconGroup-'])]) as HTMLLIElement;
        // 下一页文字
        this.next.innerText = this.options.nextText || '';
        // 下一页事件
        this.next.addEventListener('click', () => {
            if (this.options.pageIndex < this.pageNum) {
                this.handleClick(this.options.pageIndex + 1);
            }
        });
        ul.appendChild(this.next);
        // 尾页
        if (this.options.type <= 1) {
            this.last = this.createElement('li', ['_last', ...next_disabled]) as HTMLLIElement;
            this.last.innerText = '尾页';
            this.last.addEventListener('click', () => {
                if (this.options.pageIndex < this.pageNum) {
                    this.handleClick(this.pageNum);
                }
            });
            ul.appendChild(this.last);
        }
        container.appendChild(ul);
        // 输入框跳转
        if (this.options.jumper) {
            // 容器
            let jumper = this.createElement('div', '_jumper');
            // 总页码
            let count = this.createElement('span', '_count');
            count.innerText = `共 ${this.pageNum} 页`;
            jumper.appendChild(count);
            let text_1 = this.createElement('span');
            text_1.innerText = '前往';
            jumper.appendChild(text_1);
            let value = 0;
            // 输入框
            this.input = this.createElement('input', '_jumper_input') as HTMLInputElement;
            this.input.type = 'number';
            this.input.value = this.options.pageIndex.toString();
            this.input.setAttribute('min', '1');
            this.input.setAttribute('max', this.pageNum.toString());
            this.input.addEventListener('blur', function() {
                value = ~~this.value;
                if (value < 1)  value = 1;
                if (value > _this.pageNum) value = _this.pageNum;
                // @ts-ignore
                this.value = value;
                if (value !== _this.options.pageIndex) _this.handleClick(value);
            });
            jumper.appendChild(this.input);
            let text_2 = this.createElement('span');
            text_2.innerText = '页';
            jumper.appendChild(text_2);
            container.appendChild(jumper);
        }
        
        // 保存元素
        (this as any).element.appendChild(container);
    }

    handleClick(index: number): void {
        this.options.pageIndex = index;
        let mode: string;
        let around = ['home', 'last', 'prev', 'next'];
        around.forEach( (param: string) => {
            if (param === 'home' || param === 'prev') {
                mode = index <= 1 ? 'add' : 'remove';
            }
            if (param === 'last' || param === 'next') {
                mode = index >= this.pageNum ? 'add' : 'remove';
            }
            if ((this as any)[param]) {
                (this as any)[param].classList[mode]('_disabled_c');
                this.options.disabled && (this as any)[param].classList[mode]('_disabled');
            }
        })
        // 区间值
        let between = this.getBetween();
        let betweenList = this.generateArray(between.min, between.max);
        // 排它
        for(let i = 0; i < (this as any).lis.length; i++) {
            mode = betweenList[i] === index ? 'add' : 'remove';
            (this as any).lis[i].classList[mode](`_active_${this.options.type}`);
            (this as any).lis[i].setAttribute('data-index', betweenList[i]);
            (this as any).lis[i].innerText = betweenList[i].toString();
            this.options.disabled && (this as any).lis[i].classList[mode]('_disabled');
        }
        // @ts-ignore 修改input值
        if (this.options.jumper) this.input.value = index;
        // 回调
        typeof this.options.currentChange === 'function' && this.options.currentChange(index);
    }

    getBetween(): IBetween {
        // 最小下标
        let min = this.options.pageIndex - Math.floor(this.options.pageCount / 2);
        // 最小下标最大值
        if (min > this.pageNum - this.options.pageCount) {
            min = this.pageNum - this.options.pageCount  + 1;
        }
        // 最小值
        if (min <= 1) min = 1;
        // 最大下标
        let max = this.options.pageIndex + Math.floor(this.options.pageCount / 2);
        // 最大下标最小值
        if (max < this.options.pageCount) max = this.options.pageCount;
        // 最大值
        if (max > this.pageNum) max = this.pageNum;
        return {min, max};
    }

    generateArray(start: number, end: number) {
        return (Array as any).from((new Array(end + 1) as any).keys()).slice(start);
    }

    createElement(tag: string, classList?: string | string[]): HTMLElement {
        let dom = document.createElement(tag);
        if (classList) {
            if (classList instanceof Array) {
                classList.forEach( v => {
                    dom.classList.add(v);
                });
            } else {
                dom.classList.add(classList);
            }
        }
        return dom;
    }

    validate(options: IPaginationOptions): boolean | undefined {

        if (!options) throw new Error('options of null');

        if (typeof options !== 'object') throw new Error('options not an object');

        if (!document.querySelector(options.element)) throw new Error('element of null');

        try {
            ['type', 'pageIndex', 'pageSize', 'pageCount', 'total'].forEach( v => {
                if (options[v]) {
                    if (isNaN(options[v])) throw new Error(`${v} not an number`);
                    if (v === 'pageCount' && options[v] % 2 === 0) throw new Error(`${v} not an odd number`);
                }
            })
        } catch(error) {
            throw new Error(error);
        }

        return true;
    }

    setOptions(options: IPaginationOptions): void {
        // 容器
        this.element = document.querySelector(options.element) as never;
        for (let name in options) {
            if (options[name] !== void 0) {
                this.options[name] = isNaN(options[name]) ? options[name] : +options[name];
            }
        }
    }
    
}