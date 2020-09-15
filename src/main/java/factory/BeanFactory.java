package factory;

import java.util.ResourceBundle;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 14:28
 * @version: 1.0
 */
public class BeanFactory{
    private static ResourceBundle bundle = null;
    static {
        //获取配置文件信息
        bundle = ResourceBundle.getBundle("instance");
    }

    /**
     * create by: sky
     * create time: 15:30 2020/9/14
     *  返回构造函数
     * @Param: key
     * @Param: cls
     * @return T
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
