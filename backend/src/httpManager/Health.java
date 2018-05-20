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

public class Health extends HttpServlet {

    public Health() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String sql = "select * from info_information";
		List<Map<String,Object>> result = new GetData().getInfo2(sql);
		if(result == null)
			System.out.println("从数据库获取数据失败");
		else
			System.out.println("数据大小为：" + result.size());
		try {
			out.println(new InfoToJson().listToJson(result));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String operation = request.getParameter("operation");
		String type = request.getParameter("type");
		
		if(operation.equals("getAll")){
			String sql = "select * from info_information";
			List<Map<String,Object>> result = new GetData().getInfo2(sql);
			try {
				out.println(new InfoToJson().listToJson(result));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.flush();
		}else if(operation.equals("getType")){
			System.out.println("PHR表有人提取了！");
			String sql = "select * from info_information where Type = '" + type + "' order by time desc";
			List<Map<String,Object>> result = new GetData().getInfo2(sql);
			try {
				out.println(new InfoToJson().listToJson(result));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.flush();
		}else if(operation.equals("getOne")){
			String newsID = request.getParameter("newsID");
//			System.out.println("客户端要获取" + newsID);
			String sql = "select * from info_health where newsID = '" + newsID + "'";
			List<Map<String,Object>> result = new GetData().getInfo2(sql);
			try {
				out.println(new InfoToJson().mapToJson(result.get(0)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.flush();
		}else if(operation.equals("read")){
			String newsID = request.getParameter("id");
			List<Map<String,Object>> result = new GetData().getInfo("select * from info_comment where newsID = " + newsID);
			try {
				out.println(new InfoToJson().listToJson(result));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else if(operation.equals("save")){
			String sql = "insert into info_information(newsID,type,content,title,userID) values(?,?,?,?,?)";
			String userID = request.getParameter("userID");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			type = request.getParameter("type");
			Object[] params = {String.valueOf(UUID.randomUUID()),type,content,title,userID};
			int lineNum = new SavaData().savaInfo(sql, params);
			
			out.println("1");
		}else if(operation.equals("answer")){
			String sql = "insert into info_comment(userID,newsID,content) values(?,?,?)";
			String userID = request.getParameter("userID");
			String newsID = request.getParameter("newsID");
			String content = request.getParameter("content");
			Object[] params = {userID,newsID,content};
			int lineNum = new SavaData().savaInfo(sql, params);
			
			sql = "update info_news set commentNum=? where newsID=?";
			int commentNum = Integer.valueOf(request.getParameter("commentNum"));
			Object[] paramss = {commentNum,newsID};
			new SavaData().savaInfo(sql, paramss);
			
			out.println("1");
		}
		out.flush();
	}

}
