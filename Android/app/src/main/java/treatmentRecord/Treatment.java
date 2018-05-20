package treatmentRecord;
import java.io.Serializable;
/**
 * Created by 吴俊达 on 2016/3/19.
 */
public class Treatment implements Serializable{

    private String userID;       //用户id
    private String time;         //时间
    private String date;         //日期
    private String doctorName;  //医生名称
    private String doctorID;    //医生id
    private String hospital;    //医院名称
    private String section;     //科室
    private String doctorPhone;//医生电话
    private String kind;        //病症
    private String situation;  //病状
    private String method;     //诊疗方案
    private String price;      //价格

    public Treatment(){}

    public Treatment(String userID, String time, String date, String doctorName, String doctorID, String hospital, String section, String doctorPhone, String kind, String situation, String method, String price){
        this.userID = userID;
        this.time = time;
        this.date = date;
        this.doctorName = doctorName;
        this.doctorID = doctorID;
        this.doctorPhone = doctorPhone;
        this.hospital = hospital;
        this.section = section;
        this.doctorPhone = doctorPhone;
        this.kind = kind;
        this.situation = situation;
        this.method = method;
        this.price = price;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospital() {
        return hospital;
    }

    public String getDoctorID() {
        return doctorID;
    }
    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
