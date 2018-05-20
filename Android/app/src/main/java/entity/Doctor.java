package entity;

import android.os.Parcel;

/**
 * Created by mating on 16/3/12.
 */
public class Doctor extends NormalUser{

    private String doctorNum;
    private String hospitalName;

    public Doctor(String id,String Username, String pwd, String name, String sexy,
                  String telephone, String IDCard, String age,String bmp,String doctorNum,
                  String hospitalName){
        super(id,Username,pwd,name,sexy,telephone,IDCard,age,bmp);
        this.doctorNum = doctorNum;
        this.hospitalName = hospitalName;
    }

    public String getDoctorNum() {
        return doctorNum;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setDoctorNum(String doctorNum) {
        this.doctorNum = doctorNum;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.doctorNum);
        dest.writeString(this.hospitalName);
    }

    protected Doctor(Parcel in) {
        super(in);
        this.doctorNum = in.readString();
        this.hospitalName = in.readString();
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        public Doctor createFromParcel(Parcel source) {
            return new Doctor(source);
        }

        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };
}
