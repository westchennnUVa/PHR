package com.example.westchen.phr;
/**
 * Created by WestChen on 16/3/6.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fengchuang.My_Balance;
import fengchuang.Personal_Data;
import mating.LoginActivity;
import personalDr.ffd_MainActivity;
import url.CommonUrl;
import util.HttpUtil;


public class MainActivity_cxh extends Activity implements OnClickListener {
    private Toast mToast;
    private Button mButton,postButton,ceHuaButton;
    //private TextView post_check;
    private ClearEditText realnameet,ageet,heightet,weightet,xueyaet,feihuolianget,shilizuoet,shiliyouet,xinlvet,tiwenet,xuehongdanbaiet,xuexiaobanet,niaohonget,niaolianget,yaoweiet,xiongweiet,tunweiet;
    private Spinner genderSpinner,xuexingSpinner;
    private List<String> gender_data,xuexing_data;
    private ArrayAdapter<String> gender_adapter,xuexing_adapter;
    private String genderSaveData,xuexingSaveData;
    private String Sheight_data;
    private String login_id;
    private String str1;
    private String str2;

    //计算时候和传值时候用到的变量
    private String Sweight_data,Sxueya_data,Sfeihuoliang_data,Sshilizuo_data,Sshiliyou_data,Sxinlv_data,Stiwen_data,Sxuehongdanbai_data,Sxuexiaoban_data,Sniaohong_data,Sniaoliang_data,Syaowei_data,Sxiongwei_data,Stunwei_data;
    private int height_data,weight_data,xueya_data,shuzhangya_data,feihuoliang_data,shilizuo_data,shiliyou_data,xinlv_data,tiwen_data,xuehongdanbai_data,xuexiaoban_data,niaohong_data,niaoliang_data,yaowei_data,xiongwei_data,tunwei_data;
    private int PHR_data;
    // 菜单与子菜单组件
    private ResideMenu resideMenu; 			// 菜单组件
    private ResideMenuItem itemUser; 		// 子菜单组件“我的账号”
    private ResideMenuItem itemChongzhi; 	// 子菜单组件“充值会员”
    private ResideMenuItem itemMyletter; 	// 子菜单组件“站内信”
    private ResideMenuItem itemMyshoucang; // 子菜单组件“我的收藏”
    private ResideMenuItem itemQuit;        // 子菜单组件“退出登录”
    private ImageView user_head; 			// 点击用户头像登录


    //性别spinner
    private String[] gender = { "男", "女"};
    private String sgender;

    // 血型spinner
    private String[] xuexing = { "A", "B","AB","O"};
    private String sxuexing;

    //往服务器传输局用到的变量
    private String jsontest;
    private Handler handler,handler_post;
    private JSONArray PHR_Server_data;
    private String Server_name,Server_age,Server_sex,Server_height,Server_weight,Server_blood_pressure,Server_vital_capacity,Server_vision_left;
    private String Server_vision_right,Server_heart_rate,Server_temperature,Server_hemoglobin,Server_platelet,Server_urine;
    private String Server_urine_red_blood_cell,Server_blood_type,Server_Measurements_1,Server_Measurements_2,Server_Measurements_3,Server_score;
    private JSONObject test;

    private Drawable phrMainDrawable;

    public static final int SUCCESS=1;
    public static final int ERROR=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_cxh);
   /*     phrMainDrawable = this.getResources().getDrawable(R.drawable.phr_score_background);
        phrMainDrawable.setAlpha(50);*/
        Intent intentfirst = getIntent();
        login_id = intentfirst.getStringExtra("id");
        //System.out.print("id=" + login_id);

 /*侧滑菜单*/
        resideMenu = new ResideMenu(this);                // 侧边菜单new出来
        //resideMenu.setVisibility(View.GONE);
        resideMenu.setBackground(R.drawable.menu_bg);    // 设置侧边菜单背景
        resideMenu.attachToActivity(this);                // 侧边菜单绑定当前Activity
        resideMenu.setScaleValue(0.8f);                    // 设置主页的缩放比例(0.1~1f)
        user_head = (ImageView) findViewById(R.id.menu_user_head);    //初始化用户头像
        user_head.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_cxh.this, ffd_MainActivity.class);
                startActivity(intent);
            }
        });

// 创建子菜单（并设置相应子菜单的图标与文字）,并为其添加点击事件
        itemUser = new ResideMenuItem(this, R.drawable.left_fragment_home_icon_normal, "我的账户");
        itemUser.setOnClickListener(this);
        itemChongzhi = new ResideMenuItem(this, R.drawable.left_fragment_near_icon_normal, "充值会员");
        itemChongzhi.setOnClickListener(this);
        itemMyletter = new ResideMenuItem(this, R.drawable.left_fragment_mine_icon_normal, "站内信");
        itemMyletter.setOnClickListener(this);
        itemMyshoucang = new ResideMenuItem(this, R.drawable.left_fragment_settings_icon_normal, "我的收藏");
        itemMyshoucang.setOnClickListener(this);
        itemQuit = new ResideMenuItem(this, R.drawable.left_fragment_quit_icon_normal, "退出登录");
        itemQuit.setOnClickListener(this);
        //计算按钮的监听和获取
        mButton = (Button) findViewById(R.id.PHR_conmmit);
        mButton.setOnClickListener(this);
        postButton = (Button) findViewById(R.id.post_button);
        postButton.setOnClickListener(this);

        // 添加子菜单（通过常量来控制子菜单的添加位置）
        resideMenu.addMenuItem1(itemUser, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem1(itemChongzhi, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem1(itemMyletter, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem2(itemMyshoucang, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem2(itemQuit, ResideMenu.DIRECTION_LEFT);

        //Edittext的对应
        realnameet = (ClearEditText) findViewById(R.id.realnameet);
        ageet = (ClearEditText) findViewById(R.id.ageet);
        heightet = (ClearEditText) findViewById(R.id.heightet);
        weightet = (ClearEditText) findViewById(R.id.weightet);
        xueyaet = (ClearEditText) findViewById(R.id.xueyaet);
        /*shuzhangyaet = (ClearEditText) findViewById(R.id.shuzhangyaet);*/
        feihuolianget = (ClearEditText) findViewById(R.id.feihuolianget);
        shilizuoet = (ClearEditText) findViewById(R.id.shilizuoet);
        shiliyouet = (ClearEditText) findViewById(R.id.shiliyouet);
        xinlvet = (ClearEditText) findViewById(R.id.xinlvet);
        tiwenet = (ClearEditText) findViewById(R.id.tiwenet);
        xuehongdanbaiet = (ClearEditText) findViewById(R.id.xuehongdanbaiet);
        xuexiaobanet = (ClearEditText) findViewById(R.id.xuexiaobanet);
        niaohonget = (ClearEditText) findViewById(R.id.niaohonget);
        niaolianget = (ClearEditText) findViewById(R.id.niaolianget);
        yaoweiet = (ClearEditText) findViewById(R.id.yaoweiet);
        xiongweiet = (ClearEditText) findViewById(R.id.xiongweiet);
        tunweiet = (ClearEditText) findViewById(R.id.tunweiet);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        xuexingSpinner = (Spinner) findViewById(R.id.xuexingSpinner);

        //性别那里的spinner的编写
        gender_data = new ArrayList<String>();
        gender_data.add("男");
        gender_data.add("女");
        gender_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender_data);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(gender_adapter);
        //性别spinner完成*/
        //血型spinner
        xuexing_data = new ArrayList<String>();
        xuexing_data.add("A");
        xuexing_data.add("B");
        xuexing_data.add("AB");
        xuexing_data.add("O");
        xuexing_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, xuexing_data);
        xuexing_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xuexingSpinner.setAdapter(xuexing_adapter);

        str1 = (String) genderSpinner.getSelectedItem();
        str2 = (String) xuexingSpinner.getSelectedItem();
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str1 = (String) genderSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        xuexingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str2 = (String) xuexingSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Log.i("TAG", str1);
        //Log.i("TAG",str2);
        initialJudge();

        //血型spinner完成

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == SUCCESS){
                    setText();
                    //Toast.makeText(MainActivity_cxh.this,"成功",Toast.LENGTH_LONG).show();
                }
                else if(msg.what == ERROR){
                   //Toast.makeText(MainActivity_cxh.this,"失败",Toast.LENGTH_LONG).show();
                    //setNullText();
                }
            }
        };

    }

    private void setText(){
        realnameet.setText(Server_name);
        ageet.setText(Server_age);
        heightet.setText(Server_height);
        weightet.setText(Server_weight);
        setSpinnerItemSelectedByValue(genderSpinner,str1);
        setSpinnerItemSelectedByValue(xuexingSpinner, str2);
        xueyaet.setText(Server_blood_pressure);
        feihuolianget.setText(Server_vital_capacity);
        shilizuoet.setText(Server_vision_left);
        shiliyouet.setText(Server_vision_right);
        xinlvet.setText(Server_heart_rate);
        tiwenet.setText(Server_temperature);
        xuehongdanbaiet.setText(Server_hemoglobin);
        xuexiaobanet.setText(Server_platelet);
        niaohonget.setText(Server_urine_red_blood_cell);
        niaolianget.setText(Server_urine);
        yaoweiet.setText(Server_Measurements_1);
        xiongweiet.setText(Server_Measurements_2);
        tunweiet.setText(Server_Measurements_3);
    }
    //各种按钮
    @Override
     public void onClick(View v){
        if (v == postButton){
            saveDate();
        }
        if (v == mButton) {
            if (TextUtils.isEmpty(realnameet.getText())) {
                //设置晃动
                realnameet.setShakeAnimation();
                //设置提示
                showToast("姓名不能为空！");
                return;
            }
            if (TextUtils.isEmpty(ageet.getText())) {
                ageet.setShakeAnimation();
                showToast("年龄不能为空！");
                return;
            }
            if (TextUtils.isEmpty(heightet.getText())) {
                heightet.setShakeAnimation();
                showToast("身高不能为空！");
                return;
            }
            if (TextUtils.isEmpty(weightet.getText())) {
                heightet.setShakeAnimation();
                showToast("体重不能为空！");
                return;
            }
            if (TextUtils.isEmpty(xueyaet.getText())) {
                heightet.setShakeAnimation();
                showToast("血压-收缩压不能为空！");
                return;
            }
            if (TextUtils.isEmpty(feihuolianget.getText())) {
                heightet.setShakeAnimation();
                showToast("肺活量不能为空！");
                return;
            }
            if (TextUtils.isEmpty(shilizuoet.getText())) {
                heightet.setShakeAnimation();
                showToast("视力-左眼不能为空！");
                return;
            }
            if (TextUtils.isEmpty(shiliyouet.getText())) {
                heightet.setShakeAnimation();
                showToast("视力-右眼不能为空！");
                return;
            }
            if (TextUtils.isEmpty(xinlvet.getText())) {
                heightet.setShakeAnimation();
                showToast("心率不能为空！");
                return;
            }
            if (TextUtils.isEmpty(tiwenet.getText())) {
                heightet.setShakeAnimation();
                showToast("体温不能为空！");
                return;
            }
            if (TextUtils.isEmpty(xuehongdanbaiet.getText())) {
                heightet.setShakeAnimation();
                showToast("血红蛋白不能为空！");
                return;
            }
            if (TextUtils.isEmpty(xuexiaobanet.getText())) {
                heightet.setShakeAnimation();
                showToast("血小板计数不能为空！");
                return;
            }
            if (TextUtils.isEmpty(niaohonget.getText())) {
                heightet.setShakeAnimation();
                showToast("尿红蛋白不能为空！");
                return;
            }
            if (TextUtils.isEmpty(niaolianget.getText())) {
                heightet.setShakeAnimation();
                showToast("尿量不能为空！");
                return;
            }
            if (TextUtils.isEmpty(yaoweiet.getText())) {
                heightet.setShakeAnimation();
                showToast("腰围不能为空！");
                return;
            }
            if (TextUtils.isEmpty(xiongweiet.getText())) {
                heightet.setShakeAnimation();
                showToast("胸围不能为空！");
                return;
            }
            if (TextUtils.isEmpty(tunweiet.getText())) {
                heightet.setShakeAnimation();
                showToast("臀围不能为空！");
                return;
            } else {
                //值的获得
                Sheight_data = heightet.getText().toString();
                Sweight_data = weightet.getText().toString();
                Sxueya_data = xueyaet.getText().toString();
                Sfeihuoliang_data = feihuolianget.getText().toString();
                Sshiliyou_data = shiliyouet.getText().toString();
                Sshilizuo_data = shilizuoet.getText().toString();
                Sxinlv_data = xinlvet.getText().toString();
                Stiwen_data = tiwenet.getText().toString();
                Sxuehongdanbai_data = xuehongdanbaiet.getText().toString();
                Sxuexiaoban_data = xuexiaobanet.getText().toString();
                Sniaohong_data = niaohonget.getText().toString();
                Sniaoliang_data = niaolianget.getText().toString();
                Syaowei_data = yaoweiet.getText().toString();
                Sxiongwei_data = xiongweiet.getText().toString();
                Stunwei_data = tunweiet.getText().toString();
                //edittext中数据的获取
                if (Sheight_data != null && Sheight_data.length() > 0) {
                    height_data = Integer.parseInt(Sheight_data);
                }
                if (Sweight_data != null && Sweight_data.length() > 0) {
                    weight_data = Integer.parseInt(Sweight_data);
                }
                if (Sxueya_data != null && Sxueya_data.length() > 0) {
                    xueya_data = Integer.parseInt(Sxueya_data);
                }
                if (Sfeihuoliang_data != null && Sfeihuoliang_data.length() > 0) {
                    feihuoliang_data = Integer.parseInt(Sfeihuoliang_data);
                }
                if (Sshilizuo_data != null && Sshilizuo_data.length() > 0) {
                    shilizuo_data = Integer.parseInt(Sshilizuo_data);
                }
                if (Sshiliyou_data != null && Sshiliyou_data.length() > 0) {
                    shiliyou_data = Integer.parseInt(Sshiliyou_data);
                }
                if (Sxinlv_data != null && Sxinlv_data.length() > 0) {
                    xinlv_data = Integer.parseInt(Sxinlv_data);
                }
                if (Stiwen_data != null && Stiwen_data.length() > 0) {
                    tiwen_data = Integer.parseInt(Stiwen_data);
                }
                if (Sxuehongdanbai_data != null && Sxuehongdanbai_data.length() > 0) {
                    xuehongdanbai_data = Integer.parseInt(Sxuehongdanbai_data);
                }
                if (Sxuexiaoban_data != null && Sxuexiaoban_data.length() > 0) {
                    xuexiaoban_data = Integer.parseInt(Sxuexiaoban_data);
                }
                if (Sniaohong_data != null && Sniaohong_data.length() > 0) {
                    niaohong_data = Integer.parseInt(Sniaohong_data);
                }
                if (Sniaoliang_data != null && Sniaoliang_data.length() > 0) {
                    niaoliang_data = Integer.parseInt(Sniaoliang_data);
                }
                if (Syaowei_data != null && Syaowei_data.length() > 0) {
                    yaowei_data = Integer.parseInt(Syaowei_data);
                }
                if (Sxiongwei_data != null && Sxiongwei_data.length() > 0) {
                    xiongwei_data = Integer.parseInt(Sxiongwei_data);
                }
                if (Stunwei_data != null && Stunwei_data.length() > 0) {
                    tunwei_data = Integer.parseInt(Stunwei_data);
                }
                PHR_data = height_data + weight_data + xueya_data + shuzhangya_data + feihuoliang_data + shilizuo_data + shiliyou_data + xinlv_data + tiwen_data + xuehongdanbai_data + xuexiaoban_data + niaohong_data + niaoliang_data + yaowei_data + xiongwei_data + tunwei_data;
                Intent intent = new Intent(MainActivity_cxh.this, PHR_info_display.class);
                Bundle bundle = new Bundle();
                bundle.putInt("PHR_Score", PHR_data);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
            }
        }
        else if (v == itemUser) { 									// 点击子菜单的“我的账号”按钮
            showToast("我的账号");
            Intent intent = new Intent(MainActivity_cxh.this, Personal_Data.class);
            startActivity(intent);
        } else if (v == itemChongzhi) { 								// 点击子菜单的“充值会员”按钮
            showToast("充值会员");
            Intent intent = new Intent(MainActivity_cxh.this, My_Balance.class);
            startActivity(intent);
            /*realnameet.setText("111");*/
        } else if (v == itemMyletter) { 									// 点击子菜单的“站内信”按钮
            showToast("站内信");
        } else if (v == itemQuit) { 									// 点击子菜单的“退出登录”按钮
            showToast("退出登录");
            Intent intent = new Intent(MainActivity_cxh.this, LoginActivity.class);
            startActivity(intent);
        }else if (v == itemMyshoucang) { 								// 点击子菜单的“我的收藏”按钮
            showToast("我的收藏");
        }
        /*else if (v == user_head) { 								// 点击子菜单的“我的收藏”按钮
            showToast("跳转到登录界面");
            Intent intent2 = new Intent(MainActivity_cxh.this, ffd_MainActivity.class);
            startActivity(intent2);
        }*/
    }

    //弹出弹框提示
    private void showToast(String msg) {
        if (mToast == null){
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Spinner根据值的动态设定
    public void setSpinnerItemSelectedByValue(Spinner spinner,String value){
        SpinnerAdapter myAdapter= spinner.getAdapter(); //得到SpinnerAdapter对象
        int k= myAdapter.getCount();
        for(int i=0;i<k;i++){
            if(value.equals(myAdapter.getItem(i).toString())){
                spinner.setSelection(i,true);// 默认选中项
                break;
            }
        }
    }
    public void initialJudge(){
        //开始判断时候的handler msg.what == 0
        new Thread(){
            @Override
            public void run(){
                try {
                    //调用new HttpUtil().post(CommonUrl.Logon, paprams);返回的是JSON字符串
                    //参数左边是URL，从CommonUrl提取，右边是Map<String,String>,post的参数集
                    //开始post传id和操作 判断是否曾经有过数据，来进行以下操作决定展示旧数据还是空
                    Map<String,String> paprams = new HashMap<String,String>();
                    paprams.put("userID",login_id);
                    paprams.put("operation","get");
                    String result = new HttpUtil().post(CommonUrl.PHR, paprams);

                    Log.i("result",result);
                    if(!(result.equals("-1"))){

                        test = new JSONObject(result);
                        Server_name = (String) test.get("name");
                        Server_age = (String) test.get("age");
                        Server_sex = (String) test.get("sex");
                        Server_height = (String) test.get("height");
                        Server_blood_type = (String) test.get("blood_type");
                        Server_platelet = (String) test.get("platelet");
                        Server_temperature = (String) test.get("temperature");
                        Server_vital_capacity = (String) test.get("vital_capacity");
                        Server_Measurements_1 = (String) test.get("Measurements_1");
                        Server_Measurements_2 = (String) test.get("Measurements_2");
                        Server_Measurements_3 = (String) test.get("Measurements_3");
                        Server_score = (String) test.get("score");
                        Server_hemoglobin = (String) test.get("hemoglobin");
                        Server_blood_pressure = (String) test.get("blood_pressure");
                        Server_urine_red_blood_cell = (String) test.get("urine_red_blood_cell");
                        Server_heart_rate = (String) test.get("heart_rate");
                        Server_weight = (String) test.get("weight");
                        Server_vision_right = (String) test.get("vision_right");
                        Server_vision_left = (String) test.get("vision_left");
                        Server_urine = (String) test.get("urine");
                        /*showToast("以读取以前数据");*/
                        //Toast.makeText(MainActivity_cxh.this,"以读取以前数据！",Toast.LENGTH_LONG).show();
                        Log.i("height",Server_height);

                        handler.sendEmptyMessage(SUCCESS);
                    }
                    else
                    {
                          setText();
                        //handler.sendEmptyMessage(ERROR);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void saveDate(){
        //save时候的handler msg.what == 1
        new Thread() {
            @Override
            public void run() {
                try {
                    Map<String,String> saveData = new HashMap<String, String>();
                    Map<String,String> saveData2 = new HashMap<String, String>();
                    saveData.put("name",realnameet.getText().toString());
                    saveData.put("age",ageet.getText().toString());
                    saveData.put("sex",str1);
                    saveData.put("height",heightet.getText().toString());
                    saveData.put("blood_type",str2);
                    saveData.put("platelet",xuexiaobanet.getText().toString());
                    saveData.put("temperature", tiwenet.getText().toString());
                    saveData.put("vital_capacity",feihuolianget.getText().toString());
                    saveData.put("Measurements_1", yaoweiet.getText().toString());
                    saveData.put("Measurements_2",xiongweiet.getText().toString());
                    saveData.put("Measurements_3",tunweiet.getText().toString());
                    saveData.put("score","1");
                    saveData.put("hemoglobin",xuehongdanbaiet.getText().toString());
                    saveData.put("blood_pressure",xueyaet.getText().toString());
                    saveData.put("urine_red_blood_cell",niaohonget.getText().toString());
                    saveData.put("heart_rate",xinlvet.getText().toString());
                    saveData.put("weight",weightet.getText().toString());
                    saveData.put("vision_right", shiliyouet.getText().toString());
                    saveData.put("vision_left",shilizuoet.getText().toString());
                    saveData.put("urine", niaolianget.getText().toString());
                    saveData.put("userID",login_id);
                    String dataJson = mapToJson(saveData);
                    saveData2.put("operation","save");
                    saveData2.put("userID",login_id);
                    saveData2.put("data",dataJson);
                    String saveFeedBack = new HttpUtil().post(CommonUrl.PHR, saveData2);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    /*showToast("保存成功！");*/
                    Toast.makeText(MainActivity_cxh.this,"保存成功！",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    //一般Map<String,Object>集合转化为JSONObject字符串
    public String mapToJson(Map<String,String> map) throws JSONException {

        if (map == null)
            System.out.println("InfoToJson:数组为空");
        else {
            JSONObject json = new JSONObject();

            for(Map.Entry<String, String> entry : map.entrySet())
                json.put(entry.getKey(), entry.getValue());

            return json.toString();
        }
        return null;
    }
}
