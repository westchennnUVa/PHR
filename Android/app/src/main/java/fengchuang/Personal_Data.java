package fengchuang;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.westchen.phr.R;


public class Personal_Data extends ActionBarActivity {
    //控件对象声明
    private ImageButton head;
    private ImageButton ok;
    private EditText age;
    private RadioGroup sexChoice;
    private RadioButton male;
    private RadioButton female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示actionbar的返回键
    /*    ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
        setContentView(R.layout.activity_personal__data);
        //控件绑定
        head =(ImageButton)findViewById(R.id.imageButton8);
        ok = (ImageButton)findViewById(R.id.imageButton9);
        age = (EditText)findViewById(R.id.editText);
        male =(RadioButton)findViewById(R.id.maleButton);
        female =(RadioButton)findViewById(R.id.femaleButton);
        sexChoice =(RadioGroup)findViewById(R.id.sex_choice);
        //radioButton监听，改变性别
        sexChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(checkedId==male.getId()){

                }else if(checkedId==female.getId()){

                }
            }
        });
        //点击ok后保存数据
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new Thread(){

                    @Override
                    public void run(){
                        try {
                            //调用new HttpUtil().post(CommonUrl.Logon, paprams);返回的是JSON字符串
                            //参数左边是URL，从CommonUrl提取，右边是Map<String,String>,post的参数集
                            /*Map<String,String> paprams = new HashMap<String,String>();
                            paprams.put("age",age.getText().toString());
                            paprams.put("sex","6男");

                            String result = new HttpUtil().post(CommonUrl.HEALTH, paprams);
                            Message msg = new Message();
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();*/
                //
                Toast.makeText(Personal_Data.this,"数据已保存并上传",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal__data, menu);
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
