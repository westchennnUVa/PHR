package adapter;

/**
 * Created by AnthonySSS on 2016/3/13.
 */
public class Registration {
    //price
    private String hospital;
    //
    private String department;

    private String price ;
    private String type;
    private String time;

    public Registration(String hospital,String department,String price,String  type,String time){
        this.hospital = hospital;
        this.department= department;
        this.price = price;
        this.type = type;
        this.time =time;
    }
    public String getHospital() {
        return hospital;
    }

    public String getDepartment() {
        return department;
    }

    public String getPrice(){
        return price;
    }

    public String getType(){
        return type;
    }

    public String getTime(){
        return time;
    }
}
