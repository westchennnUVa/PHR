package item;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westchen.phr.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;

public class DrugItem4 extends AppCompatActivity {

    private TextView drugtv;
    private JSONObject object;
    private String item1;
    Map<String,String> list=new HashMap<String,String>();

    private Handler handler;

    public static final int SUCCESS=1;
    public static final int ERROR=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_item4);
        drugtv =(TextView)findViewById(R.id.yaopintv4);
        showView1();
        handler=new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SUCCESS:
                        Toast.makeText(DrugItem4.this, "显示成功", Toast.LENGTH_SHORT).show();
                        drugtv.setText(item1);
                        break;
                    case ERROR:
                        Toast.makeText(DrugItem4.this, "显示失败"+msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
    public void showView1(){
        list.put("operation", "getOne");
        list.put("type", "药品");
        list.put("newsID","14");
        new Thread(){
            @Override
            public void run(){
                String result = new HttpUtil().post(CommonUrl.HEALTH,list);
                if(result!=null||!result.equals("")) {
                    Log.i("get data", result.toString());
                    try {
                        JSONObject datelist = new JSONObject(result);
                        object = datelist;
                        item1 = (String) object.get("content");
                        item1=item1.replace("newline","\n\t\t");
                    }catch (Exception e){}
                    handler.sendEmptyMessage(SUCCESS);
                }
                else
                {
                    handler.sendEmptyMessage(ERROR);
                }
            }

        }.start();
    }
}
