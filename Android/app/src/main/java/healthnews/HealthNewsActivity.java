package healthnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.westchen.phr.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import mating.Chronic;
import mating.Drug;
import mating.Mentality;
import mating.Regimen;
import mating.Sport;

/**
 * Created by 吴俊达 on 2016/3/15.
 */
public class HealthNewsActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private List<News> data;
    private ImageButton yp;     //药品按键
    private ImageButton xl;     //心理按键
    private ImageButton mxb;    //慢性病按键
    private ImageButton ys;     //养生按键
    private ImageButton yd;     //运动按键
    private ImageButton app;    //个人应用按键
    private ImageButton sq;     //社区按键
    private ImageButton sy;     //首页按键键
    private ImageButton my;     //我的按键
    private ImageButton back;   //返回键
    JSONObject object;
    private NewsDataManager dataManager;
    private NewsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_news);

        //初始化
        init();

        //返回键按键监听
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //药品键按键监听
        yp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(HealthNewsActivity.this, Drug.class);
                startActivity(i1);
            }
        });

        //心理键按键监听
        xl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(HealthNewsActivity.this, Mentality.class);
                startActivity(i2);
            }
        });

        //慢性病键按键监听
        mxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(HealthNewsActivity.this, Chronic.class);
                startActivity(i3);
            }
        });

        //养生键按键监听
        ys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(HealthNewsActivity.this, Regimen.class);
                startActivity(i4);
            }
        });

        //运动键按键监听
        yd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(HealthNewsActivity.this, Sport.class);
                startActivity(i5);
            }
        });


    }


    private void init() {
        //获取组件
        listView = (ListView) findViewById(R.id.hot_news);
        listView.setOnItemClickListener(this);
        yp = (ImageButton) findViewById(R.id.yaopin);
        xl = (ImageButton) findViewById(R.id.xinli);
        mxb = (ImageButton) findViewById(R.id.manxingbing);
        ys = (ImageButton) findViewById(R.id.yangsheng);
        yd = (ImageButton) findViewById(R.id.yundong);
        back = (ImageButton) findViewById(R.id.back);

        //获取
        dataManager = new NewsDataManager();
        try {
            data = dataManager.getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new NewsAdapter(this,data);
        listView.setAdapter(adapter);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //获取用户点击的资讯
        News news = data.get(position);

        //将数据打包在Intent里面，并跳转界面
        Intent intent = new Intent(this, HealthNewsAnswerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        //bundle.putSerializable("type",1);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }

}
