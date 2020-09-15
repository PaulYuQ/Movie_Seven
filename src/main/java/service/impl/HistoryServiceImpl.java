package service.impl;

import dao.MovieDao;
import factory.BeanFactory;
import pojo.ShowHistory;
import service.MovieService;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 14:24
 * @version: 1.0
 */
public class HistoryServiceImpl implements MovieService {
    MovieDao movieDao;
    public HistoryServiceImpl() {
        movieDao= BeanFactory.getInstance("HistoryDao",MovieDao.class);
    }

    @Override
    public int movieAdd(Object o) {
        return movieDao.movieAdd(o);
    }

    @Override
    public int movieDelete(int id) {
        return movieDao.movieDelete(id);
    }

    @Override
    public int movieUpdate(Object o) {
        return movieDao.movieUpdate(o);
    }

    @Override
    public Object movieFindById(int i) {
        return movieDao.movieFindById(i);
    }

    @Override
    public long movieNumber() {
        return movieDao.movieNumber();
    }

    @Override
    public List<Object> movieList(int page, int row) {
        return movieDao.movieList(page, row);
    }

}
