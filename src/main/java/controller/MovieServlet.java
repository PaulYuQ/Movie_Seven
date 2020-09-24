package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import factory.BeanFactory;
import pojo.Admin;
import pojo.Movie;
import service.MovieService;
import service.impl.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lao liu
 * @Date 2020/9/12 15:23
 * @Version 1.0
 */
@WebServlet(name = "MovieServlet", urlPatterns = {"/movie/*"})
public class MovieServlet extends HttpServlet {
    public MovieService movieService;

    public MovieServlet() {
        movieService = BeanFactory.getInstance("MovieService", MovieServiceImpl.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(request.getRequestURI());
        if (requestURI.contains("initAllData.do")) {
            initAllData(request, response);
        } else if (requestURI.contains("initAnyMovies.do")) {
            initAnyMovies(request, response);
        } else if (requestURI.contains("gotoIntroduction.do")) {
            gotoIntroduction(request, response);
        } else if (requestURI.contains("showIntroduction.do")) {
            showIntroduction(request, response);
        } else if (requestURI.contains("gotoPlayer.do")) {
            gotoPlayer(request, response);
        } else if (requestURI.contains("showPlayer.do")) {
            showPlayer(request, response);
        } else if (requestURI.contains("gotoSearch.do")) {
            gotoSearch(request, response);
        } else if (requestURI.contains("showSearch.do")) {
            showSearch(request, response);
        } else if (requestURI.contains("getMoviesCount.do")) {
            getMoviesCount(request, response);
        } else if (requestURI.contains("getRelevantMovies.do")){
            getRelevantMovies(request,response);
        }else if (requestURI.contains("getMovies.do")) {
            getMovies(request, response);
        }else if (requestURI.contains("getcount.do")) {
            getCount(request, response);
        }else if (requestURI.contains("getmoviebyid.do")) {
            getOneMovie(request, response);
        }else if (requestURI.contains("delmoviebyid.do")) {
            delMovie(request, response);
        }else if (requestURI.contains("updatemoviebyid.do")) {
            updateMovie(request, response);
        }else if (requestURI.contains("searchbyname.do")) {
            getByName(request, response);
        }else if (requestURI.contains("addmovie.do")) {
            addmovie(request, response);
        }
    }

    private void  addmovie(HttpServletRequest request, HttpServletResponse response) {
        String name=request.getParameter("name");
        String type=request.getParameter("type");
        String actor=request.getParameter("actor");
        String image_url=request.getParameter("image_url");
        String banner_url=request.getParameter("banner_url");
        String introduction=request.getParameter("introduction");
        String url=request.getParameter("url");
        Movie movie=new Movie(name,type,actor,image_url,banner_url,introduction,url);
        int i = movieService.insertMovie(movie);
        if(i==1){
            try {
                System.out.println("修改成功");
                response.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                System.out.println("修改失败");
                response.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void  getByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        List<Movie> movies = movieService.getMoviesByName(name);
        if(movies!=null) {
            JSONArray jsonArray=JSONArray.parseArray(JSON.toJSONString(movies));
            try {
                response.getWriter().print(jsonArray.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(jsonArray.toString());
        }

    }


    private void updateMovie(HttpServletRequest request, HttpServletResponse response) {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String type=request.getParameter("type");
        String actor=request.getParameter("actor");
        String image_url=request.getParameter("image_url");
        String banner_url=request.getParameter("banner_url");
        String introduction=request.getParameter("introduction");
        String url=request.getParameter("url");
        Movie movie=new Movie(Integer.parseInt(id),name,type,actor,image_url,banner_url,introduction,url);
        int i = movieService.updateMovie(movie);
        if(i==1){
            try {
                System.out.println("修改成功");
                response.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                System.out.println("修改失败");
                response.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void delMovie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        int i= movieService.deleteMovie(Integer.parseInt(id));
        if(i==1){
            response.getWriter().print("true");
        }else {
            response.getWriter().print("false");
        }

    }

    private void getOneMovie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        Movie movieById = movieService.getMovieById(Integer.parseInt(id));
        ArrayList<Movie> movies=new ArrayList<>();
        movies.add(movieById);
        if(movieById!=null) {
            String s = JSON.toJSONString(movies);
            response.getWriter().print(s);
        }

    }

    private void getCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long count=movieService.getMoviesCount();
        response.getWriter().print((int)count);
    }

    private void getMovies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page=request.getParameter("page");
        String pageAmount=request.getParameter("pageAmount");
        List<Movie> anyMovies = movieService.getPageMovies(Integer.parseInt(page),Integer.parseInt(pageAmount));
        if(anyMovies!=null) {
            JSONArray jsonArray=JSONArray.parseArray(JSON.toJSONString(anyMovies));
            response.getWriter().print(jsonArray.toString());
            System.out.println(jsonArray.toString());
        }
    }


    private void getMoviesCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        List<Movie> anyMovies = movieService.getMoviesByType(name);
        int size = anyMovies.size();
        response.getWriter().print(size);
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        List<Movie> movies = movieService.getMoviesByName(name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("movies", movies);
        jsonObject.put("name", name);
        jsonObject.put("movieSum", movies.size());
        response.getWriter().print(jsonObject.toJSONString());
    }

    private void gotoSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");

        response.sendRedirect("/search.jsp?name=" + URLEncoder.encode(name, "utf-8"));


    }

    private void showPlayer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String movie_id = request.getParameter("movie_id");
        Movie movie = null;
        if (movie_id != null && movie_id != "") {
            movie = movieService.getMovieById(Integer.parseInt(movie_id));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("movie", movie);
        response.getWriter().print(jsonObject.toJSONString());
    }

    private void gotoPlayer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String movie_id = request.getParameter("movie_id");
        response.sendRedirect("/player.jsp?movie_id=" + movie_id);
    }

    private void showIntroduction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String movie_id = request.getParameter("movie_id");
        Movie movie = null;
        if (movie_id != null && movie_id != "") {
            movie = movieService.getMovieById(Integer.parseInt(movie_id));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("movie", movie);
        response.getWriter().print(jsonObject.toJSONString());
    }

    private void gotoIntroduction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String movie_id = request.getParameter("movie_id");
        System.out.println("movie_id"+movie_id);
        response.sendRedirect("/introduction.jsp?movie_id=" + movie_id);
    }

    private void getRelevantMovies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //电影类型名
        String name = request.getParameter("name");
        List<Movie> relevantMovies = movieService.getLimitMoviesByType(name, 12);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("anyMovies", relevantMovies);
        response.getWriter().print(jsonObject.toJSONString());
    }

    private void initAnyMovies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //一页展示多少影片
        Integer pageSize = 36;
        //电影类型名
        String name = request.getParameter("name");
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        List<Movie> anyMovies = movieService.getPageMoviesByType(name, (currentPage - 1) * pageSize, pageSize);
        int movieSum = anyMovies.size();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("anyMovies", anyMovies);
        jsonObject.put("movieName", name);
        jsonObject.put("movieSum", movieSum);
        response.getWriter().print(jsonObject.toJSONString());
    }

    private void initAllData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long moviesCount = movieService.getMoviesCount();
        List<Movie> limitMovies = movieService.getLimitMovies(12);
        List<Movie> actionMovies = movieService.getLimitMoviesByType("动作片", 12);
        List<Movie> comedy = movieService.getLimitMoviesByType("喜剧片", 12);
        List<Movie> loveMovies = movieService.getLimitMoviesByType("爱情片", 12);
        List<Movie> storyMovies = movieService.getLimitMoviesByType("剧情片", 12);
        List<Movie> horrorMovies = movieService.getLimitMoviesByType("恐怖片", 12);
        List<Movie> scienceMovies = movieService.getLimitMoviesByType("科幻片", 12);
        List<Movie> documentaryMovies = movieService.getLimitMoviesByType("纪录片", 12);
        if (moviesCount > 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("movieSum", moviesCount);
            jsonObject.put("limitMovies", limitMovies);
            jsonObject.put("actionMovies", actionMovies);
            jsonObject.put("comedy", comedy);
            jsonObject.put("loveMovies", loveMovies);
            jsonObject.put("storyMovies", storyMovies);
            jsonObject.put("horrorMovies", horrorMovies);
            jsonObject.put("scienceMovies", scienceMovies);
            jsonObject.put("documentaryMovies", documentaryMovies);
            response.getWriter().print(jsonObject.toJSONString());
            System.out.println("JsonObject:" + jsonObject);
        } else {
            System.out.println("电影数量为0");
        }
    }
}
