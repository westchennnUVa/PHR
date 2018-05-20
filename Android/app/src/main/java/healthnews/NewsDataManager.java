package healthnews;

import android.util.Log;

import com.example.westchen.phr.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;
/**
 * Created by 吴俊达 on 2016/3/16.
 */
public class NewsDataManager extends HealthNewsActivity{

    private String head;//修改！！！！！
    private String name;
    private String date;
    private String title;
    private String content;
    private String userID;
    private String newsID;
    private int commentNum;
    private String type;
    private News self;

    List<News> data = new ArrayList<News>();
    JSONObject object;


    public List<News> getData() throws JSONException {

        data.clear();
        getNewsData();

        return data;
    }

    public List<News> getAnswerData(News n) throws JSONException {

        data.clear();
        getAnswerNetData(n);

        return data;
    }

    public void setData()
    {
        //头像信息需修改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        News news = new News(userID,newsID, R.drawable.head1, name, date, title, content, commentNum, type);
        data.add(news);
    }

    public News getSelfData()
    {
        //若修改数据类型相应修改调用的地方
        self = new News("666666",R.drawable.head1, "da", " ", " ", " ");
        return self;
    }

    private void getNewsData(){
        new Thread(){
            @Override
            public void run(){
                try {
                    final Map<String,String> newInfo = new HashMap<String,String>();
                    //newInfo.add(new BasicNameValuePair("info", "operation "));
                    newInfo.put("operation", "getAll");
                    String result = new HttpUtil().post(CommonUrl.HEALTH, newInfo);
                    if(result != null) {
                        Log.i("getData", result);
                        JSONArray datelist = new JSONArray(result);
                        //解析返回信息
                        for (int i = 0; i < datelist.length(); i++) {
                            object = datelist.getJSONObject(i);
                            name = (String) object.get("name");
                            head = (String) object.get("head");//修改
                            date = (String) object.get("time");
                            title = (String) object.get("title");
                            content = (String) object.get("content");
                            newsID = (String) object.get("newsID");
                            userID = (String) object.get("userID");
                            commentNum = (int)object.get("commentNum");
                            type = (String) object.get("type");
                            setData();
                        }
                    }
                    else
                    {
                        Log.i("not net", "not net");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getAnswerNetData(News n) {

        final Map<String,String> newInfo = new HashMap<String,String>();
        newInfo.put("info", "operation id");
        newInfo.put("id", n.getNewsID());//获取贴子id
        Log.i("newsid", n.getNewsID().toString());
        newInfo.put("operation", "read");
        new Thread() {
            @Override
            public void run() {
                try {

                    //将贴子id发送获取改贴子对应的回复
                    String result = new HttpUtil().post(CommonUrl.HEALTH, newInfo);
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
                            HealthNewsAnswerActivity.handler.sendEmptyMessage(1);

                        }
                    }
                    else
                    {
                        HealthNewsAnswerActivity.handler.sendEmptyMessage(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}

