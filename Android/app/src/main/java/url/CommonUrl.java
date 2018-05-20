package url;

public class CommonUrl {

    public static final String URL = "10.22.48.251";


    public static final String NEWS = "http://" + URL +":8080/News";

    public static final String HEALTH = "http://" + URL +":8080/Health";

    /*
    数据类型：
    "id"  "head"  "name"  "time"  "type"  "content"   "prizeNum"  "commentNum"
     */

    public static final String PHR = "http://" + URL +":8080/PHR_Servlet";

    /*
    数据类型：
    "id"  "height"    "weight"    "blood_pressure"    "vital_capacity "vision_left"
    "vision_right"    "heart_rate"    "temperature"   "hemoglobin"    "platelet"
    "urine"   "urine_red_blood_cell"    "blood_type"  "Measurements_1"  "Measurements_2"
    "Measurements_3"  "score"
     */

//    public static final String Logon = "http://" + URL +":8080/PHR%20Server/Logon";
    public static final String Login = "http://"+  URL + ":8080/Login";


//    PHR%20Server
    /*
    说明：
    登录成功即返回用户资料，登录失败则返回登录失败
    数据类型：
    "id"  "head"    "name"    "userName"    "password"    "sex" "personalID"  "phone"
     */
    public static final String EXAMINATION = "http://" + URL +":8080/Consumption";
    public static final String Registration = "http://" + URL +":8080/Registration";
    public static final String main = "http://" + URL +":8080/main";
    public static final String Treatment = "http://" + URL +":8080/Treatment";
    public static final String getOne = "http://" + URL +":8080/getOne";
    public static final String GetUser = "http://" + URL +":8080/GetUser";
    public static final String Chat = "http://" + URL +":8080/Chat";
    public static final String GetAllDoctor = "http://" + URL +":8080/GetAllDoctor";

}
