package adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnthonySSS on 2016/3/12.
 */
public class Data_Manager2 {

    public Data_Manager2(){}

    public List<Comment> getData(){

        Comment comment1 = new Comment("流感来袭","2016-3-12-20:16","注意防治");
        Comment comment2 = new Comment("亚健康","2016-3-12-20:16","注意防治");
        Comment comment3 = new Comment("华西医院","2016-3-12-20:16","注意防治");
        Comment comment4 = new Comment("流感来袭","2016-3-12-20:16","注意防治");
        Comment comment5 = new Comment("流感来袭","2016-3-12-20:16","注意防治");
        Comment comment6 = new Comment("流感来袭","2016-3-12-20:16","注意防治");
        Comment comment7 = new Comment("流感来袭","2016-3-12-20:16","注意防治");

        List<Comment> data =new ArrayList<Comment>();
        data.add(comment1);
        data.add(comment2);
        data.add(comment3);
        data.add(comment4);
        data.add(comment5);
        data.add(comment6);
        data.add(comment7);

        return data;

    }
}

