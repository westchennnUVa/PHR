package forum3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westchen.phr.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import forumutil.HttpUtil2;
import url.CommonUrl;

public class AddQuestionActivity extends Activity{
    private ImageView head;
    private TextView name;
    private TextView date;
    private EditText title;
    private EditText content;
    private Button send;
    private Button clean;
    private ImageButton back;

    private String titleStr;
    private String contentStr;

    private Handler handler;
    private Intent intent;
    private Bundle bundle;
    private int temp;

    private forum3.DataManager dataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        //获取当前时间
        SimpleDateFormat formatter  =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        final String currentDate = formatter.format(curDate);

        //初始化界面控件
        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddQuestionActivity.this, forum3.OnLineForumActivity.class); //修改类名！！！！！！！！！！！！
                startActivity(intent);
                AddQuestionActivity.this.finish();
            }
        });
        date.setText(currentDate);

        //提交问题
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取当前用户信息,需修改
                forum3.Question self = dataManager.getSelfData();
                //获取当前用户信息,需修改
                titleStr = title.getText().toString();
                contentStr = content.getText().toString();

                final List<NameValuePair> newInfo = new ArrayList<NameValuePair>();
                newInfo.add(new BasicNameValuePair("info", "operation userID title content"));
                newInfo.add(new BasicNameValuePair("operation", "save"));
//                newInfo.add(new BasicNameValuePair("head", "head"));
                newInfo.add(new BasicNameValuePair("userID", self.getUserID()));
//                newInfo.add(new BasicNameValuePair("name",self.getName()));
//                newInfo.add(new BasicNameValuePair("time", currentDate));
                newInfo.add(new BasicNameValuePair("title",titleStr));
                newInfo.add(new BasicNameValuePair("content",contentStr));
                new Thread(){
                    @Override
                    public void run(){
                        try {
                            String result = new HttpUtil2().post(CommonUrl.NEWS, newInfo);
                            Message msg = new Message();
                            msg.obj = result;
                            Log.i("test", result.toString());
                            if(result.equals("post请求成功!"))
                            {
                                Toast.makeText(AddQuestionActivity.this, "发贴成功", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
                Intent intent = new Intent(AddQuestionActivity.this, forum3.OnLineForumActivity.class);
                startActivity(intent);

            }
        });

        clean = (Button)findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("");
                content.setText("");
            }
        });

    }

    //初始化界面
    public void init() {

        dataManager = new forum3.DataManager();
        head = (ImageView)findViewById(R.id.head);
        name = (TextView)findViewById(R.id.name);
        date = (TextView)findViewById(R.id.date);

        back = (ImageButton)findViewById(R.id.back);
        title = (EditText)findViewById(R.id.addTitle);

        content = (EditText)findViewById(R.id.addContent);
        //设置输入框模式
        content.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        content.setGravity(Gravity.TOP);
        content.setSingleLine(false);
        content.setHorizontallyScrolling(false);


        //获取上一个界面传来的Question
        intent = getIntent();
        bundle = intent.getExtras();
        forum3.Question question = (forum3.Question)bundle.get("question");
        temp = (int)bundle.get("type");
        //设置question信息
        head.setImageResource(question.getHead());
        name.setText(question.getName());
        date.setText(question.getDate());
        title.setText(question.getTitle());
        content.setText(question.getContent());

    }

    //修改系统返回键
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if(temp == 1)
            {
                Intent intent = new Intent(this, forum3.OnLineForumActivity.class);
                startActivity(intent);
                this.finish();
            }
            else if(temp == 2)
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
