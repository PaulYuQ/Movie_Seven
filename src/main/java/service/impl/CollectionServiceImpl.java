package service.impl;

import dao.CollectionDao;
import dao.impl.CollectionDaoImpl;
import factory.BeanFactory;
import pojo.Collection;
import pojo.Movie;
import service.CollectionService;
import vo.CollectionVo;

import java.util.List;

/**
 * @author 小强子大大
 */
public class CollectionServiceImpl implements CollectionService {
    private CollectionDao collectionDao;

    public CollectionServiceImpl() {
        collectionDao = BeanFactory.getInstance("CollectionDao", CollectionDaoImpl.class);
    }

    /**
     * 插入一条数据
     *
     * @param collection
     * @return 返回是否成功
     */
    @Override
    public int insert(Collection collection) {
        return collectionDao.insert(collection);
    }

    /**
     * 删除一条数据
     *
     * @param collectId
     * @return 返回是否成功
     */
    @Override
    public int delete(int collectId) {
        return collectionDao.delete(collectId);
    }

    /**
     * 更新一条数据
     *
     * @param collection
     * @return 返回是否成功
     */
    @Override
    public int update(Collection collection) {
        return collectionDao.update(collection);
    }

    /**
     * 获取全部记录
     *
     * @return 返回全部的收藏
     */
    @Override
    public List<Collection> getAllCollect() {
        return collectionDao.getAllCollect();
    }

    /**
     * 根据id查询记录
     *
     * @param collectId
     * @return 返回一条记录
     */
    @Override
    public Collection getCollectById(int collectId) {
        return collectionDao.getCollectById(collectId);
    }

    /**
     * 在收藏表中遍历收藏的电影
     * 根据用户输入的关键字返回电影Id的list
     *
     * @param keyWord
     * @return 返回id的List
     */
    @Override
    public List<Collection> getMovieIdByKeyWord(String keyWord, int userId, int currentPage, int pageSize) {
        return collectionDao.getMovieIdByKeyWord(keyWord, userId, currentPage, pageSize);
    }

    /**
     * 根据前端传来的关键字返回收藏
     *
     * @param keyWord
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<CollectionVo> getAllCollectionByKeyWord(String keyWord, int userId, int currentPage, int pageSize) {
        return collectionDao.getAllCollectionByKeyWord(keyWord, userId, currentPage, pageSize);
    }

    /**
     * 根据要查询的页返回对应页的数据
     *
     * @param currentPage
     * @return
     */
    @Override
    public List<Collection> getCollectionByPage(int currentPage) {
        return collectionDao.getCollectionByPage(currentPage);
    }

    /**
     * 获取前端界面需要的收藏的信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<CollectionVo> getAllCollection(int userId, int currentPage, int pageSize) {
        return collectionDao.getAllCollection(userId, currentPage, pageSize);
    }

    /**
     * 根据collection的电影id获取电影的name和img_url
     *
     * @param movieId
     * @return
     */
    @Override
    public Movie getMovieByMovieId(int movieId) {
        return collectionDao.getMovieByMovieId(movieId);
    }

    /**
     * 根据前端传来的用户id获取对应的collection
     *
     * @param userId
     * @return
     */
    @Override
    public List<Collection> getAllCollectByUser(int userId, int currentPage, int pageSize) {
        return collectionDao.getAllCollectByUser(userId, currentPage, pageSize);
    }

    /**
     * 计算当前用户的收藏的总记录数
     *
     * @return
     */
    @Override
    public Long calCollectionCount() {
        return collectionDao.calCollectionCount();
    }


    /**
     * 实现分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Collection> getUserCollectionByPage(int currentPage, int pageSize) {
        return collectionDao.getUserCollectionByPage(currentPage, pageSize);
    }

    /**
     * 根据userId和movieId在播放页面移除收藏
     *
     * @param userId
     * @param movieId
     * @return
     */
    @Override
    public int deleteInPlayer(int userId, int movieId) {
        return collectionDao.deleteInPlayer(userId, movieId);
    }

    /**
     * 查询播放页面的收藏状态
     *
     * @param userId
     * @param movieId
     * @return
     */
    @Override
    public int queryCollectionStatus(int userId, int movieId) {
        return collectionDao.queryCollectionStatus(userId, movieId);
    }

    /**
     * 获取收藏数量
     *
     * @param userId
     * @return
     */
    @Override
    public long getCollectionCountByUserId(int userId) {
        return collectionDao.getCollectionCountByUserId(userId);
    }

    /**
     * 模糊查询收藏数量
     *
     * @param userId
     * @param keyWord
     * @return
     */
    @Override
    public long getCollectionCountByKeyWord(int userId, String keyWord) {
        return collectionDao.getCollectionCountByKeyWord(userId, keyWord);
    }
}
