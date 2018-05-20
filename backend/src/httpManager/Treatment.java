package httpManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import database.GetData;
import json.InfoToJson;

/**
 * Servlet implementation class Treatment
 */
public class Treatment extends HttpServlet {

    public Treatment() {        super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String from = request.getParameter("fromID");
		
		List<Map<String,Object>> result = new GetData().getInfo("select * from info_treatment where userID = '" + from + "'");
		
		try {
			out.println(new InfoToJson().listToJson(result));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.flush();
	}
	

}
