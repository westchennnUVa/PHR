package forum3;

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
 * Created by 吴俊达 on 2016/3/11.
 */
public class ForumAdapter extends BaseAdapter {

    //用于加载列表项布局
    private LayoutInflater inflater ;
    //列表数据
    //**************************************
    //如需要更变，需要修改此List数据类型
    //**************************************
    private List<Question> data;

    //**************************************
    //如需要更变，需要修改此List数据类型
    //**************************************
    public ForumAdapter(Context context, List<Question> data){

        this.inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Question getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //**************************************
        //如需要更变，需要修改此接收数据数据类型
        //**************************************
        Question question = data.get(position);
        ViewHolder viewHolder;

        //当当前列表项不存在时，生成一个列表项
        if(convertView == null){
            viewHolder = new ViewHolder();
            //**************************************
            //如需要更变，需要修改此列表项布局
            //**************************************
            convertView = inflater.inflate(R.layout.layout_news, null);
            //**************************************
            //如需要更变，需要修改此控件ID
            //**************************************
            viewHolder.head = (ImageView)convertView.findViewById(R.id.head);
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);
            viewHolder.date = (TextView)convertView.findViewById(R.id.date);
            viewHolder.title = (TextView)convertView.findViewById(R.id.title);
            viewHolder.content = (TextView)convertView.findViewById(R.id.content);
            viewHolder.commentNum = (TextView)convertView.findViewById(R.id.commentNum);
            convertView.setTag(viewHolder);
            //当列表项已存在，直接读取convertView
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        //**************************************
        //如需要更变，需要修改此数据类型方法
        //**************************************

        viewHolder.head.setImageResource(question.getHead());
        viewHolder.name.setText(question.getName());
        viewHolder.date.setText(question.getDate());
        viewHolder.title.setText(question.getTitle());
        viewHolder.content.setText(question.getContent());
        viewHolder.commentNum.setText("评论数(" + question.getCommentNum() + ")");
        return convertView;
    }
}

//**************************************
//如需要更变，需要修改此ViewHolder数据类型
//**************************************
//用于封装列表项数据的控件数据
class ViewHolder{
    ImageView head;
    TextView name;
    TextView date;
    TextView title;
    TextView content;
    TextView commentNum;
}