package pojo;

/**
 * @author ：sky
 * @date ：Created in 2020/9/14 14:53
 * @version: 1.0
 * 展示历史记录的实体类
 */
public class ShowHistory {


    /**
     * type: 电影类型
     */
    private String type;

    /**
     *name: 历史记录的电影名字
     */
    private String name;

    /**
     *image_url: 历史记录的电影图片
     */
    private  String image_url;

    /**
     * actor:历史记录的电影作者
     */
    private String actor;

    /**
     *progress: 历史记录的观影时间
     */
    private int progress;

    /**
     * history_id:历史记录的id
     */
    private int history_id;


    /**
     * create by: sky
     * create time: 19:53 2020/9/14
     * 无参构造函数
     * @Param:
     * @return
     */
    public ShowHistory() {

    }

    /**
     * create by: sky
     * create time: 10:22 2020/9/15
     *
     * @Param: type
     * @Param: name
     * @Param: image_url
     * @Param: actor
     * @Param: progress
     * @Param: history_id
     * @return
     */
    public ShowHistory(String type, String name, String image_url, String actor, int progress, int history_id) {
        this.type = type;
        this.name = name;
        this.image_url = image_url;
        this.actor = actor;
        this.progress = progress;
        this.history_id = history_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    @Override
    public String toString() {
        return "ShowHistory{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", actor='" + actor + '\'' +
                ", progress=" + progress +
                ", history_id=" + history_id +
                '}';
    }
}
