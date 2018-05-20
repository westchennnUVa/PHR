package treatmentRecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.westchen.phr.R;

import org.json.JSONException;

import java.util.List;


/**
 * Created by 吴俊达 on 2016/3/19.
 */
public class TreatmentActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView list;
    private List<Treatment> data;
    public static Handler handler;
    private TreatmentDataManager dataManager;
    private TreatmentAdapter adapter;
    private String id1,age,realName,sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        Intent i = getIntent();
        id1 = i.getStringExtra("id");
        age = i.getStringExtra("age");
        realName = i.getStringExtra("realName");
        sex = i.getStringExtra("sex");
        init();
    }

    private void init()
    {
        list = (ListView)findViewById(R.id.list);
        list.setOnItemClickListener(this);

        //初始化列表
        dataManager = new TreatmentDataManager();
        try {
            data = dataManager.getData("001");
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
                {
                    adapter = new TreatmentAdapter(TreatmentActivity.this,data);
                    list.setAdapter(adapter);
                }
                else if(msg.what == 2)
                {
                    Toast.makeText(TreatmentActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //获取用户点击的问题
        Treatment treatment = data.get(position);

        //将数据打包在Intent里面，并跳转界面
        Intent intent = new Intent(this, TreatmentInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("treatment",treatment);
        bundle.putString("id",id1);
        bundle.putString("age",age);
        bundle.putString("realName",realName);
        bundle.putString("sex",sex);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
