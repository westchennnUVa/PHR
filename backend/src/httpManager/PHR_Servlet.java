package httpManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import bean.PHR;
import database.GetData;
import database.SavaData;
import json.InfoToJson;
import json.JsonToObject;

public class PHR_Servlet extends HttpServlet {

	public PHR_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		boolean hasUser = false;

		String userID = request.getParameter("userID");
		String operation = request.getParameter("operation");
		List<Map<String, Object>> result = new GetData().getInfoWithoutUser("select * from info_phr");
		for (Map<String, Object> map : result) {
			if (userID.equals(map.get("userID"))) {
				hasUser = true;
				break;
			}
		}
		if (operation.equals("get")) {
			if (hasUser == false) {
				out.println("-1");
			} else if (hasUser == true) {
				String sql = "select * from info_phr where userID = '" + userID + "'";
				List<Map<String, Object>> json = new GetData().getInfo(sql);
				try {
					out.println(new InfoToJson().mapToJson(json.get(0)));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		if (operation.equals("save")){
			if (hasUser == false) {
				String jsonStr = request.getParameter("data");
				List<String> params = null;
				try {
					params = new JsonToObject().jsonToPHR(jsonStr);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String sql = "insert into info_phr values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				int lineNum = new SavaData().savaInfo(sql, params);
			} else if (hasUser == true) {
				String jsonStr = request.getParameter("data");
				List<String> params = null;
				try {
					params = new JsonToObject().jsonToPHR(jsonStr);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String sql = "delete from info_phr where userID=?";
				Object[] params1= {userID};
				int lineNum = new SavaData().savaInfo(sql, params1);
				
				sql = "insert into info_phr values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				lineNum = new SavaData().savaInfo(sql, params);
				System.out.println("PHR表已更新！");
			}
		}
	}

}
