package tijianguahao;

/**
 * Created by 吴俊达 on 2016/3/18.
 */
public class Registration {
    private String name;
    private String ID;
    private String phone;
    private String hospital;
    private String time;
    private String keshi;

    public Registration(){}

    public Registration(String name, String ID, String phone, String hospital, String time, String keshi){
        this.name = name;
        this.ID = ID;
        this.phone = phone;
        this.hospital = hospital;
        this.time = time;
        this.keshi = keshi;
    }

    public String getHospital() {
        return hospital;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKeshi() {
        return keshi;
    }
    public void setKeshi(String keshi) {
        this.keshi = keshi;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
