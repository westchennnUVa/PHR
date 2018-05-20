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
 * �����ݿ��ȡ���ݵķ�װ��
 * 
 * @author wind
 *
 */
public class GetData {

	private static ResultSet ret = null;
	private ResultSetMetaData rsmd = null;

	/**
	 * ͨ�ò�ѯ���������Ը��ݲ�ͬsql��䣬��ѯ������������List<Map<String,Object>>��
	 * 
	 * @param sql	ͨ�õĲ�ѯSQL���
	 * @return		���ز�ѯ���������װ��List<Map<String, Object>>����
	 */
	public List<Map<String, Object>> getInfoWithoutUser(String sql) {

		DBManager db = new DBManager(sql);
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			ret = db.pst.executeQuery();// ִ����䣬�õ������
			rsmd = ret.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (ret.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++)
					map.put(rsmd.getColumnLabel(i), ret.getObject(i));
				list.add(map);
			} // ��ʾ����
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}	/**
	 * ͨ�ò�ѯ���������Ը��ݲ�ͬsql��䣬��ѯ������������List<Map<String,Object>>��
	 * �����ѯ�ı�����û�ID������ѯ��Ӧ���û���������û��Ļ������ϣ������û���
	 * 
	 * @param sql	ͨ�õĲ�ѯSQL���
	 * @return		���ز�ѯ���������װ��List<Map<String, Object>>����
	 */
	public List<Map<String, Object>> getInfo(String sql) {

		DBManager db = new DBManager(sql);
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			ret = db.pst.executeQuery();// ִ����䣬�õ������
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
//						System.out.println("getInfo������" + rsmd.getColumnLabel(i));
					}
				}
				if (userID != null)
					map = this.getInfoItem("select * from p_baseuser where userID = " + userID, map);
				list.add(map);
			} // ��ʾ����
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}
	
	/**
	 * ͨ�ò�ѯ���������Ը��ݲ�ͬsql��䣬��ѯ������������List<Map<String,Object>>��
	 * �����ѯ�ı�����û�ID������ѯ��Ӧ���û���������û��Ļ������ϣ�ҽ���û���
	 * 
	 * @param sql	ͨ�õĲ�ѯSQL���
	 * @return		���ز�ѯ���������װ��List<Map<String, Object>>����
	 */
	public List<Map<String, Object>> getInfo2(String sql) {

		DBManager db = new DBManager(sql);
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			ret = db.pst.executeQuery();// ִ����䣬�õ������
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
			} // ��ʾ����
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}

	/**
	 * ͨ�ò�ѯ�������ӷ��������ڲ�ѯ�����û��ķ��������Ѷ�Ӧ���û����ϼӵ�map��
	 * 
	 * @param sql	���û����ѯ��Ӧ��SQL���
	 * @return		��Ӻ��û����ϵ�map����
	 */
	public Map<String, Object> getInfoItem(String sql, Map<String, Object> map) {

		DBManager db = new DBManager(sql);

		try {
			ResultSet ret = db.pst.executeQuery();// ִ����䣬�õ������
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
	 * ͨ�ò�ѯ�������ӷ��������ڲ�ѯҽԺ���ϵķ��������Ѷ�Ӧ��ҽԺ���ϼӵ�map��
	 * 
	 * @param sql	���û����ѯ��Ӧ��SQL���
	 * @return		��Ӻ�ҽԺ���ϵ�map����
	 */
	public Map<String, Object> getInfoItem2(String sql, Map<String, Object> map) {

		DBManager db = new DBManager(sql);
		String hospital = null;

		try {
			ResultSet ret = db.pst.executeQuery();// ִ����䣬�õ������
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
	 * ʵ�ַ�ҳ��ѯ��ͨ�÷�����������Ӧ��SQL����ҳ������ʾ��Ӧ�Ľ������װ��List������
	 * 
	 * @param sql	��ѯSQL���
	 * @param page	��ѯҳ��
	 * @return		��ѯ�����List<Map<String,Object>>����������
	 */
	public List<Map<String,Object>> getSpecificInfo(String sql,int page){
		
		DBManager db = new DBManager(sql);
		int roll = 0;//�����жϳ�������
		List<Map<String,Object>> chooselistPage = new ArrayList<Map<String,Object>>();//����ѡ��洢�̶�10������
		Map<String,Object> map1[];
		try
		{
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			ret = db.pst.executeQuery();// ִ����䣬�õ������
			rsmd = ret.getMetaData();	//��ñ���
			int columnCount = rsmd.getColumnCount();
			String userID = null;
//			System.out.println("getInfo������" + rsmd.getColumnLabel(columnCount));
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
					chooselistPage.add((Map)list.get(z));//�õ��̶�ʮ������
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
	 * ��ѯ�û����ϵ�ͨ�÷�����ͨ��������Ӧ���û������ͣ��÷������ѯϵͳ�������û���
	 * ������Ӧ�û����͵������û����ϣ�����װ��List������
	 * 
	 * @param type	Ҫ��ѯ�û�����
	 * @return		��Ӧ���͵������û�����
	 */
	public List<Map<String, Object>> getUser(String type) {

		String sql = null;

		if (type.equals("BaseUser")) {
			sql = "select * from p_baseuser";// SQL���
		} else if (type.equals("VIPUser")) {
			sql = "select * from p_vipuser";// SQL���
		} else if (type.equals("Doctor")) {
			sql = "select * from p_doctor";// SQL���
		}

		DBManager db = new DBManager(sql);
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			ret = db.pst.executeQuery();// ִ����䣬�õ������
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
	 * ��ѯPHR��ķ������������PHR�������
	 * 
	 * @return	PHR��������
	 */
	public PHR getPHR() {

		String sql = "select * from info_phr";// SQL���
		DBManager db = new DBManager(sql);

		try {
			ret = db.pst.executeQuery();// ִ����䣬�õ������
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

			} // ��ʾ����
			// ret.close();
			// db.close();// �ر�����
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return null;
	}

}
