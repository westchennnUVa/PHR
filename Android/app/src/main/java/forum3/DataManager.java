package forum3;

import android.os.Message;
import android.util.Log;

import com.example.westchen.phr.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import forumutil.HttpUtil2;
import url.CommonUrl;



public class DataManager extends forum3.OnLineForumActivity {

    private String head;//修改！！！！！
    private String name;
    private String date;
    private String title;
    private String content;
    private String userID;
    private String newsID;
    private int commentNum;
    Question self = null;

    List<Question> data = new ArrayList<Question>();
    JSONObject object;


    public DataManager() {

    }


    /*
        获取全部贴子信息
    */
    public List<Question> getData() throws JSONException {

        data.clear();
        getNetData();

        return data;
    }

    /*
        获取回复信息
    */
    public List<Question> getAnswerData(Question q) throws JSONException {

        data.clear();
        getAnswerNetDataP(q);

        return data;
    }

    /*
        获取我的贴子信息
    */
    public List<Question> getMyData(Question q) throws JSONException {
        data.clear();
        getSelfNetDataP(q);

        return data;
    }

    //获取个人信息（需修改）!!!!!!!!!!!!!!!!!!!!!!!!!!
    public Question getSelfData()
    {
        //若修改数据类型相应修改调用的地方
        self = new Question("666666", R.drawable.head1, "da", " ", " ", " ");
        return self;
    }

    /*
        写入信息
    */
    public void setData()
    {
        //头像信息需修改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Question q = new Question(userID,newsID,R.drawable.head1, name, date, title, content, commentNum);
        data.add(q);
    }

    /*
       获取贴子信息
    */
    public void getNetData(){
        new Thread(){

            @Override
            public void run(){
                try {
                    String result = new HttpUtil2().get(CommonUrl.NEWS);
                    if(result != null) {
                        Log.i("getData", result);
                        JSONArray datelist = new JSONArray(result);
                        //解析返回信息
                        for (int i = 0; i < datelist.length(); i++) {
                            object = datelist.getJSONObject(i);
                            //name = (String) object.get("name");
                            head = (String) object.get("head");//修改
                            date = (String) object.get("time");
                            title = (String) object.get("title");
                            content = (String) object.get("content");
                            newsID = (String) object.get("newsID");
                            userID = (String) object.get("userID");
                            commentNum = (int)object.get("commentNum");
                            setData();
                            forum3.OnLineForumActivity.handler.sendEmptyMessage(1);
                        }
                    }
                    else
                    {
                        Log.i("not net", "not net");
                        OnLineForumActivity.handler.sendEmptyMessage(2);
                    }
                    Message msg = new Message();
                    msg.obj = result;
                } catch (IOException e) {
                   e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    /*
        获取回复新消息post版
    */
    public void getAnswerNetDataP(Question q) {

        final List<NameValuePair> newInfo = new ArrayList<NameValuePair>();
        newInfo.add(new BasicNameValuePair("info", "operation id"));
        newInfo.add(new BasicNameValuePair("id", q.getNewsID()));//获取贴子id
        Log.i("newsid", q.getNewsID().toString());
        newInfo.add(new BasicNameValuePair("operation", "read"));
        new Thread() {
            @Override
            public void run() {
                try {

                    //将贴子id发送获取改贴子对应的回复
                    String result = new HttpUtil2().post(CommonUrl.NEWS, newInfo);
                    if(result != null)
                    {
                        Log.i("get answer", result.toString());
                        JSONArray datelist = new JSONArray(result);
                        //将返回信息解析
                        for(int i = 0; i<datelist.length(); i++)
                        {
                            object = datelist.getJSONObject(i);
                            name = (String)object.get("name");
                            date = (String)object.get("time");
                            //title = (String)object.get("title");
                            content = (String)object.get("content");
                            userID = (String)object.get("userID");
                            newsID = (String)object.get("newsID");
                            setData();
                            AnswerActivity.handler.sendEmptyMessage(1);
                        }
                    }
                    else
                    {
                        AnswerActivity.handler.sendEmptyMessage(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    /*
        获取我的贴子的信息
    */
    public void getSelfNetDataP(Question q) {

        final List<NameValuePair> newInfo = new ArrayList<NameValuePair>();
        newInfo.add(new BasicNameValuePair("info", "operation userID"));
        newInfo.add(new BasicNameValuePair("userID", q.getUserID()));//获取用户id
        Log.i("user id", q.getUserID().toString());
        newInfo.add(new BasicNameValuePair("operation", "my"));
        new Thread() {
            @Override
            public void run() {
                try {

                    //将贴子id发送获取改贴子对应的回复
                    String result = new HttpUtil2().post(CommonUrl.NEWS, newInfo);
                    if(result != null)
                    {
                        Log.i("get self data", result.toString());
                        JSONArray datelist = new JSONArray(result);

                        //将返回信息解析
                        for(int i = 0; i<datelist.length(); i++)
                        {
                            object = datelist.getJSONObject(i);
                            name = (String)object.get("name");
                            date = (String)object.get("time");
                            title = (String)object.get("title");
                            content = (String)object.get("content");
                            userID = (String)object.get("userID");
                            newsID = (String)object.get("newsID");
                            commentNum = (int)object.get("commentNum");
                            setData();
                        }
                        MyTActivity.MyThandler.sendEmptyMessage(1);
                    }
                    else
                    {
                        MyTActivity.MyThandler.sendEmptyMessage(2);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


 }

