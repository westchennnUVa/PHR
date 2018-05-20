package mating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.westchen.phr.R;

import item.DrugItem1;
import item.DrugItem2;
import item.DrugItem3;
import item.DrugItem4;

public class Drug extends AppCompatActivity {

    private ImageView drug1;
    private ImageView drug2;
    private ImageView drug3;
    private ImageView drug4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        drug1 = (ImageView)findViewById(R.id.yaopinitem1);
        drug2 = (ImageView)findViewById(R.id.yaopinitem2);
        drug3 = (ImageView)findViewById(R.id.yaopinitem3);
        drug4 = (ImageView)findViewById(R.id.yaopinitem4);

        drug1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Drug.this, DrugItem1.class);
                startActivity(i);
            }
        });
        drug2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Drug.this, DrugItem2.class);
                startActivity(i);
            }
        });
        drug3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Drug.this, DrugItem3.class);
                startActivity(i);
            }
        });
        drug4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Drug.this, DrugItem4.class);
                startActivity(i);
            }
        });
    }
}
