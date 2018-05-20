package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.BaseUser;
import bean.Information;
import bean.PHR;

/**
 * 从数据库获取数据的封装类
 * 
 * @author wind
 *
 */
public class GetData {

	private static ResultSet ret = null;
	private ResultSetMetaData rsmd = null;

	/**
	 * 通用查询方法，可以根据不同sql语句，查询任意表，最后生成List<Map<String,Object>>类
	 * 
	 * @param sql	通用的查询SQL语句
	 * @return		返回查询结果，并封装成List<Map<String, Object>>集合
	 */
	public List<Map<String, Object>> getInfoWithoutUser(String sql) {

		DBManager db = new DBManager(sql);
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			ret = db.pst.executeQuery();// 执行语句，得到结果集
			rsmd = ret.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (ret.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++)
					map.put(rsmd.getColumnLabel(i), ret.getObject(i));
				list.add(map);
			} // 显示数据
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}	/**
	 * 通用查询方法，可以根据不同sql语句，查询任意表，最后生成List<Map<String,Object>>类
	 * 如果查询的表包含用户ID，则会查询相应的用户表，加入该用户的基本资料（基本用户）
	 * 
	 * @param sql	通用的查询SQL语句
	 * @return		返回查询结果，并封装成List<Map<String, Object>>集合
	 */
	public List<Map<String, Object>> getInfo(String sql) {

		DBManager db = new DBManager(sql);
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			ret = db.pst.executeQuery();// 执行语句，得到结果集
			rsmd = ret.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String userID = null;
			while (ret.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					if (rsmd.getColumnLabel(i).equals("userID")) {
						userID = (String) ret.getObject(i);
					} else {
						map.put(rsmd.getColumnLabel(i), ret.getObject(i));
//						System.out.println("getInfo函数：" + rsmd.getColumnLabel(i));
					}
				}
				if (userID != null)
					map = this.getInfoItem("select * from p_baseuser where userID = " + userID, map);
				list.add(map);
			} // 显示数据
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}
	
	/**
	 * 通用查询方法，可以根据不同sql语句，查询任意表，最后生成List<Map<String,Object>>类
	 * 如果查询的表包含用户ID，则会查询相应的用户表，加入该用户的基本资料（医生用户）
	 * 
	 * @param sql	通用的查询SQL语句
	 * @return		返回查询结果，并封装成List<Map<String, Object>>集合
	 */
	public List<Map<String, Object>> getInfo2(String sql) {

		DBManager db = new DBManager(sql);
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			ret = db.pst.executeQuery();// 执行语句，得到结果集
			rsmd = ret.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String userID = null;
			while (ret.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					if (rsmd.getColumnLabel(i).equals("userID")) {
						userID = (String) ret.getObject(i);
					} else {
						map.put(rsmd.getColumnLabel(i), ret.getObject(i));
					}
				}
				if (userID != null)
					map = this.getInfoItem("select * from p_doctor where userID = " + userID, map);
				list.add(map);
			} // 显示数据
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}

	/**
	 * 通用查询方法的子方法，用于查询基本用户的方法，并把对应的用户资料加到map中
	 * 
	 * @param sql	对用户表查询相应的SQL语句
	 * @return		添加好用户资料的map集合
	 */
	public Map<String, Object> getInfoItem(String sql, Map<String, Object> map) {

		DBManager db = new DBManager(sql);

		try {
			ResultSet ret = db.pst.executeQuery();// 执行语句，得到结果集
			ResultSetMetaData rsmd = ret.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (ret.next()) {
				for (int i = 1; i <= columnCount; i++) {
					map.put(rsmd.getColumnLabel(i), ret.getObject(i));
				}
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}
	
	/**
	 * 通用查询方法的子方法，用于查询医院资料的方法，并把对应的医院资料加到map中
	 * 
	 * @param sql	对用户表查询相应的SQL语句
	 * @return		添加好医院资料的map集合
	 */
	public Map<String, Object> getInfoItem2(String sql, Map<String, Object> map) {

		DBManager db = new DBManager(sql);
		String hospital = null;

		try {
			ResultSet ret = db.pst.executeQuery();// 执行语句，得到结果集
			ResultSetMetaData rsmd = ret.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (ret.next()) {
				for (int i = 1; i <= columnCount; i++) {
					if (rsmd.getColumnLabel(i).equals("hospital")) {
						hospital = (String) ret.getObject(i);
						map = this.getInfoItem("select * from p_hospital where name = '" + hospital + "'", map);
					}
				}
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}
	
	/**
	 * 实现分页查询的通用方法，输入相应的SQL语句和页数，显示相应的结果并封装到List集合里
	 * 
	 * @param sql	查询SQL语句
	 * @param page	查询页数
	 * @return		查询结果，List<Map<String,Object>>的数据类型
	 */
	public List<Map<String,Object>> getSpecificInfo(String sql,int page){
		
		DBManager db = new DBManager(sql);
		int roll = 0;//用来判断超界问题
		List<Map<String,Object>> chooselistPage = new ArrayList<Map<String,Object>>();//用来选择存储固定10个数据
		Map<String,Object> map1[];
		try
		{
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			ret = db.pst.executeQuery();// 执行语句，得到结果集
			rsmd = ret.getMetaData();	//获得标题
			int columnCount = rsmd.getColumnCount();
			String userID = null;
//			System.out.println("getInfo函数：" + rsmd.getColumnLabel(columnCount));
			while (ret.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				for(int i = 1;i <= columnCount;i++){
					if(rsmd.getColumnLabel(i).equals("userID")){
						userID = (String) ret.getObject(i);
					}else{
						map.put(rsmd.getColumnLabel(i), ret.getObject(i));
					}
				}
				if(userID != null)
					map = this.getInfoItem("select * from p_baseuser where userID = " + userID, map);
				list.add(map);
				roll++;
			} 
			for(int z=10*page;z<10*page+10;z++){
				if(z>=roll){
					break;
				}
				else{
					chooselistPage.add((Map)list.get(z));//得到固定十个数据
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		/*return chooselistPage;*/
		return chooselistPage;
	}

	/**
	 * 查询用户资料的通用方法，通过输入相应的用户的类型，该方法会查询系统的所有用户表，
	 * 返回相应用户类型的所有用户资料，并封装到List集合里
	 * 
	 * @param type	要查询用户类型
	 * @return		相应类型的所有用户资料
	 */
	public List<Map<String, Object>> getUser(String type) {

		String sql = null;

		if (type.equals("BaseUser")) {
			sql = "select * from p_baseuser";// SQL语句
		} else if (type.equals("VIPUser")) {
			sql = "select * from p_vipuser";// SQL语句
		} else if (type.equals("Doctor")) {
			sql = "select * from p_doctor";// SQL语句
		}

		DBManager db = new DBManager(sql);
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			ret = db.pst.executeQuery();// 执行语句，得到结果集
			rsmd = ret.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (ret.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1 ; i <= columnCount ; i++) {
					map.put(rsmd.getColumnLabel(i), ret.getObject(i));
				}
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}

	/**
	 * 查询PHR表的方法，获得所有PHR表的数据
	 * 
	 * @return	PHR所有数据
	 */
	public PHR getPHR() {

		String sql = "select * from info_phr";// SQL语句
		DBManager db = new DBManager(sql);

		try {
			ret = db.pst.executeQuery();// 执行语句，得到结果集
			while (ret.next()) {
				String id = ret.getString(1);
				String height = ret.getString(2);
				String weight = ret.getString(3);
				String blood_pressure = ret.getString(4);
				String vital_capacity = ret.getString(5);
				String vision_left = ret.getString(6);
				String vision_right = ret.getString(7);
				String heart_rate = ret.getString(8);
				String temperature = ret.getString(9);
				String hemoglobin = ret.getString(10);
				String platelet = ret.getString(11);
				String urine = ret.getString(12);
				String urine_red_blood_cell = ret.getString(13);
				String blood_type = ret.getString(14);
				String Measurements_1 = ret.getString(15);
				String Measurements_2 = ret.getString(16);
				String Measurements_3 = ret.getString(17);
				String score = ret.getString(18);
				PHR phr = new PHR(id, height, weight, blood_pressure, vital_capacity, vision_left, vision_right,
						heart_rate, temperature, hemoglobin, platelet, urine, urine_red_blood_cell, blood_type,
						Measurements_1, Measurements_2, Measurements_3, score);
				return phr;

			} // 显示数据
			// ret.close();
			// db.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}

}
