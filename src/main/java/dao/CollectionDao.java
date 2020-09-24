package dao;

import pojo.Collection;
import pojo.Movie;
import vo.CollectionVo;

import java.util.List;

/**
 * @author 小强子大大
 * @date 2020年9月14日15:01:55
 */
public interface CollectionDao {
    /**
     * 插入一条数据
     * @param collection
     * @return 返回是否成功
     */
    int insert(Collection collection);

    /**
     * 删除一条数据
     * @param collectId
     * @return 返回是否成功
     */
    int delete(int collectId);

    /**
     * 更新一条数据
     * @param collection
     * @return 返回是否成功
     */
    int update(Collection collection);

    /**
     * 获取全部记录
     * @return 返回全部的收藏
     */
    List<Collection> getAllCollect();

    /**
     * 根据id查询记录
     * @param collectId
     * @return 返回一条记录
     */
    Collection getCollectById(int collectId);

    /**
     * 在收藏表中遍历收藏的电影
     * 根据用户输入的关键字返回电影Id的list
     * @param keyWord
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Collection> getMovieIdByKeyWord(String keyWord, int userId, int currentPage, int pageSize);

    /**
     * 根据前端传来的关键字返回收藏
     * @param keyWord
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<CollectionVo> getAllCollectionByKeyWord(String keyWord,int userId, int currentPage, int pageSize);

    /**
     * 根据要查询的页返回对应页的数据
     * @param currentPage
     * @return
     */
    List<Collection> getCollectionByPage(int currentPage);

    /**
     * 获取用户所有的收藏
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<CollectionVo> getAllCollection(int userId, int currentPage, int pageSize);

    /**
     * 根据collection的电影id获取电影的name和img_url
     * @param movieId
     * @return
     */
    Movie getMovieByMovieId(int movieId);

    /**
     * 根据前端传来的用户id获取对应的collection
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Collection> getAllCollectByUser(int userId, int currentPage, int pageSize);

    /**
     * 计算当前用户的收藏的总记录数
     * @return
     */
    Long calCollectionCount();

    /**
    Long getCollectionCountByUserId(int userId);

    /**
     * 实现分页
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Collection> getUserCollectionByPage(int currentPage, int pageSize);

    /**
     * 根据userId和movieId在播放页面移除收藏
     * @param userId
     * @param movieId
     * @return
     */
    int deleteInPlayer(int userId, int movieId);

    /**
     * 查询播放页面的收藏状态
     * @param userId
     * @param movieId
     * @return
     */
    int queryCollectionStatus(int userId, int movieId);
}
