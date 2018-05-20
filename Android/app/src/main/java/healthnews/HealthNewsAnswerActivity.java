package healthnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westchen.phr.R;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;


/**
 * Created by 吴俊达 on 2016/3/16.
 */
public class HealthNewsAnswerActivity extends Activity {

    private ImageView head;
    private TextView name;
    private TextView date;
    private TextView title;
    private TextView content;
    private TextView commentNum;
    private EditText inputMessage;
    private Button send;
    private ImageButton back;
    private ImageButton refresh;
    private ListView list;
    private List<News> data;

    public static Handler handler;
    private Intent intent;
    private NewsDataManager dataManager;
    private News thisNews;
    private HealthNewsAnswerAdapter adapter;
    private Bundle bundle;
    private Map<String,String> newInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_health);

        //获取当前时间
        SimpleDateFormat formatter  =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        final String currentDate = formatter.format(curDate);

        //初始化界面控件
        init();

        //返回键按键监听
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthNewsAnswerActivity.this, HealthNewsActivity.class);
                startActivity(intent);
                HealthNewsAnswerActivity.this.finish();
            }
        });

        //刷新键按键监听
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updata();
                //adapter.notifyDataSetChanged();
                //list.setAdapter(adapter);
                Toast.makeText(HealthNewsAnswerActivity.this, "正在刷新", Toast.LENGTH_SHORT).show();
            }
        });

        //发送键按键监听
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!inputMessage.toString().equals("")) {

                    //获取当前用户信息,需修改
                    News self = dataManager.getSelfData();
                    //获取当前用户信息,需修改

                    //包装数据
                    //final List<NameValuePair> newInfo = new ArrayList<NameValuePair>();
                    newInfo = new HashMap<String, String>();
                    newInfo.put("info", "operation userID newsID commentNum content");
                    newInfo.put("operation", "answer");
                    newInfo.put("userID", self.getUserID());//用户id
                    newInfo.put("newsID", thisNews.getNewsID());
                    newInfo.put("commentNum", String.valueOf((thisNews.getCommentNum() + 1)));
                    newInfo.put("time", currentDate);
                    //newInfo.add(new BasicNameValuePair("title", ""));
                    newInfo.put("content", inputMessage.getText().toString());

                    //post数据
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                String result = new HttpUtil().post(CommonUrl.HEALTH, newInfo);
                                Message msg = new Message();
                                msg.obj = result;
                                //r = result;
                                //handler.sendMessage(msg);
                                Log.i("answer reply", result.toString());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                    commentNum.setText("评论数(" + (thisNews.getCommentNum() + 1) + ")");
                    inputMessage.setText("");
                    Updata();
                }
                else
                {
                    Toast.makeText(HealthNewsAnswerActivity.this, "内容不能为空", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    //初始化界面控件
    public void init() {
        head = (ImageView)findViewById(R.id.head);
        name = (TextView)findViewById(R.id.name);
        date = (TextView)findViewById(R.id.date);
        title = (TextView)findViewById(R.id.title);
        commentNum = (TextView)findViewById(R.id.commentNum);
        content = (TextView)findViewById(R.id.content);
        inputMessage = (EditText)findViewById(R.id.inputMessage);
        send = (Button)findViewById(R.id.send);
        back = (ImageButton)findViewById(R.id.back);
        refresh = (ImageButton)findViewById(R.id.refresh);   //右上方刷新
        list = (ListView)findViewById(R.id.answerList);

        //获取上一个界面传来的Question
        intent = getIntent();
        bundle = intent.getExtras();

        thisNews = (News)bundle.get("news");

        //初始化列表
        data = null;
        dataManager = new NewsDataManager();
        try {
            data = dataManager.getAnswerData(thisNews);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1)
                {

                }

            }
        };
        adapter = new HealthNewsAnswerAdapter(HealthNewsAnswerActivity.this,data);
        list.setAdapter(adapter);


        //设置question信息
        head.setImageResource(thisNews.getHead());
        name.setText(thisNews.getName());
        date.setText(thisNews.getDate());
        title.setText(thisNews.getTitle());
        content.setText(thisNews.getContent());
        commentNum.setText("评论数(" + thisNews.getCommentNum() + ")");

    }

    //刷新信息
    private void Updata()
    {
        data = null;
        try {
            data = dataManager.getAnswerData(thisNews);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1)
                    adapter.notifyDataSetChanged();
                else if(msg.what == 2)
                {
                    Toast.makeText(HealthNewsAnswerActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    //修改系统返回键
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            Intent intent = new Intent(this, HealthNewsActivity.class);
            startActivity(intent);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
