package forum3;

import android.app.Activity;
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

import java.util.List;

/**
 * Created by 吴俊达 on 2016/3/11.
 */
public class OnLineForumActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView list;
    private List<Question> data =null;
    private List<Question> temp;
    private ImageButton refresh;
    private ImageButton back;

    private ImageButton my;
    private ImageButton myt;
    private ImageButton sendt;
    private ImageButton setting;
    //APP与服务器通信管理类
    private DataManager dataManager;
    private ForumAdapter adapter;
    public static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_forum);

        //初始化界面控件等
        init();

        //发表贴子按键监听
        sendt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取当前用户信息,需修改
                Question self = dataManager.getSelfData();
                //获取当前用户信息,需修改
                Intent intentAdd = new Intent(OnLineForumActivity.this, AddQuestionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("question",self);
                bundle.putSerializable("type",1);
                intentAdd.putExtras(bundle);
                startActivity(intentAdd);
                OnLineForumActivity.this.finish();
            }
        });

        //我的贴子按键监听
        myt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(OnLineForumActivity.this, MyTActivity.class);
                startActivity(intentAdd);
                OnLineForumActivity.this.finish();
            }
        });

        //刷新按键监听
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upData();
                //adapter.notifyDataSetChanged();
                //list.setAdapter(adapter);
                Toast.makeText(OnLineForumActivity.this, "正在刷新", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化界面控件
    public void init(){

        //获取组件
        list = (ListView)findViewById(R.id.forum);
        list.setOnItemClickListener(this);
        refresh = (ImageButton)findViewById(R.id.refresh);   //右上方刷新
        back = (ImageButton)findViewById(R.id.back); //左上返回键
        my = (ImageButton)findViewById(R.id.my);      //我的
        myt = (ImageButton)findViewById(R.id.myt);    //我都贴子
        setting = (ImageButton)findViewById(R.id.setting);//设置
        sendt = (ImageButton)findViewById(R.id.sendt); //发送贴子

        //初始化列表
        dataManager = new DataManager();
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
                Log.i("msgInfo",msg.toString());
                if(msg.what == 1)
                    adapter.notifyDataSetChanged();
                else if(msg.what == 2)
                {
                    Toast.makeText(OnLineForumActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
        adapter = new ForumAdapter(this,data);
        list.setAdapter(adapter);

    }


    //刷新获取贴子列表服务器数据
    public void upData()
    {
        try {
            temp = null;
            temp = dataManager.getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        data = temp;

        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1)
                    adapter.notifyDataSetChanged();
                else if(msg.what == 2)
                {
                    Toast.makeText(OnLineForumActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    //参数int position ，代表你点的时列表的第几项（从0开始）
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //获取用户点击的问题
        Question question = data.get(position);

        //将数据打包在Intent里面，并跳转界面
        Intent intent = new Intent(this, AnswerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("question",question);
        bundle.putSerializable("type",1);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }
}