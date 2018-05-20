package fengchuang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.westchen.phr.R;

import java.util.List;

import adapter.Data_Manager;
import adapter.Function;
import adapter.Adapter_my_data;


public class MyDataFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView list;
    private List<Function> function_l;
    private Data_Manager data;

    private TextView textView7;
    private TextView textView8;
    private Button Button_personal;
    private Button Button_healthy_community;
    private Button Button_home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my__data, null);

        list = (ListView) view.findViewById(R.id.listView);
        list.setOnItemClickListener(this);
        data = new Data_Manager();
        function_l = data.getData();
        Log.i("data---------------", data.getData().toString());

        Adapter_my_data adapter = new Adapter_my_data(getActivity(), function_l);
        list.setAdapter(adapter);
        //控件绑定
        textView7 = (TextView) view.findViewById(R.id.textView7);
        textView8 = (TextView) view.findViewById(R.id.textView8);
        Button_personal = (Button) view.findViewById(R.id.button_personal);
        Button_healthy_community = (Button) view.findViewById(R.id.button_healthy_community);
        Button_home = (Button) view.findViewById(R.id.button_home);

        //字体加粗与颜色设置
        textView7.setTextColor(0xffffffff);
        TextPaint tp2 = textView7.getPaint();
        tp2.setFakeBoldText(true);

        textView8.setTextColor(0xffffff00);
        TextPaint tp3 = textView8.getPaint();
        tp3.setFakeBoldText(true);

        //下方选择栏跳转
        Button_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonalActivityFragment.class);
                startActivity(intent);
            }
        });

        Button_healthy_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthyCommunityFragment.class);
                startActivity(intent);
            }
        });

        Button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }


    //五大子功能跳转
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case(0):
                Intent intent0 = new Intent(getActivity(),My_Orders.class);
                startActivity(intent0);
                break;
            case(1):
                Intent intent1 = new Intent(getActivity(),My_Balance.class);
                startActivity(intent1);
                break;
            case(2):
                Intent intent2 = new Intent(getActivity(),My_Comment.class);
                startActivity(intent2);
                break;
            case(3):
                Intent intent3 = new Intent(getActivity(),Personal_Data.class);
                startActivity(intent3);
                break;
            case(4):
                Intent intent4 = new Intent(getActivity(),Security.class);
                startActivity(intent4);
                break;
            default:
                    break;

        }
    }
}
