package pojo;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 16:00
 * @version: 1.0
 * 修改Histories表的实体类
 */
public class Histories {
    /**
     * historyId:历史记录id,数据库表自增
     */
    private int history_id;
    /**
     * userId:用户id
     */
    private int user_id;
    /**
     * movieId:电影id
     */
    private int movie_id;
    /**
     * progress:电影观看时间
     */
    private int progress;

    /**
     * 不带参数的构造函数
     */
    public Histories() {
    }

    /**
     * 不带historyId的构造函数
     * @param userId
     * @param movieId
     * @param progress
     */
    public Histories(int userId, int movieId, int progress) {
        this.user_id = userId;
        this.movie_id = movieId;
        this.progress = progress;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Histories{" +
                "historyId=" + history_id +
                ", userId=" + user_id +
                ", movieId=" + movie_id +
                ", progress=" + progress +
                '}';
    }
}
