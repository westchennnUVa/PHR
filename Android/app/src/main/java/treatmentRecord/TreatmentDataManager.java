package treatmentRecord;

import android.util.Log;

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
 * Created by 吴俊达 on 2016/3/19.
 */
public class TreatmentDataManager extends TreatmentActivity {

    private String userID;       //用户id
    private String time;         //时间
    private String date;         //日期
    private String doctorName;  //医生名称
    private String doctorID;    //医生id
    private String hospital;    //医院名称
    private String section;     //科室
    private String doctorPhone;//医生电话
    private String kind;        //病症
    private String situation;  //病状
    private String method;     //诊疗方案
    private String price;      //价格
    private List<Treatment> data = new ArrayList<Treatment>();

    JSONObject object;


    public TreatmentDataManager() {}

    public List<Treatment> getData(String id) throws JSONException {

        data.clear();
        getNetData(id);

        return data;
    }

    public void setData()
    {
        //String userID, String time, String date, String doctorName, String doctorID, String hospital, String section, String doctorPhone, String kind, String situation, String method, String price
        Treatment t = new Treatment(userID,time,date, doctorName, doctorID, hospital, section, doctorPhone, kind, situation, method, price);
        data.add(t);
    }

    public void getNetData(String id){
        final Map<String,String> newInfo = new HashMap<String,String>();
        newInfo.put("fromID",id);//获取贴子id

        Log.i("user id", id);
        new Thread() {
            @Override
            public void run() {
                try {

                    //将贴子id发送获取改贴子对应的回复
                    String result = new HttpUtil().post(CommonUrl.Treatment, newInfo);
                    if(result != null)
                    {
                        Log.i("get treatment", result.toString());
                        JSONArray datelist = new JSONArray(result);
                        //将返回信息解析
                        for(int i = 0; i<datelist.length(); i++)
                        {
                            object = datelist.getJSONObject(i);
                            userID = (String)object.get("userID");
                            date = (String)object.get("date");
                            time = (String)object.get("time");
                            doctorName = (String)object.get("doctorName");
                            doctorID = (String)object.get("doctorID");
                            section = (String)object.get("section");
                            hospital = (String)object.get("hospital");
                            doctorPhone = (String)object.get("doctorPhone");
                            kind = (String)object.get("kind");
                            situation = (String)object.get("situation");
                            method = (String)object.get("method");
                            price = (String)object.get("price");
                            setData();
                        }
                        TreatmentActivity.handler.sendEmptyMessage(1);
                    }
                    else
                    {
                        TreatmentActivity.handler.sendEmptyMessage(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
