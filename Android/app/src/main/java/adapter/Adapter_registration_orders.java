package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.westchen.phr.R;

import java.util.List;

/**
 * Created by AnthonySSS on 2016/3/13.
 */
public class Adapter_registration_orders extends  BaseAdapter {
    private LayoutInflater inflater;
    //列表信息
    private List<Registration> registration_l;

    public Adapter_registration_orders(Context context, List<Registration> list) {
        this.registration_l = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return registration_l.size();
    }

    @Override
    public Object getItem(int position) {
        return registration_l.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Registration registration = registration_l.get(position);
        ViewHolder3 viewHolder;

        //当当前列表项不存在时，生成一个列表项
        if (convertView == null) {
            viewHolder = new ViewHolder3();

            convertView = inflater.inflate(R.layout.layout_adapter_registration_orders, null);

            viewHolder.hospital = (TextView) convertView.findViewById(R.id.hospital_name);
            viewHolder.hospital.getPaint().setFakeBoldText(true);
            viewHolder.department =(TextView)convertView.findViewById(R.id.department_name);
            viewHolder.price =(TextView)convertView.findViewById(R.id.registration_price);
            viewHolder.time = (TextView) convertView.findViewById(R.id.registration_time);
            viewHolder.type = (TextView) convertView.findViewById(R.id.registration_type);
            convertView.setTag(viewHolder);
            //当列表项已存在，直接读取convertView
        } else {
            viewHolder = (ViewHolder3) convertView.getTag();
            viewHolder.hospital.getPaint().setFakeBoldText(true);
        }
        //导入资源
        viewHolder.hospital.setText(registration.getHospital());
        viewHolder.time.setText(registration.getTime());
        viewHolder.department.setText(registration.getDepartment());
        viewHolder.type.setText(registration.getType());
        viewHolder.price.setText(registration.getPrice());
        return convertView;
    }
}

class ViewHolder3{
    TextView hospital;
    TextView department;
    TextView price;
    TextView type;
    TextView time;
}