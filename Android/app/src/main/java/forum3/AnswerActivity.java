package forum3;

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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import forumutil.HttpUtil2;
import url.CommonUrl;


public class AnswerActivity extends Activity {

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
    private List<Question> data;

    public static Handler handler;
    private Intent intent;
    private forum3.DataManager dataManager;
    private Question thisQuestion;
    private forum3.AnswerAdapter adapter;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

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
                Intent intent = new Intent(AnswerActivity.this, OnLineForumActivity.class);
                startActivity(intent);
                AnswerActivity.this.finish();
            }
        });

        //刷新键按键监听
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updata();
                //adapter.notifyDataSetChanged();
                //list.setAdapter(adapter);
                Toast.makeText(AnswerActivity.this, "正在刷新", Toast.LENGTH_SHORT).show();
            }
        });

        //发送键按键监听
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!inputMessage.toString().equals("")) {

                    //获取当前用户信息,需修改
                    Question self = dataManager.getSelfData();
                    //获取当前用户信息,需修改

                    //包装数据
                    final List<NameValuePair> newInfo = new ArrayList<NameValuePair>();
                    newInfo.add(new BasicNameValuePair("info", "operation userID newsID commentNum content"));
                    newInfo.add(new BasicNameValuePair("operation", "answer"));
                    newInfo.add(new BasicNameValuePair("userID", self.getUserID()));//用户id
                    newInfo.add(new BasicNameValuePair("newsID", thisQuestion.getNewsID()));
                    newInfo.add(new BasicNameValuePair("commentNum", String.valueOf((thisQuestion.getCommentNum() + 1))));
                    newInfo.add(new BasicNameValuePair("time", currentDate));
                    //newInfo.add(new BasicNameValuePair("title", ""));
                    newInfo.add(new BasicNameValuePair("content", inputMessage.getText().toString()));

                    //post数据
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                String result = new HttpUtil2().post(CommonUrl.NEWS, newInfo);
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

                    commentNum.setText("评论数(" + (thisQuestion.getCommentNum() + 1) + ")");
                    inputMessage.setText("");
                    Updata();
                }
                else
                {
                    Toast.makeText(AnswerActivity.this, "内容不能为空", Toast.LENGTH_SHORT)
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

        thisQuestion = (Question)bundle.get("question");

        //初始化列表
        data = null;
        dataManager = new forum3.DataManager();
        try {
            data = dataManager.getAnswerData(thisQuestion);
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
        adapter = new forum3.AnswerAdapter(AnswerActivity.this,data);
        list.setAdapter(adapter);


        //设置question信息
        head.setImageResource(thisQuestion.getHead());
        name.setText(thisQuestion.getName());
        date.setText(thisQuestion.getDate());
        title.setText(thisQuestion.getTitle());
        content.setText(thisQuestion.getContent());
        commentNum.setText("评论数(" + thisQuestion.getCommentNum() + ")");

    }

    //刷新信息
    private void Updata()
    {
        data = null;
        try {
            data = dataManager.getAnswerData(thisQuestion);
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
                    Toast.makeText(AnswerActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        };


    }

    //修改系统返回键
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            if((int)bundle.get("type") == 1)
            {
                Intent intent = new Intent(this, OnLineForumActivity.class);
                startActivity(intent);
                this.finish();
            }
            else if((int)bundle.get("type") == 2)
            {
                Intent intent = new Intent(this, MyTActivity.class);
                startActivity(intent);
                this.finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}