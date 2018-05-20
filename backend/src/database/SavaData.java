package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SavaData{
	
	public int savaInfo(String sql,Object[] params){
		
		DBManager db = new DBManager(sql);

		try
		{
			db.pst = db.conn.prepareStatement(sql);
			if(params != null){
				for(int i = 0;i<params.length;i++)
					db.pst.setObject(i + 1, params[i]);
			}
			int columnCount = db.pst.executeUpdate();
			
			return columnCount;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		return 0;
	}
	
	public int savaInfo(String sql,List<String> params){
		
		DBManager db = new DBManager(sql);

		try
		{
			db.pst = db.conn.prepareStatement(sql);
			if(params != null){
				for(int i = 0;i<params.size();i++)
					db.pst.setObject(i + 1, params.get(i));
			}
			int columnCount = db.pst.executeUpdate();
			
			return columnCount;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		return 0;
	}
}
