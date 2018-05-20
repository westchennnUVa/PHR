package adapter;

/**
 * Created by AnthonySSS on 2016/3/12.
 */
public class Comment {
    private String title;
    //名称
    private String content;
    //时间
    private String time;

    public Comment(String title,String time,String content){
        this.title = title;
        this.content = content;
        this.time =time;
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime(){
        return time;
    }
}
