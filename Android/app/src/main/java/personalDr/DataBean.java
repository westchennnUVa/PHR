package personalDr;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ffd on 2016/3/17.
 */
public class DataBean implements Serializable {

    //最近聊天列表
    private List<Doctor> doctorList;
    //组名列表
    private List<String> groupList;
    //组员成员列表
    private List<List<Doctor>> groupDoctorList;

    public DataBean(List<Doctor> doctorList, List<String> groupList, List<List<Doctor>> groupDoctorList) {
        this.doctorList = doctorList;
        this.groupList = groupList;
        this.groupDoctorList = groupDoctorList;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<String> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
    }

    public List<List<Doctor>> getGroupDoctorList() {
        return groupDoctorList;
    }

    public void setGroupDoctorList(List<List<Doctor>> groupDoctorList) {
        this.groupDoctorList = groupDoctorList;
    }
}
