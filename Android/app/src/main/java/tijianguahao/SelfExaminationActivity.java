package tijianguahao;

import android.app.Activity;
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
 * Created by 吴俊达 on 2016/3/16.
 */
public class SelfExaminationActivity extends Activity implements AdapterView.OnItemClickListener{
    private ListView list;
    private List<Examination> data;
    private ImageButton add;
    private ImageButton back;
    private ImageButton shangchuan;
    private ImageButton my;
    private ImageButton wodetj;
    private ImageButton shouye;

    private EDataManager dataManager;
    private ExaminationAdapter adapter;
    public static Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfexamination);
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
            data = dataManager.getSelfData();
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
                    Toast.makeText(SelfExaminationActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
        adapter = new ExaminationAdapter(this,data);
        list.setAdapter(adapter);

    }

    //列表项监听
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }


}
