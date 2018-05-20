package httpManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import database.GetData;
import json.InfoToJson;

public class GetAllDoctor extends HttpServlet {

	public GetAllDoctor() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		result = new GetData().getInfoWithoutUser("select * from p_doctor");
		
		try {
			out.println(new InfoToJson().listToJson(result));
			System.out.println("send all doctor info");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.flush();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
