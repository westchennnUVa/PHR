package mating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.westchen.phr.R;

import item.SportItem1;
import item.SportItem2;
import item.SportItem3;
import item.SportItem4;


public class Sport extends AppCompatActivity {

    private ImageView s1;
    private ImageView s2;
    private ImageView s3;
    private ImageView s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        s1 = (ImageView)findViewById(R.id.yundong1);
        s2 = (ImageView)findViewById(R.id.yundong2);
        s3 = (ImageView)findViewById(R.id.yundong3);
        s4 = (ImageView)findViewById(R.id.yundong4);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sport.this, SportItem1.class);
                startActivity(i);
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sport.this, SportItem2.class);
                startActivity(i);
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sport.this, SportItem3.class);
                startActivity(i);
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sport.this, SportItem4.class);
                startActivity(i);
            }
        });
    }
}
