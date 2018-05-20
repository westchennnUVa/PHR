package httpManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import database.GetData;
import database.SavaData;
import json.InfoToJson;

public class Consumption extends HttpServlet {

    public Consumption() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String userID = request.getParameter("userID");
		String operation = request.getParameter("operation");
		String type = request.getParameter("type");
		
		if (operation.equals("read")){
//			
//			String sql = "select registrationID from info_myregistration where userID = '" + userID + "' order by time desc";
//			List<Map<String,Object>> registrationID = new GetData().getInfo(sql);
//
//			System.out.println("Consumption���ԣ�");
//			System.out.println("registrationID��СΪ��" + registrationID.size());
//			
//			List<Map<String,Object>> registration = new ArrayList<Map<String,Object>>();
//			for (int i = 0;i<registrationID.size();i++){
//				sql = "select * from a_registration where recordType = '" + type + "' and where id = '" + registrationID.get(i).get("registrationID") + "' order by time desc";
//				List<Map<String,Object>> record = new GetData().getInfo(sql);
//				
//				System.out.println("Consumption���ԣ�");
//				System.out.println("record��СΪ��" + record.size());
//				registration.add(record.get(0));
//			}
//			System.out.println("Consumption���ԣ�");
//			System.out.println("registration��СΪ��" + registration.size());
//			try {
//				out.println(new InfoToJson().listToJson(registration));
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			out.println("1");
		}else if (operation.equals("delete")){
			String registrationID = request.getParameter("registrationID");
			String sql = "delete from info_myregistration where id=? and where registrationID=?";
			Object[] paramss = {userID,registrationID};
			new SavaData().savaInfo(sql, paramss);

			out.println("1");
		}else if (operation.equals("get")){
			
			String sql = "select * from a_registration where recordType = '" + type + "' order by time desc";
			List<Map<String,Object>> result = new GetData().getInfo(sql);
			System.out.println("get" + type + "record");
			
			try {
				out.println(new InfoToJson().listToJson(result));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.flush();
		}
	}

}
