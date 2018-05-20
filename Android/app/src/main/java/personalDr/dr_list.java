package personalDr;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westchen.phr.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mating.BaseFragment;
import url.CommonUrl;
import util.HttpUtil;

public class dr_list extends ExpandableListActivity {

	//List<String> group;      //组列表
	//List<List<Doctor>> child;   //子列表
	ContactsInfoAdapter adapter=new ContactsInfoAdapter(); //数据适配器

	public DataBean dataBean;
	private Handler handler;
	//最近聊天列表
	private List<Doctor> doctorList = new ArrayList<Doctor>();
	//组名列表
	private List<String> groupList = new ArrayList<String>();
	//组员成员列表
	List<List<Doctor>> groupDoctorList = new ArrayList<List<Doctor>>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //设置为无标题
		setContentView(R.layout.ffd_dr_list);


		initializeData();
		getExpandableListView().setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
				Intent intent = new Intent(dr_list.this, detail_of_chat.class);
				//String s = new String(myPacket.getClass().getName());
				intent.putExtra("id", groupDoctorList.get(i).get(i1).getUserID());
				intent.putExtra("name", groupDoctorList.get(i).get(i1).getName());
				//Toast.makeText(dr_list.this, groupDoctorList.get(i).get(i1).getName(), Toast.LENGTH_SHORT).show();
				startActivity(intent);
				return false;
			}
		});

		getExpandableListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				int itemType = ExpandableListView.getPackedPositionType(id);
				if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
					int groupPosition = ExpandableListView.getPackedPositionGroup(id);

					return false; //true if we consumed the click, false if not

				}
				if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
//
					return false;
				}
				return false;
			}
		});
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg){
				if(msg.what == 1){
					//show.append(msg.obj.toString());

				}else if(msg.what == 13){
					System.out.println("ffdffdffdffd1");
					dataBean = (DataBean)msg.obj;
					doctorList = dataBean.getDoctorList();
					groupList = dataBean.getGroupList();
					groupDoctorList = dataBean.getGroupDoctorList();
					getExpandableListView().setAdapter(adapter);
					getExpandableListView().setCacheColorHint(0); //设置拖动列表的时候防止出现黑色背景

				}
			}
		};
		getDoctorList();
	}

	public void dr_list_click1(View source)
	{
		System.out.println("zxccv1");
		Intent intent=new Intent(dr_list.this,chat_record.class);
		System.out.println("zxccv2");
		Bundle myBudle=new Bundle();
		myBudle.putInt("identify", 1);
		myBudle.putSerializable("dataBean", dataBean);
		startActivity(intent);
	}
	public void dr_list_click(View source)
	{
		final EditText et = new EditText(this);
		new AlertDialog.Builder(this).setTitle("添加分组")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(et)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String input = et.getText().toString();
						if (input.equals("")) {
							Toast.makeText(getApplicationContext(), "搜索内容不能为空！" + input, Toast.LENGTH_LONG).show();
						}
						else {
							System.out.println("asdf1");
							groupList.add(input);
							groupDoctorList.add(new ArrayList<Doctor>());
							System.out.println("asdf11111111");
							saveDoctorList();
							System.out.println("asdf22222222");
						}
					}
				})
				.setNegativeButton("取消", null)
				.show();
	}
	public void dr_list_click12(View source)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(dr_list.this);
		builder.setTitle("选择要删除医生的分组");
		String[] myPacket=new String[groupList.size()];
		for (int i = 0; i < groupList.size(); i++) {
			myPacket[i] = groupList.get(i);
			builder.setItems(myPacket, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which1) {
					AlertDialog.Builder builder = new AlertDialog.Builder(dr_list.this);
					builder.setTitle("选择要删除的医生");
					final String[] myPacket=new String[groupDoctorList.get(which1).size()];
					final int whichwide=which1;
					for (int i = 0; i < myPacket.length; i++) {
						myPacket[i] = groupDoctorList.get(which1).get(i).getName();
						builder.setItems(myPacket, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								groupDoctorList.get(whichwide).remove(which);
								saveDoctorList();
								adapter.notifyDataSetChanged();
								//this.notify();
							}
						});
						builder.show();
					}
				}
			});
			builder.show();
		}
	}
	public void dr_list_click13(View source)
	{
		final AlertDialog.Builder builder = new AlertDialog.Builder(dr_list.this);
		builder.setTitle("选择要删除的分组");

		String[] myPacket=new String[groupList.size()];
		for (int i = 0; i < groupList.size(); i++) {
			myPacket[i] = groupList.get(i);
			builder.setItems(myPacket, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					groupList.remove(which);
					saveDoctorList();
					adapter.notifyDataSetChanged();
					//this.notify();
					dialog.dismiss();


				}
			});
			builder.show();

		}
	}
	public void dr_list_click14(View source)
	{
		Intent myintent=new Intent(dr_list.this, BaseFragment.class);
		startActivity(myintent);
	}
	private void saveDoctorList() {
		new Thread(){
			@Override
			public void run(){
				JSONArray doctorListJson = new JSONArray();
				for (int i = 0; i < doctorList.size(); i++)
				{
					doctorListJson.put(doctorList.get(i).getUserID());
				}
				System.out.println("asd2");
				JSONArray listJson1 = new JSONArray();
				for (int i = 0; i < groupList.size(); i++) {
					try {
						JSONObject listJson2 = new JSONObject();
						JSONArray listJson3 = new JSONArray();
						listJson2.put("分组名称",groupList.get(i));
						System.out.println("asdfghjl1"+groupList.get(i));
						List<Doctor> groupDoctor = groupDoctorList.get(i);
						for (int j = 0; j < groupDoctor.size(); j++)
						{
							listJson3.put(groupDoctor.get(j).getUserID());
						}
						listJson2.put("医生列表",listJson3);
						listJson1.put(listJson2);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				System.out.println("asd4");
				Map<String, String> map = new HashMap<String, String>();
				map.put("operation", "change");
				map.put("docterID", doctorListJson.toString());
				map.put("groupList", listJson1.toString());
				map.put("fromID", ffd_MainActivity.id);
				System.out.println("asd5"+doctorListJson.toString());
				new HttpUtil().post(CommonUrl.Chat, map);
				System.out.println("asd6"+listJson1.toString());
			}
		}.start();
		//好友列表

	}
	/**
	 * 初始化组、子列表数据
	 */
	private void getDoctorList() {

		new Thread(){

			@Override
			public void run(){
				List<Doctor> doctorList1 = new ArrayList<Doctor>();
				List<String> groupList1 = new ArrayList<String>();
				List<List<Doctor>> groupDoctorList1 = new ArrayList<List<Doctor>>();
//                System.out.println("获取数据1");
				Map<String, String> map = new HashMap<String, String>();
				map.put("operation", "getDoctorList");
				map.put("fromID", ffd_MainActivity.id);
				String jsonStr = new HttpUtil().post(CommonUrl.Chat, map);
				JSONObject json = null;
				try {
					json = new JSONObject(jsonStr);
					JSONArray doctorListJson = new JSONArray((String) json.get("doctorID"));
					for (int i = 0; i < doctorListJson.length(); i++){
						Map<String, String> params = new HashMap<String, String>();
						params.put("userID", doctorListJson.getString(i));
						String result = new HttpUtil().post(CommonUrl.GetUser, params);
						JSONObject userJson = new JSONObject(result);
						Doctor doctor = new Doctor(userJson);
						doctorList1.add(doctor);
					}
					JSONArray listJson1 = new JSONArray((String) json.get("groupList"));
					JSONObject listJson2;
					JSONArray listJson3;
					for (int i = 0; i < listJson1.length(); i++) {
						System.out.println("startstartstartstartstartstart");
						listJson2 = listJson1.getJSONObject(i);
						groupList1.add(listJson2.getString("分组名称"));
						listJson3 = listJson2.getJSONArray("医生列表");
						List<Doctor> groupDoctor = new ArrayList<Doctor>();
						for (int j = 0; j < listJson3.length(); j++) {
							Map<String, String> params = new HashMap<String, String>();
							params.put("userID", listJson3.getString(j));
							String result = new HttpUtil().post(CommonUrl.GetUser, params);
							JSONObject userJson = new JSONObject(result);
							groupDoctor.add(new Doctor(userJson));
						}
						groupDoctorList1.add(groupDoctor);
					}
					Message msg = new Message();
					DataBean dataBean = new DataBean(doctorList1,groupList1,groupDoctorList1);
					msg.obj = dataBean;
					msg.what = 13;
					handler.sendMessage(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}	private void initializeData(){
		//group = new ArrayList<String>();
		//child = new ArrayList<List<Doctor>>();

//		addInfo("外科医生", new String[]{"male", "138123***", "GuangZhou"});
//		addInfo("内科医生", new String[]{"female", "138123***", "GuangZhou"});
//		addInfo("眼科医生", new String[]{"male", "138123***", "ShenZhen"});
//		addInfo("免疫医生", new String[]{"female", "138123***", "ShangHai"});
//		addInfo("其它", new String[]{"male", "138231***", "ZhanJiang"});
	}


	class ContactsInfoAdapter extends BaseExpandableListAdapter{
		//-----------------Child----------------//
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return groupDoctorList.get(groupPosition).get(childPosition);
		}
		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
		@Override
		public int getChildrenCount(int groupPosition) {
			try
			{
				return groupDoctorList.get(groupPosition).size();
			}
			catch (Exception e)
			{
				return 0;
			}

		}
		@Override
		public View getChildView(int groupPosition, int childPosition,
								 boolean isLastChild, View convertView, ViewGroup parent) {
			String string = groupDoctorList.get(groupPosition).get(childPosition).getName();
			return getGenericView(string);
		}
		//----------------Group----------------//
		@Override
		public Object getGroup(int groupPosition) {
			return groupList.get(groupPosition);
		}
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		@Override
		public int getGroupCount() {
			return groupList.size();
		}
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
								 View convertView, ViewGroup parent) {
			String string = groupList.get(groupPosition);
			return getGenericView(string);
		}
		//创建组/子视图
		public TextView getGenericView(String s) {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, 100);
			TextView text = new TextView(dr_list.this);
			text.setLayoutParams(lp);
			text.setTextSize(24);
			// Center the text vefrtically
			text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			text.setPadding(50, 0, 0, 0);
			text.setText(s);
			return text;
		}
		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}
	}
}

