package pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 小强子大大
 * @2020年9月14日10:57:46
 */
public class Collection {
    private Integer collection_id;
    private Integer user_id;
    private Integer movie_id;
    private Date date;

    public Collection() {
        this.date = new Date();
    }

    public Collection(Integer collection_id, Integer user_id, Integer movie_id) {
        this.collection_id = collection_id;
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.date = new Date();
    }

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collection_id=" + collection_id +
                ", user_id=" + user_id +
                ", movie_id=" + movie_id +
                ", date=" + date +
                '}';
    }
}


