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
import org.json.JSONObject;

import bean.ChatBean;
import bean.RegistrationBean;
import database.GetData;
import database.SavaData;
import json.InfoToJson;

/**
 * ����һ���Һŵ�servlet
 */
public class Registration extends HttpServlet {
	
	//�Һ���Ϣ�б�
	//�û����͹Һ���Ϣ�����������һ�����ݣ����������ݿ�
	//�û����ܹҺ���Ϣ����ɾ����Ӧ������
	List<RegistrationBean> messageList = new ArrayList<RegistrationBean>();

	public Registration() {super();	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * ����Һŵ�post����
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String operation = request.getParameter("operation");
		String type = request.getParameter("type");
		
		String from = request.getParameter("fromID");
		
		if (operation.equals("get")){
//			System.out.println("���˻�ȡ����ˣ�");
			if(messageList.size() > 0){
				for (RegistrationBean registration : messageList){
					if(from.equals(registration.getTo())){
						try {
							JSONObject json = new JSONObject();
							json.put("name", registration.getName());
							json.put("IDCard", registration.getIDCard());
							json.put("phone", registration.getPhone());
							json.put("time", registration.getTime());
							out.println(json.toString());
						} catch (JSONException e) {
							e.printStackTrace();
						}
						messageList.remove(registration);
						break;
					}
					
				}
			}
		}else if (operation.equals("send") && type.equals("挂号")) {

			String hospital = request.getParameter("hospital");
			String section = request.getParameter("section");
//			String price = request.getParameter("price");
			String time = request.getParameter("time");
			String name = request.getParameter("userName");
			String IDCard = request.getParameter("IDCard");
			String phone = request.getParameter("phone");
			
			String sql = "select * from p_doctor where hospital = '" + hospital + "' and section = '" + section + "'";
			List<Map<String,Object>> result = new GetData().getInfoWithoutUser(sql);
			
			for (Map<String,Object> map : result){
				String to = (String) map.get("userID");
				RegistrationBean bean = new RegistrationBean(to,time,name,IDCard,phone,"无","无");
				System.out.println("�йҺŷ����ˣ�");
				messageList.add(bean);
			}

			out.println("挂号发送成功");
		}else if (operation.equals("send") && type.equals("体检")) {
			System.out.println("进入体检了！");

			String hospital = request.getParameter("hospital");
			String price = request.getParameter("price");
			String time = request.getParameter("time");
			String userNamename = request.getParameter("userName");
			String IDCard = request.getParameter("IDCard");
			String phone = request.getParameter("phone");
			String name = request.getParameter("name");
			String people = request.getParameter("people");
			
			String sql = "select * from p_doctor where hospital = '" + hospital + "'";
			List<Map<String,Object>> result = new GetData().getInfoWithoutUser(sql);
			
			for (Map<String,Object> map : result){
				String to = (String) map.get("userID");
				RegistrationBean bean = new RegistrationBean(to,time,userNamename,IDCard,phone,name,people);
				messageList.add(bean);
			}
		}
	}

}
