package mating;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.westchen.phr.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import url.CommonUrl;
import util.HttpUtil;

/**
 * Created by mating on 16/3/13.
 */
public class DoctorRegister2 extends Activity {

    private EditText username;
    private EditText password;
    private EditText age;
    private EditText name;
    private EditText realname;
    private RadioButton gender;
    private EditText IDnum;
    private EditText Telnum;
    private Button zhucewancheng;
    private ImageView touxiang;
    private Button wancheng;
    private String stouxiang;
    private String type = "医生用户";
    private String sgender;
    private String doctorId;
    private String hospital;
    private String section;
    Map<String,String> list=new HashMap<String,String>();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_REGISTER_SUCCESS:
                    Toast.makeText(DoctorRegister2.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DoctorRegister2.this, LoginActivity.class);

                    handler.sendEmptyMessage(HANDLER_REGISTER_SUCCESS);
                    startActivity(intent);
                    finish();
                    break;
                case HANDLER_REGISTER_ERROR:
                    Toast.makeText(DoctorRegister2.this, "注册失败" + msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public static final int HANDLER_REGISTER_SUCCESS = 1;
    public static final int HANDLER_REGISTER_ERROR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_register);
        setViews();

        Intent intent = getIntent();
        doctorId = intent.getStringExtra("doctorID");
        hospital = intent.getStringExtra("hospital");
        section = intent.getStringExtra("section");
        //getContent();
    }
    public void setViews() {
        username = (EditText) findViewById(R.id.usernameet);
        password = (EditText) findViewById(R.id.passwordet);
        age = (EditText) findViewById(R.id.ageet);
        name = (EditText) findViewById(R.id.nameet);
        realname = (EditText) findViewById(R.id.realnameet);
        RadioGroup gendergp = (RadioGroup) findViewById(R.id.genderrg);
        gender = (RadioButton) findViewById(gendergp.getCheckedRadioButtonId());
        if(gendergp.getId()==R.id.male)
        {
            sgender="male";
        }else {
            sgender="female";
        }
        IDnum = (EditText) findViewById(R.id.Idnum);
        Telnum = (EditText) findViewById(R.id.Telnum);
        wancheng = (Button) findViewById(R.id.wancheng);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        touxiang.setImageResource(R.drawable.doctortouxiang);

        zhucewancheng = (Button) findViewById(R.id.wancheng);

        touxiang.setDrawingCacheEnabled(true);
        Bitmap btouxiang = touxiang.getDrawingCache();
//        stouxiang = new BitmapUtils().bitmaptoString(btouxiang);

        wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            zhuce();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("error", "登录失败");
                        }
                    }
                }.start();
            }
        });
    }
    private void zhuce() throws IOException, JSONException {
        list.put("operation", "Enroll_Doctor");
        list.put("userName", username.getText().toString());
        list.put("password", password.getText().toString());
        list.put("age", age.getText().toString());
        list.put("name" ,name.getText().toString());
        list.put("realName", realname.getText().toString());
        list.put("sex", sgender);
        list.put("personalID", IDnum.getText().toString());
        list.put("phone", Telnum.getText().toString());
        list.put("head", stouxiang);
        list.put("type", type);
        list.put("doctorID", doctorId);
        list.put("hospital", hospital);
        list.put("section", section);

        Log.i("test", username.getText().toString());

        String result = new HttpUtil().post(CommonUrl.Login, list);
        if(!result.equals("-1")){
            Intent intent1 = new Intent(DoctorRegister2.this, LoginActivity.class);
            startActivity(intent1);
            finish();
        }else {
            Toast.makeText(this,"注册失败",Toast.LENGTH_LONG);
        }
    }
}
