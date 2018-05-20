package personalDr;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ffd on 2016/3/17.
 */
public class Doctor {

    private String userID;
    private String name;
    private String sex;
    private String head;
    private String personalID;
    private String phone;
    private String docterID;
    private String hospital;
    private String section;
    private String introduce;
    private String score;
    private String type;
    private String realName;
    private int age;
    private JSONObject doctorJson = null;

    public Doctor()
    {
        userID="ffd";
        name="ffd";
        sex="ffd";
        head = "ffd";
        personalID = "ffd";
        phone = "ffd";
        docterID = "ffd";
        hospital = "ffd";
        section = "ffd";
        introduce = "ffd";
        score = "ffd";
        type = "ffd";
        realName = "ffd";
        age = 20;
    }
    public Doctor(JSONObject doctorJson) throws JSONException {
        this.userID = (String) doctorJson.getString("userID");
        this.name = (String) doctorJson.getString("name");
        this.sex = (String) doctorJson.getString("sex");
        this.head = (String) doctorJson.getString("head");
        this.personalID = (String) doctorJson.getString("personalID");
        this.hospital = (String) doctorJson.getString("hospital");
        this.phone = (String) doctorJson.getString("phone");
        this.docterID = (String) doctorJson.getString("docterID");
        this.section = (String) doctorJson.getString("section");
        this.introduce = (String) doctorJson.getString("introduce");
        this.score = (String) doctorJson.getString("score");
        this.type = (String) doctorJson.getString("type");
        this.realName = (String) doctorJson.getString("realName");
        this.age = (int) doctorJson.getInt("age");
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocterID() {
        return docterID;
    }

    public void setDocterID(String docterID) {
        this.docterID = docterID;
    }

    public String getHospital() {
        return hospital;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}