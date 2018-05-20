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
 * Created by AnthonySSS on 2016/3/12.
 */
public class Adapter_my_comment extends BaseAdapter{
    private LayoutInflater inflater;
    //列表信息
    private List<Comment> comment_l;

    public Adapter_my_comment(Context context, List<Comment> list) {
        this.comment_l = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return comment_l.size();
    }

    @Override
    public Object getItem(int position) {
        return comment_l.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = comment_l.get(position);
        ViewHolder1 viewHolder;

        //当当前列表项不存在时，生成一个列表项
        if (convertView == null) {
            viewHolder = new ViewHolder1();

            convertView = inflater.inflate(R.layout.layout_adapter_my_comment, null);

            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.title.getPaint().setFakeBoldText(true);
            viewHolder.time = (TextView) convertView.findViewById(R.id.date);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
            //当列表项已存在，直接读取convertView
        } else {
            viewHolder = (ViewHolder1) convertView.getTag();
            viewHolder.title.getPaint().setFakeBoldText(true);
        }
        //导入资源
        viewHolder.title.setText(comment.getTitle());
        viewHolder.time.setText(comment.getTime());
        viewHolder.content.setText(comment.getContent());
        return convertView;
    }
}

class ViewHolder1{
    TextView title;
    TextView time;
    TextView content;
  }
