package mating;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.westchen.phr.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fengchuang.HealthyCommunityFragment;
import fengchuang.MyDataFragment;
import fengchuang.PersonalActivityFragment;
import tijianguahao.ExaminationActivity;
import url.CommonUrl;
import util.HttpUtil;

public class BaseFragment extends FragmentActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton r0;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private MyPagerAdapter adapter;
    private ArrayList<Fragment> fs;
    //private FragmentInterface mFragment;
    private String id,realName,age,sex;

    private android.app.FragmentManager manager;
    private android.app.FragmentTransaction transaction;
    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);
        setViews();
        setListener();
        scheduledExecutorService.scheduleWithFixedDelay(new getTask(), 1, 1, TimeUnit.SECONDS);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        realName = intent.getStringExtra("realName");
        age = intent.getStringExtra("age");
        sex = intent.getStringExtra("sex");


        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        PersonalActivityFragment fragment2 = new PersonalActivityFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", id);
        fragment2.setArguments(bundle2);
        HealthyCommunityFragment fragment3 = new HealthyCommunityFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("id", id);
        bundle3.putString("age",age);
        bundle3.putString("realName",realName);
        bundle3.putString("sex",sex);
        fragment3.setArguments(bundle3);

        transaction.commit();

        fs = new ArrayList<Fragment>();
        fs.add(new MainUserPageFragment());
        fs.add(fragment2);
        fs.add(fragment3);
        fs.add(new MyDataFragment());

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //mFragment.transferMessage();


        /*manager = getFragmentManager();
        transaction = manager.beginTransaction();

        PersonalActivityFragment fragment2 = new PersonalActivityFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", id);
        fragment2.setArguments(bundle2);

        transaction.commit();*/
    }

    private void setListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        r0.setChecked(true);
                        break;
                    case 1:
                        r1.setChecked(true);
                        break;
                    case 2:
                        r2.setChecked(true);
                        break;
                    case 3:
                        r3.setChecked(true);
                        break;
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    private void setViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup1);
        r0 = (RadioButton) findViewById(R.id.radio0);
        r1 = (RadioButton) findViewById(R.id.radio1);
        r2 = (RadioButton) findViewById(R.id.radio2);
        r3 = (RadioButton) findViewById(R.id.radio3);

    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.radio0:
                viewPager.setCurrentItem(0);
                break;
            case R.id.radio1:
                viewPager.setCurrentItem(1);
                break;
            case R.id.radio2:
                viewPager.setCurrentItem(2);
                break;
            case R.id.radio3:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return fs.get(position);
        }

        public int getCount() {
            return fs.size();
        }

    }

    //接受测试用
    private class getTask implements Runnable {

        @Override
        public void run() {

            try {
                //Map<String, String> paprams = new HashMap<String, String>();
                final Map<String,String> paprams = new HashMap<String,String >();
                paprams.put("operation", "get");

                //通知的id为当前用户id
                paprams.put("fromID", id);

                String result = new HttpUtil().post(CommonUrl.Registration, paprams);

                if(result == null || result.equals("")){
                    System.out.println("11111111111result为空！！！！" );
                }else{

                    System.out.println("11111111111开始接受消息！" );
                    JSONObject json = new JSONObject(result);
                    String name = (String) json.get("name");
                    String IDCard = (String) json.get("IDCard");
                    String phone = (String) json.get("phone");
                    String time = (String) json.get("time");

                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), 0,
                            new Intent(getApplicationContext(), ExaminationActivity.class), 0);
                    Notification notify2 = new Notification.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.head1)
                            .setTicker("您有新短消息，请注意查收！")
                            .setContentTitle("预约时间: " + time)// 设置在下拉status
                            .setContentText("你有新的消息")// TextView中显示的详细内容
                            .setContentIntent(pendingIntent2) // 关联PendingIntent
                            .getNotification(); // 需要注意build()是在API level
                    notify2.flags |= Notification.FLAG_AUTO_CANCEL;
                    manager.notify(1, notify2);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
/*
Bundle bundle1 = getArguments();
textView.setText(bundle1.getString("id"));*/
