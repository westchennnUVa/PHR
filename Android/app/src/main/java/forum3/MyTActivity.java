package forum3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.westchen.phr.R;

import org.json.JSONException;

import java.util.List;

/**
 * Created by 吴俊达 on 2016/3/14.
 */
public class MyTActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView list;
    private List<forum3.Question> data =null;
    private List<forum3.Question> temp;
    private ImageButton back;
    private ImageButton home;
    private ImageButton my;
    private ImageButton myt;
    private ImageButton sendt;
    private ImageButton setting;

    private Intent intent;
    private DataManager dataManager;
    private ForumAdapter adapter;
    public static Handler MyThandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tiezi);


        //初始化界面控件等
        init();

        //返回按键监听
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyTActivity.this, forum3.OnLineForumActivity.class);
                startActivity(intent);
                MyTActivity.this.finish();
            }
        });

        //发送贴子按键监听
        sendt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取当前用户信息,需修改
                forum3.Question self = dataManager.getSelfData();
                //获取当前用户信息,需修改
                Intent intentAdd = new Intent(MyTActivity.this, AddQuestionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("question",self);
                bundle.putSerializable("type",2);
                intentAdd.putExtras(bundle);
                startActivity(intentAdd);
                MyTActivity.this.finish();
            }
        });

    }

    //初始化界面控件
    public void init(){

        //获取组件
        list = (ListView)findViewById(R.id.forum);
        list.setOnItemClickListener(this);
        back = (ImageButton)findViewById(R.id.back); //左上返回键
//        home = (ImageButton)findViewById(R.id.home); //中间红色home
        my = (ImageButton)findViewById(R.id.my);      //我的
        myt = (ImageButton)findViewById(R.id.myt);    //我都贴子
        setting = (ImageButton)findViewById(R.id.setting);//设置
        sendt = (ImageButton)findViewById(R.id.sendt); //发送贴子


        //初始化列表
        dataManager = new DataManager();
        forum3.Question self = dataManager.getSelfData();
        try {
            data = dataManager.getMyData(self);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyThandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1) {
                    adapter = new ForumAdapter(MyTActivity.this, data);
                    list.setAdapter(adapter);
                }
                else if(msg.what == 2)
                {
                    Toast.makeText(MyTActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }



    //参数int position ，代表你点的时列表的第几项（从0开始）
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //获取用户点击的问题
        forum3.Question question = data.get(position);

        //将数据打包在Intent里面，并跳转界面
        Intent intent = new Intent(this, AnswerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("question",question);
        bundle.putSerializable("type",2);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }

    //修改返回键
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

           Intent intent = new Intent(this, forum3.OnLineForumActivity.class);
           startActivity(intent);
           this.finish();
           return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
