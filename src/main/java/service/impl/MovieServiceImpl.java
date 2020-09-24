package service.impl;

import dao.MovieDao;
import dao.impl.MovieDaoImpl;
import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import pojo.Movie;
import service.MovieService;

import java.util.List;

/**
 * @Author: acer
 * @Date: 2020/9/14 14:33
 * @Description:
 */
public class MovieServiceImpl implements MovieService {
    MovieDao movieDao = null;

    public MovieServiceImpl() {
        movieDao = BeanFactory.getInstance("MovieDao", MovieDaoImpl.class);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    @Override
    public List<Movie> getMoviesByType(String type) {
        return movieDao.getMoviesByType(type);
    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        return movieDao.getMoviesByName(name);
    }

    @Override
    public int deleteMovie(Integer movie_id) {
        return movieDao.deleteMovie(movie_id);
    }

    @Override
    public int updateMovie(Movie movie) {
        return movieDao.updateMovie(movie);
    }

    @Override
    public int insertMovie(Movie movie) {
        return movieDao.insertMovie(movie);
    }

    @Override
    public long getMoviesCount() {
        return movieDao.getMoviesCount();
    }

    @Override
    public List<Movie> getLimitMovies(Integer num) {
        return movieDao.getLimitMovies(num);
    }

    @Override
    public List<Movie> getLimitMoviesByType(String type, Integer num) {
        return movieDao.getLimitMoviesByType(type,num);
    }

    @Override
    public Movie getMovieById(Integer movie_id) {
        return movieDao.getMovieById(movie_id);
    }

    @Override
    public List<Movie> getPageMoviesByType(String type, Integer currentPage, Integer pageSize) {
        return movieDao.getPageMoviesByType(type,currentPage,pageSize);
    }

    @Override
    public List<Movie> getPageMovies(Integer currentPage, Integer pageSize) {
        return movieDao.getPageMovies(currentPage,pageSize);
    }
}
