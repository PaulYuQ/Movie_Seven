import com.alibaba.fastjson.JSONObject;
import dao.MovieDao;
import dao.impl.MovieDaoImpl;
import dao.impl.UserDaoImpl;
import factory.BeanFactory;
import org.junit.Test;
import pojo.Movie;
import service.MovieService;
import service.impl.MovieServiceImpl;
import service.impl.UserServiceImpl;
import util.DBUtil;

import java.util.List;

/**
 * @Author Miss kun
 * @Date 2020/9/12 15:02
 * @Version 1.0
 */
public class TestJunit {
    MovieService movieService = null;
    MovieDao movieDao = null;

    public void init() {
        movieService = BeanFactory.getInstance("MovieService", MovieServiceImpl.class);
        movieDao = BeanFactory.getInstance("MovieDao", MovieDaoImpl.class);
    }

    //测试工厂类
    @Test
    public void testFactory() {
        UserServiceImpl userService = BeanFactory.getInstance("UserService", UserServiceImpl.class);
        UserDaoImpl userDao = BeanFactory.getInstance("UserDao", UserDaoImpl.class);
        System.out.println(userService);
        System.out.println(userDao);
    }

    //测试连接池是否成功
    @Test
    public void testC3p0() {
        System.out.println(DBUtil.getConnection());
        System.out.println(DBUtil.getDataSource());
    }

    @Test
    public void testMoviesModule() {
        init();
        //查询所有电影
//        List<Movie> allMovies = movieService.getAllMovies();
//        System.out.println(allMovies);
        //
//        System.out.println(movieService.getMoviesByType("纪录片"));
        //
//        System.out.println(movieService.getMoviesByName("安"));
        System.out.println(movieService.getPageMoviesByType("动作片",0,36));
//        System.out.println(movieService.deleteMovie(982));

//        System.out.println(movieService.updateMovie(new Movie(982,
//                "潜行天下第一季",
//                "纪录片",
//                "主演：未知 导演：周芳",
//                "https://kuyun.tv/upload/vod/20200825-1/0da879accd61955c1210a732fe311aa3.jpg",
//                "",
//                "简介：以太平洋为探索线索，围绕“物种起源地”加拉帕格斯、原始部落所罗门、“人间最后一片净土”新西兰、以及阳光肆意的美国南加洲、墨西哥下加州，深入探寻地理环境、人文风貌，与海狮、海豹、大白鲨零距离接触，感受不同国籍、不同种族的人们与海洋之间的联系，传播中国人对海洋的态度与理念酷云在线播放电影网站=酷云在线播",
//                "https://diaoshi.dehua-kuyun.com/20200824/13507_1e60dd98/index.m3u8")));


//        System.out.println(movieService.insertMovie(new Movie(1,
//                "潜行天下第二季",
//                "纪录片",
//                "主演：未知 导演：周芳",
//                "https://kuyun.tv/upload/vod/20200825-1/0da879accd61955c1210a732fe311aa3.jpg",
//                "",
//                "简介：以太平洋为探索线索，围绕“物种起源地”加拉帕格斯、原始部落所罗门、“人间最后一片净土”新西兰、以及阳光肆意的美国南加洲、墨西哥下加州，深入探寻地理环境、人文风貌，与海狮、海豹、大白鲨零距离接触，感受不同国籍、不同种族的人们与海洋之间的联系，传播中国人对海洋的态度与理念酷云在线播放电影网站=酷云在线播",
//                "https://diaoshi.dehua-kuyun.com/20200824/13507_1e60dd98/index.m3u8")));

//        System.out.println(movieService.getMoviesCount());
//        JSONObject object = new JSONObject();
//        object.put("a","dasdas");
//        System.out.println(object.get("a"));

//        System.out.println(movieService.getLimitMovies(6));
//        System.out.println(movieService.getLimitMoviesByType("纪录片",6));
//        System.out.println(movieService.getMovieById(2));
    }
}
