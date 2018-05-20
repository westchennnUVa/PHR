package adapter;

import java.io.Serializable;

/**
 * Created by AnthonySSS on 2016/3/11.
 */
public class Function  implements Serializable {

    //图标
    private int icon;
    //名称
    private String name;

    public Function(int icon,String name){
        this.icon = icon;
        this.name = name;
    }
    public String getFunction() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

}

