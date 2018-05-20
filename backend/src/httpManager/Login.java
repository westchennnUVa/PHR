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

import bean.BaseUser;
import database.GetData;
import database.SavaData;
import json.InfoToJson;

/**
 * ����һ����¼��servlet
 */
public class Login extends HttpServlet {

    public Login() {super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * ��֤�û����û�����
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String operation = request.getParameter("operation");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        boolean havedLogin = false;
        String userType = "VIPUser";

        if (operation.equals("Login")) {

            for (int j = 0; j < 3; j++) {

                List<Map<String, Object>> list = new GetData().getUser(userType);
                for (int i = 0; i < list.size(); i++) {

                    if (userName.equals(list.get(i).get("userName")) && password.equals(list.get(i).get("password"))) {
                        try {
                            out.println(new InfoToJson().mapToJson(list.get(i)));
                            System.out.println(list.get(i).get("name") + "�����ˣ�");
                            havedLogin = true;
                            break;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (havedLogin)
                    break;
                if (j == 0)
                    userType = "BaseUser";
                if (j == 1)
                    userType = "Doctor";
            }
            out.println("-1");
        } else if (operation.equals("Enroll_BaseUser")) {
            String sql = "insert into p_baseuser values(?,?,?,?,?,?,?,?,?,?,?)";
            String userName_enroll = request.getParameter("userName");
            String password_enroll = request.getParameter("password");
            String name = request.getParameter("name");
            String sex = request.getParameter("sex");
            String age = request.getParameter("age");
            String head = request.getParameter("head");
            String personalID = request.getParameter("personalID");
            String phone = request.getParameter("phone");
            String type = request.getParameter("type");
            String realName = request.getParameter("realName");
            Object[] params = { String.valueOf(UUID.randomUUID()), userName_enroll, password_enroll, name, sex, age,
                    head, personalID, phone, type, realName };
            int lineNum = new SavaData().savaInfo(sql, params);

            System.out.println("���û�" + name + "ע�����ϵͳ�ˣ�");

            out.println("1");
        } else if (operation.equals("Enroll_Doctor")) {
            String sql = "insert into p_doctor values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String userName_enroll = request.getParameter("userName");
            String password_enroll = request.getParameter("password");
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String sex = request.getParameter("sex");
            String head = request.getParameter("head");
            String personalID = request.getParameter("personalID");
            String phone = request.getParameter("phone");
            String docterID = request.getParameter("docterID");
            String hospital = request.getParameter("hospital");
            String section = request.getParameter("section");
            String introduce = request.getParameter("introduce");
            String score = request.getParameter("score");
            String type = request.getParameter("type");
            String realName = request.getParameter("realName");
            Object[] params = { String.valueOf(UUID.randomUUID()), userName_enroll, password_enroll, name, sex, age,
                    head, personalID, phone, docterID, hospital, section, introduce, score, type, realName };
            int lineNum = new SavaData().savaInfo(sql, params);

            System.out.println("���û�" + name + "ע�����ϵͳ�ˣ�");

            out.println("1");
        } else if (operation.equals("Enroll_had")) {

            String Enroll_userName = request.getParameter("userName");
            String Enroll_password = request.getParameter("password");

            boolean had = false;
            String Enroll_userType = "VIPUser";

            for (int j = 0; j < 3; j++) {

                List<Map<String, Object>> list = new GetData().getUser(Enroll_userType);
                for (int i = 0; i < list.size(); i++) {

                    if (Enroll_userName.equals(list.get(i).get("userName"))
                            && Enroll_password.equals(list.get(i).get("password"))) {

                        out.println("�û�����");
                        havedLogin = true;
                        break;
                    }
                }
                if (had)
                    break;
                if (j == 0)
                    userType = "BaseUser";
                if (j == 1)
                    userType = "Doctor";
            }
            userType = "VIPUser";
            out.println("�û�������");
        }
    }

}
