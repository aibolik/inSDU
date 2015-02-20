package kz.aituaraibol.insdu.utils;

import android.graphics.Bitmap;

import java.util.ArrayList;
/**
 * Created by Aibol on 2/20/2015.
 */


public class Post {
    public int id, from_id, owner_id, signer_id, likes, comments_num, reposts;
    public String post_type, text;

    String date;

    public ArrayList<Photo> photos = new ArrayList<Photo>();

    public Post(String text, int likes, int comments_num, int reposts, int id) {
        this.text = text;
        this.id = id;
        this.likes = likes;
        this.comments_num = comments_num;
        this.reposts = reposts;
    }

    ArrayList<Bitmap> imgs = new ArrayList<Bitmap>();


    public Post(String text, int likes, int comments_num, int reposts, int id, ArrayList<Bitmap> imgs) {
        this.text = text;
        this.id = id;
        this.likes = likes;
        this.comments_num = comments_num;
        this.reposts = reposts;
        this.imgs = imgs;
    }

    public void addPhotos(ArrayList<Photo> p) {
        for(int i = 0; i < p.size(); i++) {
            Photo x = p.get(i);
            photos.add(x);
        }
    }

    public ArrayList<Photo> getPhotoList() {
        return photos;
    }

}

