package com.example.westchen.phr;

/**
 * Created by WestChen on 16/3/11.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResideMenuItem extends LinearLayout {

    //子菜单控件
    private ImageView iv_icon;		//子菜单的图标
    private TextView tv_title;		//子菜单的名称

//	public ResideMenuItem(Context context) {
//		super(context);
//		initViews(context);
//	}
//
//	public ResideMenuItem(Context context, int icon, int title) {
//		super(context);
//		initViews(context);
//		iv_icon.setImageResource(icon);
//		tv_title.setText(title);
//	}
//
    /*******************************************
     *子菜单的构造函数
     *
     *功能：设置一个子菜单
     *
     *参数分析：
     *context-----一个context
     *icon--------子菜单图标
     *title-------子菜单名称
     ********************************************/
    public ResideMenuItem(Context context, int icon, String title) {
        super(context);
        initViews(context);					//初始化
        iv_icon.setImageResource(icon);		//设置子菜单图标
        tv_title.setText(title);			//设置子菜单名称
    }

    //初始化所有控件
    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.residemenu_item, this);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    //设置子菜单图标
    public void setIcon(int icon) {
        iv_icon.setImageResource(icon);
    }
    //
//	public void setColor(int colors) {
//		tv_title.setTextColor(colors);
//	
//
//	//设置子菜单的名称
//	public void setTitle(int title) {
//		tv_title.setText(title);
//	}
//
    //设置子菜单的名称
    public void setTitle(String title) {
        tv_title.setText(title);
    }
}
