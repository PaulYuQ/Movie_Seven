package factory;

import java.util.ResourceBundle;

/**
 * @Author lao liu
 * @Date 2020/9/10 10:47
 * @Version 1.0
 * 工程类
 */
public class BeanFactory{
    private static ResourceBundle bundle = null;
    static {
        //获取配置文件信息
        bundle = ResourceBundle.getBundle("instance");
    }


    /**
     *     采用静态方法，反射，获取类实例，注：类需要添加默认的构造方法！！
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getInstance(String key,Class<T> cls){
        try {
            return (T)  Class.forName(bundle.getString(key)).newInstance();
        } catch (ClassNotFoundException |IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
