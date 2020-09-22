package pojo;

/**
 * @Author: acer
 * @Date: 2020/9/14 14:17
 * @Description:
 */
public class Movie {
    /**
     * 电影id
     */
    private Integer movie_id;
    /**
     * 电影名
     */
    private String name;
    /**
     * 电影类型
     */
    private String type;
    /**
     * 参与的演员团队
     */
    private String actor;
    /**
     * 封面图片路径
     */
    private String image_url;
    /**
     * 轮播图片路径
     */
    private String banner_url;
    /**
     * 电影简介
     */
    private String introduction;
    /**
     * 电影资源播放路径
     */
    private String url;

    public Movie() {
    }

    public Movie(String name, String type, String actor, String image_url, String banner_url, String introduction, String url) {
        this.name = name;
        this.type = type;
        this.actor = actor;
        this.image_url = image_url;
        this.banner_url = banner_url;
        this.introduction = introduction;
        this.url = url;
    }

    public Movie(Integer movie_id, String name, String type, String actor, String image_url, String banner_url, String introduction, String url) {
        this.movie_id = movie_id;
        this.name = name;
        this.type = type;
        this.actor = actor;
        this.image_url = image_url;
        this.banner_url = banner_url;
        this.introduction = introduction;
        this.url = url;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", actor='" + actor + '\'' +
                ", image_url='" + image_url + '\'' +
                ", banner_url='" + banner_url + '\'' +
                ", introduction='" + introduction + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
