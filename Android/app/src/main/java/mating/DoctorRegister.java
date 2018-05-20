package mating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.westchen.phr.R;

public class DoctorRegister extends Activity {

    private EditText num;
    private EditText hospital;
    private EditText section;
    private ImageView next;
    private ImageView ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        num = (EditText)findViewById(R.id.zhenghaoet);
        hospital = (EditText)findViewById(R.id.hospitalnameet);
        section = (EditText)findViewById(R.id.sectionet);
        next = (ImageView)findViewById(R.id.nextstepiv);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorRegister.this,DoctorRegister2.class);
                intent.putExtra("doctorID",num.getText().toString());
                intent.putExtra("hospital",hospital.getText().toString());
                intent.putExtra("section",section.getText().toString());
                startActivity(intent);
            }
        });
    }

}
