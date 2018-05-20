package com.example.westchen.phr;
/**
 * Created by WestChen on 16/3/6.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PHR_info_display extends AppCompatActivity {
private TextView PHR_Score_display_blank;
private Integer PHR_Score;
private Button backButton;
private ImageView PHR_Score_Medal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phr_info_display);
        backButton = (Button)findViewById(R.id.backToPHR);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentScore = new Intent(PHR_info_display.this, MainActivity_cxh.class);
                startActivity(intentScore);
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });
        Bundle bundle = this.getIntent().getExtras();
        PHR_Score = bundle.getInt("PHR_Score");
        PHR_Score_display_blank = (TextView)findViewById(R.id.PHR_score_showtable);
        PHR_Score_display_blank.setText(""+PHR_Score);
        /*PHR_Score_Medal = (ImageView)findViewById(R.id.PHR_Score_Imageview);*/
    }
}
