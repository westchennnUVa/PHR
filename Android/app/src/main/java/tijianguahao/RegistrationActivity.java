package tijianguahao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.westchen.phr.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;

/**
 * Created by 吴俊达 on 2016/3/18.
 */
public class RegistrationActivity extends Activity{

    private EditText name;
    private EditText phone;
    private EditText ID;
    private Spinner hospital;
    private Spinner time;
    private Spinner keshi;
    private List<Registration> data;
    private Button guahao;
    private String id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guahao);
        Intent i = getIntent();
        id = i.getStringExtra("id");

        init();

        guahao = (Button)findViewById(R.id.button);
        guahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("info",hospital.getSelectedItem().toString());
                AlertDialog alert=new AlertDialog.Builder(RegistrationActivity.this)
                        .create();
                alert.setIcon(R.drawable.xingming);
                alert.setTitle("确认订单？");
                alert.setMessage("是否确认订单信息并预约挂号？");

                alert.setButton(DialogInterface.BUTTON_NEGATIVE,"否",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE,"是",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendData();
                    }
                });
                alert.show();
            }
        });
    }

    private void init(){
        hospital = (Spinner)findViewById(R.id.spinnerhos);
        keshi = (Spinner)findViewById(R.id.spinnerkeshi);
        time = (Spinner)findViewById(R.id.spinnershijian);
        name = (EditText)findViewById(R.id.editTextxingming);
        phone = (EditText)findViewById(R.id.editTextdianhua);
        ID = (EditText)findViewById(R.id.editTextshenfenzheng);
    }

    private void sendData(){
        final Map<String,String> newInfo = new HashMap<String,String>();
        newInfo.put("userID",id);
        newInfo.put("operation", "send");
        newInfo.put("type", "挂号");
        newInfo.put("hospital", hospital.getSelectedItem().toString());
        System.out.println("11111111111" + hospital.getSelectedItem().toString());
        newInfo.put("section", keshi.getSelectedItem().toString());
        System.out.println("11111111111" + keshi.getSelectedItem().toString());
        newInfo.put("time", time.getSelectedItem().toString());
        newInfo.put("userName", "123");
        newInfo.put("IDCard ","123123123");
        newInfo.put("phone", "21312312");

        new Thread() {
            @Override
            public void run() {
                try {
                    String result = new HttpUtil().post(CommonUrl.Registration, newInfo);
                    if(result != null)
                        Log.i("Registration reply", result.toString());
                    else
                        Log.i("Registration send", "f");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
