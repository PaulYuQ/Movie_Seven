package pojo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author Miss kun
 * @Date 2020/9/13 18:14
 * @Version 1.0
 * 注：comment(评论的)的pojo类
 */
public class Comment {

    /**
     * comment---comment_id  字段(评论id)
     */
    private Integer comment_id;

    /**
     * movie---movie_id  字段(电影id)
     */
    private Integer movie_id;

    /**
     * comment---parent_id  字段(评论的父评论)
     */
    private Integer parent_id;
    /**
     * //user---user_id  字段(用户id)
     */
    private Integer user_id;
    /**
     * //user---name  字段(用户姓名)
     */
    private String name;
    /**
     * comment---content  字段(评论内容)
     */
    private String content;
    /**
     * comment---date 字段(评论日期)
     */
    private Date date;
    /**
     * (评论的子评论)
     */
    private List<Comment> child;

    /**
     * 默认构造方法
     */
    public Comment(){}

    /**
     * 有参构造方法
     * @param movie_id
     * @param parent_id
     * @param user_id
     * @param content
     * @param child
     */
    public Comment(Integer movie_id, Integer parent_id,  String content, Integer user_id,List<Comment> child) {
        this.movie_id = movie_id;
        this.parent_id = parent_id;
        this.user_id = user_id;
        this.content = content;
        this.child = child;
    }


    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Comment> getChild() {
        return child;
    }

    public void setChild(List<Comment> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", movie_id=" + movie_id +
                ", parent_id=" + parent_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", child=" + child +
                '}';
    }
}
