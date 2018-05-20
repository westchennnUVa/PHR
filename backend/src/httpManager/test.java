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
import json.JsonToObject;

public class test extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public test() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(UUID.randomUUID());
		
		String sql = "insert into info_information(id,head,name,time,type,content,commentNum,prizeNum) values(?,?,?,?,?,?,?,?)";
		Object[] params = {String.valueOf(UUID.randomUUID()),"000","小红","now","test","测试插入的数据",1,2};
		int lineNum = new SavaData().savaInfo(sql, params);
		
		System.out.println("受影响行数" + lineNum);
		
		out.println("get请求成功");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String info = request.getParameter("info");
		String json = request.getParameter("json");
		System.out.println(info);
		List<Map<String, Object>> list = null;
		try {
			list = new JsonToObject().jsonToList(json);
			System.out.println("执行函数操作了");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("列表大小为：" + list.size());
		for(int i = 0; i < list.size(); i++){
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()){
				System.out.println(entry.getKey() + ":\t" +  entry.getValue());
			}
		}
		System.out.println(info);
		
		out.println("post请求成功");;
		out.flush();
	}

}
