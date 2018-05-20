package personalDr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.westchen.phr.R;

import mating.BaseFragment;

public class ffd_MainActivity extends Activity {
public static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ffd_activity_main);
        Intent i = getIntent();
        id = i.getStringExtra("id");
    }
    public void clickOfRecent_Record(View source)
    {
        Intent intent=new Intent(ffd_MainActivity.this,chat_record.class);
        startActivity(intent);
    }
    public void clickOfDr_list(View source)
    {
        Intent intent=new Intent(ffd_MainActivity.this,dr_list.class);
        startActivity(intent);
    }
    public void clickOf_all_Dr_list(View source)
    {
        Intent intent=new Intent(ffd_MainActivity.this,all_Dr_list.class);
        startActivity(intent);
    }
    public void myreturn(View source)
    {
        Intent myintent=new Intent(ffd_MainActivity.this, BaseFragment.class);
        startActivity(myintent);
    }
}
