package pojo;

/**
 * @Author Miss kun
 * @Date 2020/9/10 10:52
 * @Version 1.0
 * user类
 */
public class User{
    /**
     * 自增
     */
    private int id ;
    /**
     * user_id
     */
    private String user_id;
    /**
     * user_psw
     */
    private String user_psw;

    public User() {
    }

    public User(int id, String user_id, String user_psw) {
        this.id = id;
        this.user_id = user_id;
        this.user_psw = user_psw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_psw() {
        return user_psw;
    }

    public void setUser_psw(String user_psw) {
        this.user_psw = user_psw;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", user_psw='" + user_psw + '\'' +
                '}';
    }
}
