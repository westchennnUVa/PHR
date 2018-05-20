package httpManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import bean.ChatBean;
import database.GetData;
import database.SavaData;
import json.InfoToJson;

/**
 * 
 */
public class Chat extends HttpServlet {
	
	List<ChatBean> messageList = new ArrayList<ChatBean>();

	public Chat() {super();}

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("�з���CHAT�ˣ�");

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String operation = request.getParameter("operation");
		
		String from = request.getParameter("fromID");
		
		if (operation.equals("getChatContent")){
			Iterator<ChatBean> iter=messageList.iterator();
			for (ChatBean chat : messageList){
				if(from.equals(chat.getTo())){
					System.out.println("some one want to get chat history");
					Map<String,Object> json = new HashMap<String,Object>();
					json.put("from", chat.getFrom());
					json.put("to", chat.getTo());
					json.put("time", chat.getTime());
					json.put("content", chat.getContent());
					try {
						out.println(new InfoToJson().mapToJson(json));
						System.out.println("send out message");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					messageList.remove(chat);
					break;
				}
				
			}
		}else if (operation.equals("getDoctorList")){

			System.out.println("getDoctorList coming in");
			String sql = "select * from info_userdoctorlist where userID = '" + from + "'";
			List<Map<String,Object>> result = new GetData().getInfoWithoutUser(sql);
			
			try {
				out.println(new InfoToJson().mapToJson(result.get(0)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.flush();
		}else if (operation.equals("send")) {

			String to = request.getParameter("toID");
			String time = request.getParameter("time");
			String content = request.getParameter("content");
			
			ChatBean chatBean = new ChatBean(from,to,time,content);
			
			messageList.add(chatBean);

			System.out.println("Clent from:" + chatBean.getFrom());
			System.out.println("Clent to:" + chatBean.getTo());
			System.out.println("Message content" + chatBean.getContent());
			System.out.println("Message List length" + messageList.size());

			out.println("1");
		}else if (operation.equals("change")) {

			String docterID = request.getParameter("docterID");
			String groupList = request.getParameter("groupList");
			
			System.out.println("friend's list update");
			
			String sql = "UPDATE info_userdoctorlist SET doctorID = ? ,groupList = ? WHERE userID = ?";
			Object[] paramss = {docterID,groupList,from};
			new SavaData().savaInfo(sql, paramss);

			out.println("1");
		}
	}

}
