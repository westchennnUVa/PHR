package adapter;

import com.example.westchen.phr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnthonySSS on 2016/3/11.
 */
public class Data_Manager {

    public Data_Manager(){}

    public List<Function> getData(){

        Function function1 = new Function(R.drawable.fc_my_orders,"我的订单");
        Function function2 = new Function(R.drawable.fc_my_balance,"我的余额");
        Function function3 = new Function(R.drawable.fc_my_comment,"我的评论");
        Function function4 = new Function(R.drawable.fc_my_data,"个人资料");
        Function function5 = new Function(R.drawable.fc_my_security,"安全管理");
        List<Function> data =new ArrayList<Function>();
        data.add(function1);
        data.add(function2);
        data.add(function3);
        data.add(function4);
        data.add(function5);
        return data;

    }
}
