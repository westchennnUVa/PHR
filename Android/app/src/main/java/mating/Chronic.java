package mating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.westchen.phr.R;

import item.ChronicItem1;
import item.ChronicItem2;
import item.ChronicItem3;
import item.ChronicItem4;

public class Chronic extends AppCompatActivity {

    private ImageView m1;
    private ImageView m2;
    private ImageView m3;
    private ImageView m4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronic);

        m1 = (ImageView)findViewById(R.id.manxingbing1);
        m2 = (ImageView)findViewById(R.id.manxingbing2);
        m3 = (ImageView)findViewById(R.id.manxingbing3);
        m4 = (ImageView)findViewById(R.id.manxingbing4);

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chronic.this, ChronicItem1.class);
                startActivity(i);
            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chronic.this, ChronicItem2.class);
                startActivity(i);
            }
        });
        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chronic.this, ChronicItem3.class);
                startActivity(i);
            }
        });
        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chronic.this, ChronicItem4.class);
                startActivity(i);
            }
        });
    }
}
