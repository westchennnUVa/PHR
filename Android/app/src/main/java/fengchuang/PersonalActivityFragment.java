package fengchuang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.westchen.phr.MainActivity_cxh;
import com.example.westchen.phr.R;

import forum3.OnLineForumActivity;
import personalDr.ffd_MainActivity;
import tijianguahao.ExaminationActivity;


public class PersonalActivityFragment extends Fragment {

    private String s;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main, null);
        //控件绑定
        TextView textView = (TextView)view.findViewById(R.id.textView);
        ImageButton imageButton = (ImageButton)view.findViewById(R.id.imageButton);
        ImageButton imageButton3 = (ImageButton)view.findViewById(R.id.imageButton3);
        ImageButton imageButton4 = (ImageButton)view.findViewById(R.id.imageButton4);
        ImageButton imageButton5 = (ImageButton)view.findViewById(R.id.imageButton5);

        Bundle bundle = getArguments();
        s = bundle.getString("id");
        //字体颜色，加粗
        textView.setTextColor(0xffffffff);
        TextPaint tp =textView.getPaint();
        tp.setFakeBoldText(true);
        //个人应用五大功能跳转
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity_cxh.class);
                intent.putExtra("id",s);
                startActivity(intent);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExaminationActivity.class);
                intent.putExtra("id",s);
                startActivity(intent);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ffd_MainActivity.class);
                intent.putExtra("id",s);
                startActivity(intent);
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OnLineForumActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
