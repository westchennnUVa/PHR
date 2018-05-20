package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC�������ݿ�ķ�װ�����࣬���û����ô���Ĺ��캯��ʱ���������������ݿ���
 * 
 * @author wind
 *
 */
public class DBManager {
	
	//�������ݿ���Ҫ�ĸ��ֲ�����
	//test���ݿ����֣�root�û�����123456����
    public static final String url = "jdbc:mysql://localhost:3306/test";
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "2222";
  
    public Connection  conn = null;  
    public PreparedStatement pst = null;  
  
    public DBManager(String sql) {  
        try {  
            Class.forName(name);//ָ����������  
            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
            pst = conn.prepareStatement(sql);//׼��ִ�����  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    //�ر����ݿ�����
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    } 
}
