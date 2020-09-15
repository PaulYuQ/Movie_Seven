package pojo;

/**
 * @author 宋敏超
 * @version 1.0
 * @date 2020/9/15 8:33
 */
public class Admin {
    private int id;
    private String name;
    private String password;
    private String phone;
    private int control;

    public Admin(){}



    public Admin(String name,String password){
        this.name=name;
        this.password=password;
        this.control=0;
    }
    public Admin(String name,String password,String phone){
        this.name=name;
        this.password=password;
        this.phone=phone;
        this.control=0;
    }

    public Admin(int id,String name,String password,String phone,int control){
        this.id=id;
        this.name=name;
        this.password=password;
        this.phone=phone;
        this.control=control;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", control=" + control +
                '}';
    }
}
