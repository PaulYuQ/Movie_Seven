package service.impl;

import dao.ShowHistoryDao;
import factory.BeanFactory;
import service.ShowHistoryService;

import java.util.List;

/**
 * @author ：sky
 * @date ：Created in 2020/9/15 9:44
 * @version: 1.0
 */
public class ShowHistoryServiceImpl implements ShowHistoryService {
    ShowHistoryDao showHistoryDao;

    public ShowHistoryServiceImpl() {
        showHistoryDao= BeanFactory.getInstance("ShowHistoryDao",ShowHistoryDao.class);
    }

    @Override
    public long historyNumber(int id) {
        return showHistoryDao.historyNumber(id);
    }

    @Override
    public List<Object> historyList(int id, int page, int row) {
        return showHistoryDao.historyList(id,page,row);
    }
}
