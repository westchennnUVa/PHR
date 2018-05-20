package entity;

import android.graphics.Bitmap;

/**
 * Created by mating on 16/3/13.
 */
public class Item {
    private Bitmap picture;

    public Item(){

    }
    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
