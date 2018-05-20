package httpManager;

import java.io.IOException;
import java.io.PrintWriter;
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

public class GetUser extends HttpServlet {

    public GetUser() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String userID = "002";
		
		boolean havedLogin = false;
		String userType = "VIPUser";
		
		for(int j = 0;j < 3;j++){
			
			List<Map<String,Object>> list = new GetData().getUser(userType);
			for(int i = 0;i<list.size();i++){
				
				if(userID.equals(list.get(i).get("userID"))){
					try {
						out.println(new InfoToJson().mapToJson(list.get(i)));
						havedLogin = true;
						break;
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			if(havedLogin)
				return;
			if(j == 0)
				userType = "BaseUser";
			if(j == 1)
				userType = "Doctor";
		}
		userType = "VIPUser";
		out.println("-1");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String userID = request.getParameter("userID");
		
		boolean havedLogon = false;
		String userType = "VIPUser";
		
		for(int j = 0;j < 3;j++){
			
			List<Map<String,Object>> list = new GetData().getUser(userType);
			for(int i = 0;i<list.size();i++){
				
				if(userID.equals(list.get(i).get("userID"))){
					try {
						out.println(new InfoToJson().mapToJson(list.get(i)));
						havedLogon = true;
						break;
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			if(havedLogon)
				return;
			if(j == 0)
				userType = "BaseUser";
			if(j == 1)
				userType = "Doctor";
		}
		userType = "VIPUser";
		out.println("-1");
	}

}
