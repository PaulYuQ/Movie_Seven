package service;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/15 9:42
 * @version: 1.0
 */
public interface ShowHistoryService {
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
     * create time: 8:53 2020/9/15
     * 查询结果分页展示
     * @Param: id 用户id
     * @Param: page 当前页数
     * @Param: row  一页的行数
     * @return java.util.List<java.lang.Object>
     */
    public List<Object> historyList(int id, int page, int row);
}
