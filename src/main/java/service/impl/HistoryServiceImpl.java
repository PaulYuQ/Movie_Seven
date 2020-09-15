package service.impl;

import dao.HistoryDao;
import factory.BeanFactory;
import pojo.Histories;
import pojo.ShowHistory;
import service.HistoryService;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 14:24
 * @version: 1.0
 */
public class HistoryServiceImpl implements HistoryService {
    HistoryDao movieDao;
    public HistoryServiceImpl() {
        movieDao= BeanFactory.getInstance("HistoryDao", HistoryDao.class);
    }

    @Override
    public long historyNumber(int id) {
        return movieDao.historyNumber(id);
    }

    @Override
    public List<ShowHistory> historyList(int id, int page, int row) {
        return movieDao.historyList(id,page,row);
    }
    @Override
    public int movieAdd(Histories histories) {
        return movieDao.movieAdd(histories);
    }

    @Override
    public int movieDelete(int id) {
        return movieDao.movieDelete(id);
    }

    @Override
    public int movieUpdate(Histories histories) {
        return movieDao.movieUpdate(histories);
    }

    @Override
    public Histories movieFindById(int i) {
        return movieDao.movieFindById(i);
    }

    @Override
    public long movieNumber() {
        return movieDao.movieNumber();
    }

    @Override
    public List<Histories> movieList(int page, int row) {
        return movieDao.movieList(page, row);
    }

}
