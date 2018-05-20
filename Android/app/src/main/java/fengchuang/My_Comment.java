package fengchuang;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import adapter.Adapter_my_comment;
import adapter.Comment;
import adapter.Data_Manager2;

public class My_Comment extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private ListView list;
    private List<Comment> comment_l;
    private Data_Manager2 data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示actionbar的返回键
  /*      ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
        setContentView(R.layout.activity_my__comment);

        list = (ListView) findViewById(R.id.listView2);
        list.setOnItemClickListener(this);
        data = new Data_Manager2();
        comment_l = data.getData();
        Log.i("data---------------", data.getData().toString());

        Adapter_my_comment adapter = new Adapter_my_comment(this,comment_l);
        list.setAdapter(adapter);
        //控件绑定
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my__comment, menu);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(My_Comment.this);
        builder.setTitle("内容");
        builder.setMessage("saddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
       // builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("进入主题" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNeutralButton("修改回复",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }
}
