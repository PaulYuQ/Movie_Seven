package pojo;

/**
 * @Author Miss kun
 * @Date 2020/9/10 10:52
 * @Version 1.0
 * user类
 */
public class User{
    //自增
    private int user_id;
    private String name;
    private String password;
    private String date;
    private String phone;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", date='" + date + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public User(int user_id, String name, String password, String phone) {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
}
