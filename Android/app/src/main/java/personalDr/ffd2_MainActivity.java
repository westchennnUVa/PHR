package personalDr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.westchen.phr.R;

public class ffd2_MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickOfRecent_Record(View source)
    {
        Intent intent=new Intent(ffd2_MainActivity.this,chat_record.class);
        startActivity(intent);
    }
    public void clickOfDr_list(View source)
    {
        Intent intent=new Intent(ffd2_MainActivity.this,dr_list.class);
        startActivity(intent);
    }
    public void clickOf_all_Dr_list(View source)
    {
        Intent intent=new Intent(ffd2_MainActivity.this,all_Dr_list.class);
        startActivity(intent);
    }

}
