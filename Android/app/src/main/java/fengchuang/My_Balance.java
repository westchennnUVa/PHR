package fengchuang;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.westchen.phr.R;


public class My_Balance extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示actionbar的返回键
       /* ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
        setContentView(R.layout.activity_my__balance);

        TextView balance_num = (TextView)findViewById(R.id.textView9);
        ImageButton deposit = (ImageButton)findViewById(R.id.imageButton6);
        ImageButton consumption_record = (ImageButton)findViewById(R.id.imageButton7);
        //加粗，获得并余额值
        balance_num.getPaint().setFakeBoldText(true);
        //balance_num.setText();

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        consumption_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my__balance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_settings:
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
