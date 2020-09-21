package dao;

import pojo.Movie;

import java.util.List;

/**
 * @Author: acer
 * @Date: 2020/9/14 14:24
 * @Description:    电影Dao层
 */
public interface MovieDao {
    /**
     * 获取所有电影的信息
     * @return 返回一个电影集合
     */
    List<Movie> getAllMovies();

    /**
     * 根据电影类型获取对应的电影集合
     * @param type 电影类型
     * @return
     */
    List<Movie> getMoviesByType(String type);

    /**
     * 根据电影名模糊查询
     * @param name 电影名
     * @return
     */
    List<Movie> getMoviesByName(String name);

    /**
     * 根据电影id删除电影
     * @param movie_id   电影id
     * @return   删除成功返回1
     */
    int deleteMovie(Integer movie_id);

    /**
     * 修改电影信息
     * @param movie 传入修改后的电影信息
     * @return 成功返回1
     */
    int updateMovie(Movie movie);

    /**
     * 增加电影
     * @param movie 传入需要增加的电影信息
     * @return  成功返回1
     */
    int insertMovie(Movie movie);

    /**
     *  获取电影的数量
     * @return      返回电影数量
     */
    Long getMoviesCount();

    /**
     * 获取指定数量的电影集合
     * @param num  需要获取电影的数量
     * @return  返回电影集合
     */
    List<Movie> getLimitMovies(Integer num);

    /**
     *  获取指定类型和指定数量的电影集合
     * @param type   电影类型
     * @param num   需要获取的数量
     * @return      返回电影集合
     */
    List<Movie> getLimitMoviesByType(String type,Integer num);

    /**
     *  通过电影id获取电影信息
     * @param movie_id  查询的电影id
     * @return  返回查询的电影
     */
    Movie getMovieById(Integer movie_id);


    /**
     * 返回指定类型的一页电影信息
     * @param type  电影类型
     * @param currentPage 当前页号
     * @param pageSize 一页的电影数量
     * @return
     */
    List<Movie> getPageMoviesByType(String type,Integer currentPage,Integer pageSize);
}
