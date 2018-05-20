package adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnthonySSS on 2016/3/13.
 */
public class Data_Manager3 {

    public Data_Manager3() {
    }

    public List<Registration> getData() {

        Registration registration1 = new Registration("华西医院", "口腔科", "66", "普通", "2016-3-13");
        Registration registration2 = new Registration("华西医院", "骨科", "88", "普通", "2016-3-13");
        Registration registration3 = new Registration("华西医院", "口腔科", "6666", "专家", "2016-3-13");
        Registration registration4 = new Registration("华西医院", "眼科", "77", "普通", "2016-3-13");
        Registration registration5 = new Registration("华西医院", "口腔科", "66", "普通", "2016-3-13");
        Registration registration6 = new Registration("华西医院", "口腔科", "66", "专家", "2016-3-13");
        Registration registration7 = new Registration("华西医院", "口腔科", "66", "普通", "2016-3-13");
        Registration registration8 = new Registration("华西医院", "口腔科", "66", "普通", "2016-3-13");


        List<Registration> data = new ArrayList<Registration>();
        data.add(registration1);
        data.add(registration2);
        data.add(registration3);
        data.add(registration4);
        data.add(registration5);
        data.add(registration6);
        data.add(registration7);
        data.add(registration8);

        return data;
    }
}