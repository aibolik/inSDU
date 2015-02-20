package kz.aituaraibol.insdu.utils;

import android.widget.ImageView;

/**
 * Created by Aibol on 2/20/2015.
 */
public class Photo {
    public String src;
    int width, height;
    String text;

    ImageView img;

    public Photo(String src, int width, int height) {
        this.src = src;
        this.width = width;
        this.height = height;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
