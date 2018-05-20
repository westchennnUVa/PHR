package personalDr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.westchen.phr.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;

import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemSelectedListener;


public class chat_record extends Activity
{
    DataBean mydatabean;
    private Handler handler1;
    private Handler handler;
    int identify;

    SimpleAdapter simpleAdapter;
    //Map<String, Object> listItem = new HashMap<String, Object>();
    //最近聊天列表
    private List<Doctor> doctorList = new ArrayList<Doctor>();
    //组名列表
    private List<String> groupList = new ArrayList<String>();
    //组员成员列表
    List<List<Doctor>> groupDoctorList = new ArrayList<List<Doctor>>();

    List<Doctor> allDoctorList = new ArrayList<Doctor>();

    public List<Map<String, Object>> listItems =
            new ArrayList<Map<String, Object>>();
    private String[] names = new String[]
            { "范帆达", "阿三地方", "个人", "阿三地方额"};
    private String[] descs = new String[]
            { "阿三地方范围", "啊广泛大使馆岁的法国"
                    , "啊二公司的非官方的三个", "是的风格如果"};
    private int[] imageIds = new int[]
            { R.drawable.ffd_p , R.drawable.ffd_t
                    , R.drawable.ffd_z , R.drawable.ffd_ee};
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ffd_activity_chat_record);
        getAllDoctor();
        getDoctorList();
        simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.ffd_simple_item,
                new String[] { "personName", "header" , "desc"},
                new int[] { R.id.name, R.id.header , R.id.desc });
        ListView list = (ListView) findViewById(R.id.mylist);
        // 为ListView设置Adapter
        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new OnItemClickListener() {
            // 第position项被单击时激发该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(chat_record.this);
                builder.setTitle("选择一个分组");
                String[] myPacket = new String[groupList.size()];
                for (int i = 0; i < groupList.size(); i++) {
                    myPacket[i] = groupList.get(i);
                }
                final List<Doctor> mylist = new ArrayList<Doctor>();
                final int pos = position;
                builder.setItems(myPacket, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < groupDoctorList.get(which).size(); i++) {
                            mylist.add(groupDoctorList.get(which).get(i));
                        }
                        mylist.add(allDoctorList.get(position));
                        groupDoctorList.add(which, mylist);
                        saveDoctorList();
                        new AlertDialog.Builder(chat_record.this)
                                .setTitle("添加成功")
                                .setMessage("已经把医生" + allDoctorList.get(position).getName() + "添加进分组")
                                .setPositiveButton("确定", null)
                                .show();
                        Intent intent = new Intent(chat_record.this, dr_list.class);
                        startActivity(intent);
                    }
                });
                builder.show();

            }
        });
        handler = new Handler(){

            @Override
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    //show.append(msg.obj.toString());

                }else if(msg.what == 13){
                    System.out.println("ffdffdffdffd1");
                    mydatabean = (DataBean)msg.obj;
                    doctorList = mydatabean.getDoctorList();
                    groupList = mydatabean.getGroupList();
                    groupDoctorList = mydatabean.getGroupDoctorList();
                }
            }
        };
        list.setOnItemSelectedListener(new OnItemSelectedListener() {
            // 第position项被选中时激发该方法
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        handler1 = new Handler(){

            @Override
            public void handleMessage(Message msg){
                if (msg.what == 1111){
                    allDoctorList = (List<Doctor>)msg.obj;
                    System.out.println("444444444444444" + allDoctorList.size());
                    for(int i=0;i<allDoctorList.size();i++)
                    {
                        Map<String, Object> listItem = new HashMap<String, Object>();
                        listItem.put("header", imageIds[i%4]);
                        listItem.put("personName", allDoctorList.get(i).getName());
                        System.out.println("4444444444444445555" + allDoctorList.get(i).getName());
                        listItem.put("desc", allDoctorList.get(i).getSex());
                        listItems.add(listItem);
                        simpleAdapter.notifyDataSetChanged();
                    }


                }
            }
        };

    }
    private void saveDoctorList() {
        new Thread() {
            @Override
            public void run() {
                JSONArray doctorListJson = new JSONArray();
                for (int i = 0; i < doctorList.size(); i++) {
                    doctorListJson.put(doctorList.get(i).getUserID());
                }
                System.out.println("asd2");
                JSONArray listJson1 = new JSONArray();
                for (int i = 0; i < groupList.size(); i++) {
                    try {
                        JSONObject listJson2 = new JSONObject();
                        JSONArray listJson3 = new JSONArray();
                        listJson2.put("分组名称", groupList.get(i));
                        System.out.println("asdfghjl1" + groupList.get(i));
                        List<Doctor> groupDoctor = groupDoctorList.get(i);
                        for (int j = 0; j < groupDoctor.size(); j++) {
                            listJson3.put(groupDoctor.get(j).getUserID());
                        }
                        listJson2.put("医生列表", listJson3);
                        listJson1.put(listJson2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("asd4");
                Map<String, String> map = new HashMap<String, String>();
                map.put("operation", "change");
                map.put("docterID", doctorListJson.toString());
                map.put("groupList", listJson1.toString());
                map.put("fromID", ffd_MainActivity.id);
                System.out.println("asd5" + doctorListJson.toString());
                new HttpUtil().post(CommonUrl.Chat, map);
                System.out.println("asd6" + listJson1.toString());
            }
        }.start();
    }
    private void getDoctorList() {
        new Thread(){
            @Override
            public void run(){
                List<Doctor> doctorList1 = new ArrayList<Doctor>();
                List<String> groupList1 = new ArrayList<String>();
                List<List<Doctor>> groupDoctorList1 = new ArrayList<List<Doctor>>();
                System.out.println("获取数据1");
                Map<String, String> map = new HashMap<String, String>();
                map.put("operation", "getDoctorList");
                map.put("fromID", ffd_MainActivity.id);
                String jsonStr = new HttpUtil().post(CommonUrl.Chat, map);
                JSONObject json = null;
                System.out.println("ttttttt44");
                try {
                    json = new JSONObject(jsonStr);
                    JSONArray doctorListJson = new JSONArray((String) json.get("doctorID"));
                    for (int i = 0; i < doctorListJson.length(); i++){
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("userID", doctorListJson.getString(i));
                        String result = new HttpUtil().post(CommonUrl.GetUser, params);
                        JSONObject userJson = new JSONObject(result);
                        Doctor doctor = new Doctor(userJson);
                        doctorList1.add(doctor);
                        System.out.println("获取数据,用户名字是：" + doctor.getName());
                        System.out.println("获取数据,用户名字是：" + doctorList1.get(i).getName());
                        System.out.println("获取数据,列表大小为：" + doctorList1.size());
                        System.out.println("获取数据3");
                    }
                    JSONArray listJson1 = new JSONArray((String) json.get("groupList"));
                    JSONObject listJson2;
                    JSONArray listJson3;
                    for (int i = 0; i < listJson1.length(); i++) {
                        System.out.println("startstartstartstartstartstart");
                        listJson2 = listJson1.getJSONObject(i);
                        groupList1.add(listJson2.getString("分组名称"));
                        listJson3 = listJson2.getJSONArray("医生列表");
                        List<Doctor> groupDoctor = new ArrayList<Doctor>();
                        for (int j = 0; j < listJson3.length(); j++) {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("userID", listJson3.getString(j));
                            String result = new HttpUtil().post(CommonUrl.GetUser, params);
                            JSONObject userJson = new JSONObject(result);
                            groupDoctor.add(new Doctor(userJson));
                        }

//                        System.out.println("获取数据6");
                        groupDoctorList1.add(groupDoctor);

                        System.out.println("获取数据7");
                        System.out.println("endendendendendendendendend");
                    }
                    System.out.println("aaaaaaaaa1");
                    Message msg = new Message();
                    System.out.println("aaaaaaaaa2");
                    DataBean dataBean = new DataBean(doctorList1,groupList1,groupDoctorList1);
                    System.out.println("aaaaaaaaa3");
                    msg.obj = dataBean;
                    System.out.println("aaaaaaaaa4");
                    msg.what = 13;
                    System.out.println("aaaaaaaaa5");
                    handler.sendMessage(msg);
                    System.out.println("获取数据888888");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void getAllDoctor(){

        new Thread(){
            @Override
            public void run(){
                try {
                    List<Doctor> allDoctorList1 = new ArrayList<Doctor>();
                    //System.out.println("11111111111111111111");
                    String result = new HttpUtil().get(CommonUrl.GetAllDoctor);
                    //Log.i("test1",result);
                    JSONArray json = new JSONArray(result);
                    //System.out.println("3333333333333333333333"+json);
                    for(int i = 0;i<json.length();i++){
                        JSONObject jsonObject = json.getJSONObject(i);
                        allDoctorList1.add(new Doctor(jsonObject));
                        System.out.println("3333333333333333333333" + allDoctorList1.get(i).getName());
                    }
                    Message msg = new Message();
                    msg.obj = allDoctorList1;
                    msg.what = 1111;
                    System.out.println("99999999999999999999999" );
                    handler1.sendMessage(msg);
                    System.out.println("999999999999999999999991");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
