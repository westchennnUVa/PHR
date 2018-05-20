package personalDr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.westchen.phr.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import url.CommonUrl;
import util.HttpUtil;

public class detail_of_chat extends Activity {

    ArrayList<HashMap<String,Object>> chatList=null;
    String[] from={"image","text"};
    //布局文件列表
    int[] to={R.id.chatlist_image_me,R.id.chatlist_text_me,R.id.chatlist_image_other,R.id.chatlist_text_other};
    int[] layout={R.layout.ffd_chat_listitem_me,R.layout.ffd_chat_listitem_other};
    String userQQ=null;
    /**
     * 这里两个布局文件使用了同一个id，测试一下是否管用
     * TT事实证明这回产生id的匹配异常！所以还是要分开。。
     *
     * userQQ用于接收Intent传递的qq号，进而用来调用数据库中的相关的联系人信息，这里先不讲
     * 先暂时使用一个头像
     */

    //标识是自己还是别人
    public final static int OTHER=1;
    public final static int ME=0;

    //对话框列表
    public ListView chatListView=null;
    public Button chatSendButton=null;
    public EditText editText=null;

    public String myWord;
    public String toID;
    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    protected MyChatAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ffd_all_main);
        chatList=new ArrayList<HashMap<String,Object>>();


        Intent myintent=getIntent();
        Bundle myBundle=myintent.getExtras();
        toID=(String)myBundle.get("id");
        scheduledExecutorService.scheduleWithFixedDelay(new getTask(), 1, 1, TimeUnit.SECONDS);
        //打上标签
        addTextToList("I'm Xinhao", ME);
        addTextToList("Hi\n  ^_^", OTHER);
        addTextToList("What's your name", ME);
        addTextToList("Your doctor！", OTHER);
        //绑定
        chatSendButton=(Button)findViewById(R.id.chat_bottom_sendbutton);
        editText=(EditText)findViewById(R.id.chat_bottom_edittext);
        chatListView=(ListView)findViewById(R.id.chat_list);

        adapter=new MyChatAdapter(this,chatList,layout,from,to);


        chatSendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                myWord=null;

                /**
                 * 这是一个发送消息的监听器，注意如果文本框中没有内容，那么getText()的返回值可能为
                 * null，这时调用toString()会有异常！所以这里必须在后面加上一个""隐式转换成String实例
                 * ，并且不能发送空消息。
                 */
                myWord=(editText.getText()+"").toString();
                if(myWord.length()==0)
                    return;
                editText.setText("");
                addTextToList(myWord, ME);
                /**
                 * 更新数据列表，并且通过setSelection方法使ListView始终滚动在最底端
                 */
                adapter.notifyDataSetChanged();
                chatListView.setSelection(chatList.size()-1);

                new Thread() {

                    @Override
                    public void run() {
                        //String messageStr = editText.getText().toString();
                        //handler.sendEmptyMessage(2);

///////////////////////////////////////////////////////////////////////////////////////
//发送聊天消息
                        Map<String, String> paprams = new HashMap<String, String>();
                        paprams.put("operation", "send");
                        //用户自己的id
                        paprams.put("fromID", ffd_MainActivity.id);
                        paprams.put("toID",toID);
                        paprams.put("time", "1111111");
                        paprams.put("content", myWord);

                        String result = new HttpUtil().post(CommonUrl.Chat, paprams);

///////////////////////////////////////////////////////////////////////////////////////

                        //Message msg = new Message();
                        //msg.what = 1;
                        //msg.obj = "fromID:" + user.getUserID() + "\ttoID" + toId + "\ttime:" + "2016-3-13" + "\tcontent:" + messageStr;
                    }

                }.start();

            }
        });
        chatListView.setAdapter(adapter);
    }


    private class getTask implements Runnable {

        @Override
        public void run() {

            try {
///////////////////////////////////////////////////////////////////////////////////////
//接受聊天消息
                Map<String, String> paprams = new HashMap<String, String>();
                paprams.put("operation", "getChatContent");
                paprams.put("fromID", ffd_MainActivity.id);

                String result = new HttpUtil().post(CommonUrl.Chat, paprams);
                if (result == null||result.equals(""))
                    System.out.println("resultresultresult为空！！！");
                else {
                    JSONObject json = new JSONObject(result);
                    //paprams.put("operation", "get");
                    String fromID = (String) json.get("from");
                    String toID = (String) json.get("to");
                    String time = (String) json.get("time");
                    String content = (String) json.get("content");
///////////////////////////////////////////////////////////////////////////////////////
                    System.out.println("resultresultcontent的内容为：" + content);
                    addTextToList(content, OTHER);
                    adapter.notifyDataSetChanged();
                    chatListView.setSelection(chatList.size()-1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    protected void addTextToList(String text, int who){
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("person",who );
        map.put("image", who==ME?R.drawable.ffd_ffd:R.drawable.ffd_bb);
        map.put("text", text);
        chatList.add(map);
    }

    private class MyChatAdapter extends BaseAdapter {

        Context context=null;
        ArrayList<HashMap<String,Object>> chatList=null;
        int[] layout;
        String[] from;
        int[] to;
        public MyChatAdapter(Context context,
                             ArrayList<HashMap<String, Object>> chatList, int[] layout,
                             String[] from, int[] to) {
            super();
            this.context = context;
            this.chatList = chatList;
            this.layout = layout;
            this.from = from;
            this.to = to;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return chatList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        class ViewHolder{
            public ImageView imageView=null;
            public TextView textView=null;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder=null;
            int who=(Integer)chatList.get(position).get("person");
            convertView= LayoutInflater.from(context).inflate(
                    layout[who==ME?0:1], null);
            holder=new ViewHolder();
            holder.imageView=(ImageView)convertView.findViewById(to[who*2+0]);
            holder.textView=(TextView)convertView.findViewById(to[who*2+1]);
            //System.out.println(holder);
            //System.out.println("WHYWHYWHYWHYW");
            //System.out.println(holder.imageView);
            holder.imageView.setBackgroundResource((Integer)chatList.get(position).get(from[0]));
            holder.textView.setText(chatList.get(position).get(from[1]).toString());
            return convertView;
        }

    }



}