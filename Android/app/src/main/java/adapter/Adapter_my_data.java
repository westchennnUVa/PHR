package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.westchen.phr.R;

import java.util.List;

/**
 * Created by AnthonySSS on 2016/3/11.
 */
public class Adapter_my_data extends BaseAdapter {

    private LayoutInflater inflater;
    //列表信息
    private List<Function> function_l;

    public Adapter_my_data(Context context, List<Function> list) {
        this.function_l = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return function_l.size();
    }

    @Override
    public Object getItem(int position) {
        return function_l.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Function function = function_l.get(position);
        ViewHolder2 viewHolder;

        //当当前列表项不存在时，生成一个列表项
        if (convertView == null) {
            viewHolder = new ViewHolder2();

            convertView = inflater.inflate(R.layout.layout_adapter, null);

            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.function = (TextView) convertView.findViewById(R.id.function);
            viewHolder.function.getPaint().setFakeBoldText(true);
            convertView.setTag(viewHolder);
            //当列表项已存在，直接读取convertView
        } else {
            viewHolder = (ViewHolder2) convertView.getTag();
            viewHolder.function.getPaint().setFakeBoldText(true);
        }
        //导入资源
        viewHolder.icon.setImageResource(function.getIcon());
        viewHolder.function.setText(function.getFunction());
        return convertView;

    }

}

    class ViewHolder2{
        ImageView icon;
        TextView function;
    }
