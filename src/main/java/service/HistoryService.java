package service;

import pojo.Histories;
import pojo.ShowHistory;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 14:31
 * @version: 1.0
 */
public interface HistoryService {

    /**
     * create by: sky
     * create time: 8:52 2020/9/15
     * 查询结果行数
     * @Param: id 用户id
     * @return long
     */
    public long historyNumber(int id);


    /**
     * create by: sky
     * create time: 15:45 2020/9/16
     * 通过用户id进行删除操作
     * @Param: id
     * @return int
     */
    public int historyDelete(int id);

    /**
     * create by: sky
     * create time: 8:53 2020/9/15
     * 查询结果分页展示
     * @Param: id 用户id
     * @Param: page 当前页数
     * @Param: row  一页的行数
     * @return java.util.List<java.lang.Object>
     */
    public List<ShowHistory> historyList(int id, int page, int row);




    /**
     * create by: sky
     * create time: 20:19 2020/9/14
     * 表的增加
     * @Param: o 实体类对象
     * @return int
     */
    public int movieAdd(Histories histories);


    /**
     * create by: sky
     * create time: 20:21 2020/9/14
     * 表的删除
     * @Param: id 删除时根据历史记录id删除
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
    public int movieUpdate(Histories histories);


    /**
     * create by: sky
     * create time: 20:28 2020/9/14
     * 通过id进行单个查询
     * @Param: i
     * @return java.lang.Object
     */
    public Histories movieFindById(int i);


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
    public List<Histories> movieList(int page, int row);
}
