package httpManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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

public class News extends HttpServlet {
	
	List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
	
    public News() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		result = null;
		result = new GetData().getInfo("select * from info_news order by time desc");
		
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
		
		if(operation.equals("read")){
			String newsID = request.getParameter("id");
			List<Map<String,Object>> result = new GetData().getInfo("select * from info_comment where newsID = '" + newsID + "' order by time desc");
			try {
				out.println(new InfoToJson().listToJson(result));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else if(operation.equals("my")){
			String userID = request.getParameter("userID");
			List<Map<String,Object>> result = new GetData().getInfo("select * from info_news where userID = '" + userID + "' order by time desc");
			try {
				out.println(new InfoToJson().listToJson(result));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println("1");
		}else if(operation.equals("save")){
			String sql = "insert into info_news(newsID,content,title,userID) values(?,?,?,?)";
			String userID = request.getParameter("userID");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Object[] params = {String.valueOf(UUID.randomUUID()),content,title,userID};
			int lineNum = new SavaData().savaInfo(sql, params);
			
			System.out.println("有用户发新帖子了！");
			
			out.println("1");
		}else if(operation.equals("answer")){
			String sql = "insert into info_comment(userID,newsID,content) values(?,?,?)";
			String userID = request.getParameter("userID");
			String newsID = request.getParameter("newsID");
			String content = request.getParameter("content");
			int commentNum = Integer.valueOf(request.getParameter("commentNum"));
			Object[] params = {userID,newsID,content};
			int lineNum = new SavaData().savaInfo(sql, params);
			
			sql = "update info_news set commentNum=? where newsID=?";
			Object[] paramss = {commentNum,newsID};
			new SavaData().savaInfo(sql, paramss);

			System.out.println("有用户评论了！");
			
			out.println("1");
		}
		out.flush();
	}

}
