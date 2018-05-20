package fengchuang;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.westchen.phr.R;

import java.util.List;

import adapter.Adapter_registration_orders;
import adapter.Data_Manager3;
import adapter.Registration;

public class My_Orders extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private ListView list;
    private List<Registration> registration_l;
    private Data_Manager3 data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示actionbar的返回键
       /* ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
        setContentView(R.layout.activity_my__orders);

        list = (ListView) findViewById(R.id.listView3);
        list.setOnItemClickListener(this);
        data = new Data_Manager3();
        registration_l = data.getData();
        Log.i("data---------------", data.getData().toString());

        Adapter_registration_orders adapter = new Adapter_registration_orders(this,registration_l);
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my__orders, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
