package treatmentRecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.westchen.phr.R;


/**
 * Created by 吴俊达 on 2016/3/19.
 */
public class TreatmentInfoActivity extends Activity {

    private Bundle bundle;
    private Intent intent;
    private Treatment thisTreatment;

    private TextView userName;
    private TextView time;
    private TextView sex;
    private TextView age;
    private TextView doctorName;
    private TextView section;
    private TextView hospital;
    private TextView doctorPhone;
    private TextView kind;
    private TextView situation;
    private TextView method;
    private String rid,rage,rrealName,rgender;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_treatment);
        Intent i = getIntent();
        rid = i.getStringExtra("id");
        rage = i.getStringExtra("age");
        rrealName = i.getStringExtra("realName");
        rgender = i.getStringExtra("sex");

        init();
    }

    private void init(){
        userName = (TextView)findViewById(R.id.userName);
        time = (TextView)findViewById(R.id.time);
        sex = (TextView)findViewById(R.id.sex);
        age = (TextView)findViewById(R.id.age);
        doctorName = (TextView)findViewById(R.id.doctorName);
        hospital = (TextView)findViewById(R.id.hospital);
        doctorPhone = (TextView)findViewById(R.id.doctorPhone);
        section = (TextView)findViewById(R.id.section);
        kind = (TextView)findViewById(R.id.kind);
        situation = (TextView)findViewById(R.id.situation);
        method = (TextView)findViewById(R.id.method);

        intent = getIntent();
        bundle = intent.getExtras();

        thisTreatment = (Treatment)bundle.get("treatment");

        userName.setText(rrealName);//添加用户name
        time.setText(thisTreatment.getTime());
        sex.setText(rgender);//添加用户性别
        age.setText(rage);//添加用户年龄
        doctorName.setText(thisTreatment.getDoctorName());
        hospital.setText(thisTreatment.getHospital());
        doctorPhone.setText(thisTreatment.getDoctorPhone());
        section.setText(thisTreatment.getSection());
        kind.setText(thisTreatment.getKind());
        situation.setText(thisTreatment.getSituation());
        method.setText(thisTreatment.getMethod());

    }
}
