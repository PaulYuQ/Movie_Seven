package vo;


import java.util.Date;

/**
 * @author 小强子大大
 */
public class CollectionVo {
    private Integer collection_id;
    private Integer user_id;
    private Integer movie_id;
    private String date;
    private String name;
    private String image_url;

    public CollectionVo() {
    }

    public CollectionVo(Integer collection_id, Integer user_id, Integer movie_id, String date, String name, String image_url) {
        this.collection_id = collection_id;
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.date = date;
        this.name = name;
        this.image_url = image_url;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "CollectionVo{" +
                "collection_id=" + collection_id +
                ", user_id=" + user_id +
                ", movie_id=" + movie_id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
