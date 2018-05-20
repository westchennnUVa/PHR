package tijianguahao;

import java.io.Serializable;

/**
 * Created by 吴俊达 on 2016/3/16.
 */
public class Examination implements Serializable {
    private int head;       //头像
    private String name;    //名字
    private String date;    //时间
    private String people;  //人群
    private String id;       //id
    private String hospital;//医院名
    private String recordType;//类型
    private String price;    //价格


    public Examination(){}

    //顺序 head name date people id hospital recordType price
    public Examination(int head, String name, String date, String people, String id, String hospital, String recordType, String price){
        this.head = head;
        this.name = name;
        this.date = date;
        this.people = people;
        this.id = id;
        this.hospital = hospital;
        this.recordType = recordType;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getPeople() {
        return people;
    }
    public void setPeople(String people) {
        this.people = people;
    }

    public String getHospital() {
        return hospital;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public int getHead() {
        return head;
    }
    public void setHead(int head) {
        this.head = head;
    }

    public String getRecordType() {
        return recordType;
    }
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
