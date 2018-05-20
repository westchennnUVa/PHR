package tijianguahao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.westchen.phr.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;

/**
 * Created by 吴俊达 on 2016/3/16.
 */
public class ExaminationActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView list;
    private List<Examination> data;
    private ImageButton add;
    private ImageButton back;
    private ImageButton shangchuan;
    private ImageButton my;
    private ImageButton wodetj;
    private ImageButton shouye;
    private String id;
    private EDataManager dataManager;
    private ExaminationAdapter adapter;
    public static Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examination);
        Intent i = getIntent();
        id = i.getStringExtra("id");

        init();

        //加号键按键监听
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //返回键按键监听
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //上传键按键监听
        shangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //我的键按键监听
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //我的体检键按键监听
        wodetj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExaminationActivity.this, SelfExaminationActivity.class);
                startActivity(intent);

            }
        });

        //首页键按键监听
        shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //初始化
    public void init(){
        //添加组件
        list = (ListView)findViewById(R.id.list);
        list.setOnItemClickListener(this);
        add = (ImageButton)findViewById(R.id.add);
        back = (ImageButton)findViewById(R.id.back);
        shangchuan = (ImageButton)findViewById(R.id.shangchuan);
        my = (ImageButton)findViewById(R.id.wode);
        wodetj = (ImageButton)findViewById(R.id.wodetj);
        shouye = (ImageButton)findViewById(R.id.shouye);
        dataManager = new EDataManager();

        //获取数据
        try {
            data = dataManager.getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("msgInfo", msg.toString());
                if(msg.what == 1)
                {}
                    //adapter.notifyDataSetChanged();
                else if(msg.what == 2)
                {
                    Toast.makeText(ExaminationActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
        adapter = new ExaminationAdapter(this,data);
        list.setAdapter(adapter);

    }

    //列表项监听
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //获取用户点击的体检信息
        final Examination examination = data.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("examination",examination);

        //提示框
        AlertDialog alert = new AlertDialog.Builder(ExaminationActivity.this)
                .create();
        //alert.setIcon(R.drawable.xingming);
        alert.setTitle("确认订单？");
        alert.setMessage("是否确认订单信息并预约体检？");

        alert.setButton(DialogInterface.BUTTON_NEGATIVE,"否",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //发送
                    new Thread() {

                        @Override
                        public void run() {
                            final Map<String,String> paprams = new HashMap<String, String>();
                            //Map<String, String> paprams = new HashMap<String, String>();
                            paprams.put("operation", "send");
                            paprams.put("type", "体检");

                            paprams.put("hospital", examination.getHospital());
                            paprams.put("people", examination.getPeople());
                            paprams.put("price", examination.getPrice());
                            paprams.put("time", examination.getDate());

                            //固定数据需更改为个人数据
                            paprams.put("userName", "test");
                            paprams.put("IDCard", "12345678");
                            paprams.put("phone", "123123123");
                            paprams.put("name", "name");
                            String result = new HttpUtil().post(CommonUrl.Registration, paprams);
                            if(result != null)
                                Log.i("tijianguahao",result);
                            else
                                Toast.makeText(ExaminationActivity.this,"提交失败", Toast.LENGTH_SHORT).show();
                        }

                    }.start();
                }
        });
        alert.show();

    }

    //接受测试用
    private class getTask2 implements Runnable {

        @Override
        public void run() {

            try {
                //Map<String, String> paprams = new HashMap<String, String>();
                final Map<String,String> paprams = new HashMap<String,String >();
                paprams.put("operation", "get");

                //通知的id为当前用户id
                paprams.put("fromID",id);

                String result = new HttpUtil().post(CommonUrl.Registration, paprams);

                JSONObject json = new JSONObject(result);
                String name = (String) json.get("name");
                String IDCard = (String) json.get("IDCard");
                String phone = (String) json.get("phone");
                String time = (String) json.get("time");

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), 0,
                        new Intent(getApplicationContext(), ExaminationActivity.class), 0);
                Notification notify2 = new Notification.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.head1)
                        .setTicker("您有新短消息，请注意查收！")
                        .setContentTitle("预约时间: " + time)// 设置在下拉status
                        .setContentText("你有新的消息")// TextView中显示的详细内容
                        .setContentIntent(pendingIntent2) // 关联PendingIntent
                        .getNotification(); // 需要注意build()是在API level
                notify2.flags |= Notification.FLAG_AUTO_CANCEL;
                manager.notify(1, notify2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
