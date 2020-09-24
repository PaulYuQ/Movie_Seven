package dao.impl;

import dao.CollectionDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Collection;
import pojo.Movie;
import pojo.User;
import util.DBUtil;
import vo.CollectionVo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 小强子大大
 */
public class CollectionDaoImpl implements CollectionDao {
    private QueryRunner queryRunner;

    public CollectionDaoImpl() {
        queryRunner = new QueryRunner(DBUtil.getDataSource());
    }

    /**
     * 插入一条数据
     *
     * @param collection
     * @return 返回是否成功
     */
    @Override
    public int insert(Collection collection) {
        String insert = "insert into collections(user_id,movie_id) values(?,?)";
        int result = 0;
        try {
            result = queryRunner.update(insert, new Object[]{collection.getUser_id(), collection.getMovie_id()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除一条数据
     *
     * @param collectId
     * @return 返回是否成功
     */
    @Override
    public int delete(int collectId) {
        String delete = "delete from collections where collection_id=?";
        int result = 0;
        try {
            result = queryRunner.update(delete, new Object[]{collectId});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新一条数据
     *
     * @param collection
     * @return 返回是否成功
     */
    @Override
    public int update(Collection collection) {
        String update = "update collections set user_id=?,movie_id=? where collection_id=?";
        int result = 0;
        try {
            result = queryRunner.update(update, new Object[]{collection.getUser_id(), collection.getMovie_id(), collection.getCollection_id()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取全部记录
     *
     * @return 返回全部的收藏
     */
    @Override
    public List<Collection> getAllCollect() {
        String getAllCollect = "select * from collections";
        List<Collection> list = null;
        try {
            list = queryRunner.query(getAllCollect, new BeanListHandler<>(Collection.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据id查询记录
     *
     * @param collectId
     * @return 返回一条记录
     */
    @Override
    public Collection getCollectById(int collectId) {
        String getCollectById = "select * from collections where collection_id=?";
        Collection collection = null;
        try {
            collection = queryRunner.query(getCollectById, new BeanHandler<>(Collection.class), collectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collection;
    }

    /**
     * 在收藏表中遍历收藏的电影
     * 根据用户输入的关键字返回电影Id的list
     *
     * @param keyWord
     * @return 返回Collect的List
     */
    @Override
    public List<Collection> getMovieIdByKeyWord(String keyWord, int userId, int currentPage, int pageSize) {
        //select * from collections where user_id=3 and movie_id in (select movie_id from movies where name like '%"+ keyWord +"%') limit 0,6;
        String getMovieIdByKeyWord = "select * from collections where user_id=? and movie_id in (select movie_id from movies where name like '%"+ keyWord +"%') order by date desc limit ?,?";
        List<Collection> list = null;
        try {
            list = queryRunner.query(getMovieIdByKeyWord, new BeanListHandler<>(Collection.class), new Object[]{userId, currentPage, pageSize});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CollectionVo> getAllCollectionByKeyWord(String keyWord, int userId, int currentPage, int pageSize) {
        List<CollectionVo> list = new ArrayList<>();
        List<Collection> movieIdByKeyWord = getMovieIdByKeyWord(keyWord,userId,currentPage,pageSize);
        for (Collection c : movieIdByKeyWord) {
            CollectionVo collectionVo = new CollectionVo();
            collectionVo.setCollection_id(c.getCollection_id());
            collectionVo.setUser_id(c.getUser_id());
            collectionVo.setMovie_id(c.getMovie_id());
            collectionVo.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getDate()));
            //根据collection的电影id获取电影的name和img_url
            Movie movie = getMovieByMovieId(c.getMovie_id());
            collectionVo.setName(movie.getName());
            collectionVo.setImage_url(movie.getImage_url());
            list.add(collectionVo);
        }
        return list;
    }

    /**
     * 根据要查询的页返回对应页的数据
     *
     * @param currentPage
     * @return
     */
    @Override
    public List<Collection> getCollectionByPage(int currentPage) {
        String getCollectionByPage = "select * from collections limit ?,?";
        List<Collection> list = null;
        try {
            list = queryRunner.query(getCollectionByPage, new BeanListHandler<>(Collection.class),new Object[]{(currentPage-1)*6,6});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取前端界面需要的收藏的信息
     *
     * @return
     */
    @Override
    public List<CollectionVo> getAllCollection(int userId, int currentPage, int pageSize) {
        List<CollectionVo> list = new ArrayList<>();
        List<Collection> collections = getAllCollectByUser(userId, currentPage, pageSize);
        for (Collection c : collections) {
            CollectionVo collectionVo = new CollectionVo();
            collectionVo.setCollection_id(c.getCollection_id());
            collectionVo.setUser_id(c.getUser_id());
            collectionVo.setMovie_id(c.getMovie_id());
            collectionVo.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(doDate(c.getDate())));
            //根据collection的电影id获取电影的name和img_url
            Movie movie = getMovieByMovieId(c.getMovie_id());
            collectionVo.setName(movie.getName());
            collectionVo.setImage_url(movie.getImage_url());
            list.add(collectionVo);
        }
        return list;
    }

    public Date doDate(Date date){
        long time = date.getTime()-2800000;
        System.out.println(time);
        Date afterDate = new Date(time);
        return afterDate;
    }


    /**
     * 根据collection的电影id获取电影的name和img_url
     *
     * @param movieId
     * @return
     */
    @Override
    public Movie getMovieByMovieId(int movieId) {
        String getMovieByMovieId = "select * from movies where movie_id=?";
        Movie movie = null;
        try {
            movie = queryRunner.query(getMovieByMovieId, new BeanHandler<>(Movie.class),movieId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    /**
     * 根据首页传来的用户id获取所有的收藏
     *
     * @return
     */
    @Override
    public List<Collection> getAllCollectByUser(int userId, int currentPage, int pageSize) {
        String getAllCollect = "select * from collections where user_id=? order by date desc limit ?,?";
        List<Collection> list = null;
        try {
            list = queryRunner.query(getAllCollect, new BeanListHandler<>(Collection.class), new Object[]{userId, currentPage, pageSize});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 计算当前用户的收藏的总记录数
     *
     * @return
     */
    @Override
    public Long calCollectionCount() {
        String calCollectionCount = "select count(*) from collections";
        Long count = 0L;
        try {
            count = queryRunner.query(calCollectionCount, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
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
        String getUserCollectionByPage="select * from collections limit ?,?";
        List<Collection> collections = null;
        try {
            collections=queryRunner.query(getUserCollectionByPage,new BeanListHandler<>(Collection.class),new Object[]{(currentPage-1)*pageSize,pageSize});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collections;
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
        String deleteInPlayer = "delete from collections where user_id=? and movie_id=?";
        int result = 0;
        try {
            queryRunner.update(deleteInPlayer, new Object[]{userId, movieId});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
        String queryCollectionStatus = "select * from collections where user_id=? and movie_id=?";
        int result = 0;
        try {
            if(queryRunner.query(queryCollectionStatus, new BeanHandler<>(Collection.class), new Object[]{userId, movieId}) != null) {
                result = 1;
            } else {
                result = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
