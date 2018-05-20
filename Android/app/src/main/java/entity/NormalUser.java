package entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mating on 16/3/12.
 */
public class NormalUser implements Parcelable {
    private String userID;
    private String UserName;
    private String pwd;
    private String name;
    private String sexy;
    private String telephone;
    private String IDCard;
    private String age;
    private String bmp;

    public NormalUser(){

    }

    public NormalUser(String userID, String Username, String pwd, String name, String sexy, String telephone, String IDCard, String age,String bmp){
        this.userID = userID;
        this.UserName = UserName;
        this.name = name;
        this.pwd = pwd;
        this.sexy = sexy;
        this.telephone = telephone;
        this.IDCard = IDCard;
        this.age = age;
        this.bmp=bmp;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSexy() {
        return sexy;
    }
    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getIDCard() {
        return IDCard;
    }
    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    //byte[] bmp
    public String getBmp()
    {
        return bmp;
    }
    public void setBmp(String bmp)
    {
        this.bmp=bmp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UserName);
        dest.writeString(this.pwd);
        dest.writeString(this.name);
        dest.writeString(this.sexy);
        dest.writeString(this.telephone);
        dest.writeString(this.IDCard);
        dest.writeString(this.age);
        dest.writeString(this.bmp);
    }

    protected NormalUser(Parcel in) {
        this.UserName = in.readString();
        this.pwd = in.readString();
        this.name = in.readString();
        this.sexy = in.readString();
        this.telephone = in.readString();
        this.IDCard = in.readString();
        this.age = in.readString();
        this.bmp = in.readString();
    }

    public static final Creator<NormalUser> CREATOR = new Creator<NormalUser>() {
        public NormalUser createFromParcel(Parcel source) {
            return new NormalUser(source);
        }

        public NormalUser[] newArray(int size) {
            return new NormalUser[size];
        }
    };
}
