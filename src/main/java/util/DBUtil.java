package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DBUtil{
    private static DataSource dataSource = new ComboPooledDataSource();
    /**
     * create by: sky
     * create time: 15:28 2020/9/14
     * 返回Connection
     * @Param:
     * @return java.sql.Connection
     */
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * create by: sky
     * create time: 16:43 2020/9/14
     * 返回DataSource
     * @Param:
     * @return javax.sql.DataSource
     */
    public  static DataSource getDataSource(){
        return dataSource;
    }

}
