package service;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 14:31
 * @version: 1.0
 */
public interface MovieService {
    /**
     * create by: sky
     * create time: 20:19 2020/9/14
     * 表的增加
     * @Param: o 实体类对象
     * @return int
     */
    public int movieAdd(Object o);


    /**
     * create by: sky
     * create time: 20:21 2020/9/14
     * 表的删除
     * @Param: id 删除时根据id删除
     * @return int
     */
    public int movieDelete(int id);


    /**
     * create by: sky
     * create time: 20:23 2020/9/14
     *表的修改
     * @Param: o 实体类对象
     * @return int
     */
    public int movieUpdate(Object o);


    /**
     * create by: sky
     * create time: 20:28 2020/9/14
     * 通过id进行单个查询
     * @Param: i
     * @return java.lang.Object
     */
    public Object movieFindById(int i);


    /**
     * create by: sky
     * create time: 20:30 2020/9/14
     * 查询表格行数
     * @Param:
     * @return long
     */
    public long movieNumber();


    /**
     * create by: sky
     * create time: 20:33 2020/9/14
     * 实现页面分页
     * @Param: page  当前页数
     * @Param: row   一页展示的行数
     * @return java.util.List<java.lang.Object>
     */
    public List<Object> movieList(int page, int row);
}
