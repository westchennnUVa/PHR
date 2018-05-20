package tijianguahao;

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
public class EDataManager extends ExaminationActivity {

    private String name;    //名字
    private String date;    //时间
    private String people;  //人群
    private String id;       //id
    private String hospital;//医院名
    private String recordType;//类型
    private String price;    //价格

    List<Examination> data = new ArrayList<Examination>();
    JSONObject object;

    //外部调用函数
    public List<Examination> getData() throws JSONException {

        data.clear();
        getEData();

        return data;
    }

    public List<Examination> getSelfData() throws JSONException {

        data.clear();
        getSEData();

        return data;
    }
    //添加信息到list里
    private void setData(){
        //顺序 head name date people id hospital recordType price
        Examination e = new Examination(R.drawable.head2, name, date, people, id, hospital, recordType, price);
        data.add(e);
    }


    //获取信息
    private void getEData(){
        new Thread(){
            @Override
            public void run(){
                try {
                    final Map<String,String> newInfo = new HashMap<String, String>();
                    //newInfo.add(new BasicNameValuePair("info", "operation "));
                    newInfo.put("operation", "get");
                    newInfo.put("type", "体检");
                    String result = new HttpUtil().post(CommonUrl.EXAMINATION, newInfo);
                    if(result != null) {
                        Log.i("getData", result);
                        JSONArray datelist = new JSONArray(result);
                        //解析返回信息
                        for (int i = 0; i < datelist.length(); i++) {
                            object = datelist.getJSONObject(i);
                            name = (String) object.get("name");
                            //head = (String) object.get("head");//修改
                            date = (String) object.get("time");
                            hospital = (String) object.get("hospital");
                            price = (String) object.get("price");
                            id = (String) object.get("id");
                            people = (String) object.get("people");
                            recordType = (String) object.get("recordType");
                            setData();
                            ExaminationActivity.handler.sendEmptyMessage(1);
                        }
                    }
                    else
                    {
                        Log.i("not net", "not net");
                        ExaminationActivity.handler.sendEmptyMessage(2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //获取个人信息
    private void getSEData(){
        new Thread(){
            @Override
            public void run(){
                try {
                    final Map<String,String> newInfo = new HashMap<String, String>();
                    //newInfo.add(new BasicNameValuePair("info", "operation "));
                    newInfo.put("operation", "read");
                    newInfo.put("type", "体检");

                    newInfo.put("userID", "001");//用户个人ID，需修改

                    String result = new HttpUtil().post(CommonUrl.EXAMINATION, newInfo);
                    if(result != null) {
                        Log.i("getData", result);
                        JSONArray datelist = new JSONArray(result);
                        //解析返回信息
                        for (int i = 0; i < datelist.length(); i++) {
                            object = datelist.getJSONObject(i);
                            name = (String) object.get("name");
                            //head = (String) object.get("head");//修改
                            date = (String) object.get("time");
                            hospital = (String) object.get("hospital");
                            price = (String) object.get("price");
                            id = (String) object.get("id");
                            people = (String) object.get("people");
                            recordType = (String) object.get("recordType");
                            setData();
                            ExaminationActivity.handler.sendEmptyMessage(1);
                        }
                    }
                    else
                    {
                        Log.i("not net", "not net");
                        ExaminationActivity.handler.sendEmptyMessage(2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
