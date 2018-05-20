package mating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.westchen.phr.R;

import item.RegimenItem1;
import item.RegimenItem2;
import item.RegimenItem3;
import item.RegimenItem4;


public class Regimen extends AppCompatActivity {

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regimen);

        iv1 = (ImageView)findViewById(R.id.yangsheng1);
        iv2 = (ImageView)findViewById(R.id.yangsheng2);
        iv3 = (ImageView)findViewById(R.id.yangsheng3);
        iv4 = (ImageView)findViewById(R.id.yangsheng4);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Regimen.this, RegimenItem1.class);
                startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Regimen.this, RegimenItem2.class);
                startActivity(i);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Regimen.this, RegimenItem3.class);
                startActivity(i);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Regimen.this, RegimenItem4.class);
                startActivity(i);
            }
        });

    }
}
