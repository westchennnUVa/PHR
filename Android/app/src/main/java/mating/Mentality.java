package mating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.westchen.phr.R;

import item.MentalityItem1;
import item.MentalityItem2;
import item.MentalityItem3;
import item.MentalityItem4;

public class Mentality extends AppCompatActivity {

    private ImageView m1;
    private ImageView m2;
    private ImageView m3;
    private ImageView m4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentality);
        m1 = (ImageView)findViewById(R.id.mentality1);
        m2 = (ImageView)findViewById(R.id.mentality2);
        m3 = (ImageView)findViewById(R.id.mentality3);
        m4 = (ImageView)findViewById(R.id.mentality4);

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mentality.this, MentalityItem1.class);
                startActivity(i);
            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mentality.this, MentalityItem2.class);
                startActivity(i);
            }
        });
        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mentality.this, MentalityItem3.class);
                startActivity(i);
            }
        });
        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mentality.this, MentalityItem4.class);
                startActivity(i);
            }
        });
    }
}
