package personalDr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.westchen.phr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemSelectedListener;


public class all_Dr_list extends Activity
{
    private String[] names = new String[]
            { "范帆达", "阿三地方", "个人", "阿三地方额"};
    private String[] descs = new String[]
            { "阿三地方范围", "啊广泛大使馆岁的法国"
                    , "啊二公司的非官方的三个", "是的风格如果"};
    private int[] imageIds = new int[]
            { R.drawable.ffd_p , R.drawable.ffd_t
                    , R.drawable.ffd_z , R.drawable.ffd_ee};
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ffd_activity_chat_record);
        // 创建一个List集合，List集合的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++)
        {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("header", imageIds[i]);
            listItem.put("personName", names[i]);
            listItem.put("desc", descs[i]);
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.ffd_activity_all__dr_list,
                new String[] { "personName", "header" , "desc"},
                new int[] { R.id.name, R.id.header , R.id.desc });
        ListView list = (ListView) findViewById(R.id.mylist);
        // 为ListView设置Adapter
        list.setAdapter(simpleAdapter);

        // 为ListView的列表项的单击事件绑定事件监听器
        list.setOnItemClickListener(new OnItemClickListener() {
            // 第position项被单击时激发该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                ArrayList<String> mylist;
                Intent intent = getIntent();
                mylist = intent.getStringArrayListExtra("packet");


                AlertDialog.Builder builder = new AlertDialog.Builder(all_Dr_list.this);
                builder.setTitle("选择一个分组");
                String[] myPacket=new String[mylist.size()];
                for (int i = 0; i < mylist.size(); i++) {
                    myPacket[i]=mylist.get(i);
                }
                builder.setItems(myPacket, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(all_Dr_list.this, "选择的城市为：" + mylist.get(which), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(all_Dr_list.this,dr_list.class);
                        //String s = new String(myPacket.getClass().getName());
                        intent.putExtra("myPacket",which);
                        intent.putExtra("names",names[position]);
                        //Toast.makeText(all_Dr_list.this, "选择的城市为：" + myPacket[which], Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
                builder.show();

                //


            }
        });
        // 为ListView的列表项的选中事件绑定事件监听器
        list.setOnItemSelectedListener(new OnItemSelectedListener() {
            // 第position项被选中时激发该方法
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
