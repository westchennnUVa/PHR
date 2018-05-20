package mating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.westchen.phr.R;

public class RegisterActivity extends Activity {

    private ImageView yonghu;
    private ImageView yisheng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        yonghu = (ImageView)findViewById(R.id.crowd);
        yisheng = (ImageView)findViewById(R.id.doctor);

        yonghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RegisterActivity.this,NormalUserRegister.class);
                startActivity(intent1);
            }
        });

        yisheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(RegisterActivity.this,DoctorRegister.class);
                startActivity(intent2);
            }
        });
    }
}
