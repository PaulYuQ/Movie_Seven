package dao.impl;

import dao.MovieDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Movie;
import pojo.User;
import service.MovieService;
import util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: acer
 * @Date: 2020/9/14 14:24
 * @Description: 电影Dao层实现类
 */
public class MovieDaoImpl implements MovieDao {
    private QueryRunner qr;

    public MovieDaoImpl() {
        qr = new QueryRunner(DBUtil.getDataSource());
    }

    @Override
    public List<Movie> getAllMovies() {
        String sql = "select * from movies";
        List<Movie> movies = null;

        try {
            movies = qr.query(sql, new BeanListHandler<Movie>(Movie.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByType(String type) {
        String sql = "select * from movies where type=?";
        List<Movie> movies = null;

        try {
            movies = qr.query(sql, new BeanListHandler<Movie>(Movie.class), type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        String sql = "select * from movies where name like ?";
        List<Movie> movies = null;
        try {
            movies = qr.query(sql, new BeanListHandler<Movie>(Movie.class), "%" + name + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public int deleteMovie(Integer movie_id) {
        String sql = "delete from movies where movie_id=?";
        try {
            return qr.update(sql, new Object[]{movie_id});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateMovie(Movie movie) {
        String sql="update movies set name=? ,type=?,actor=?,image_url=?,banner_url=?,introduction=?,url=? where movie_id=?";
        try {
            return qr.update(sql, new Object[]{movie.getName(),movie.getType(),movie.getActor(),movie.getImage_url(),
            movie.getBanner_url(),movie.getIntroduction(),movie.getUrl(),movie.getMovie_id()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertMovie(Movie movie) {
        try {
            String sql = "insert into movies(name,type,actor,image_url,banner_url,introduction,url) values(?,?,?,?,?,?,?)";
            return qr.update(sql,new Object[]{movie.getName(),movie.getType(),movie.getActor(),movie.getImage_url(),
                    movie.getBanner_url(),movie.getIntroduction(),movie.getUrl()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Long getMoviesCount() {
        try {
            String sql = "select count(1) from movies";
            return (Long) qr.query(sql,new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public List<Movie> getLimitMovies(Integer num) {
        String sql = "select * from movies limit ?";
        List<Movie> movies = null;
        try {
            movies = qr.query(sql, new BeanListHandler<Movie>(Movie.class),num);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<Movie> getLimitMoviesByType(String type, Integer num) {
        String sql = "select * from movies where type=? limit ?";
        List<Movie> movies = null;

        try {
            movies = qr.query(sql, new BeanListHandler<Movie>(Movie.class), type,num);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public Movie getMovieById(Integer movie_id) {
        String sql = "select * from movies where movie_id=?";
        Movie movie = null;
        try {
            movie = qr.query(sql, new BeanHandler<Movie>(Movie.class), movie_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public List<Movie> getPageMoviesByType(String type, Integer currentPage, Integer pageSize) {
        String sql = "select * from movies where type=? limit ?,?";
        List<Movie> movies = null;

        try {
            movies = qr.query(sql, new BeanListHandler<Movie>(Movie.class), type,currentPage,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }


}
