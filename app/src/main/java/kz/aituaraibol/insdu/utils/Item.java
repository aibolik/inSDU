package kz.aituaraibol.insdu.utils;

import android.graphics.Bitmap;

/**
 * Created by Aibol on 2/20/2015.
 */
public class Item {
    private Bitmap image;
    private String title;

    public Item(Bitmap image, String title) {
        this.image = image;
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
