package healthnews;

import java.io.Serializable;

/**
 * Created by 吴俊达 on 2016/3/15.
 */
public class News implements Serializable {


    private int head;       //头像
    private String name;    //名字
    private String date;    //时间
    private String title;   //标题
    private String content; //内容
    private String newsID;  //贴子ID
    private String userID;  //用户ID
    private int commentNum; //评论数
    private String type;     //类型

    //
    public News(String userID, int head, String name, String date, String title, String content) {
        this.userID = userID;
        this.head = head;
        this.name = name;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public News(String userID, String newsID, int head, String name, String date, String title, String content, int commentNum, String type) {
        this.userID = userID;
        this.newsID = newsID;
        this.head = head;
        this.name = name;
        this.date = date;
        this.title = title;
        this.content = content;
        this.commentNum = commentNum;
        this.type = type;
    }

    public News() {
    }

    //读写各项内容
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

}

