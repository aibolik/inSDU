package kz.aituaraibol.insdu.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kz.aituaraibol.insdu.R;
import kz.aituaraibol.insdu.utils.JSONParser;
import kz.aituaraibol.insdu.utils.Photo;
import kz.aituaraibol.insdu.utils.Post;

public class SDUstories extends ActionBarActivity {

    private ListView lv;

    JSONParser jParser = new JSONParser();

    ArrayList<Post> allStories = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdustories);


        lv = (ListView) findViewById(R.id.postsView);

        new PostsRequest().execute();

    }

    class PostsRequest extends AsyncTask<String, String, String> {

        ProgressDialog pDialog;

        public String handleBR(String x) {
            String res = "";
            for(int i = 0; i < x.length(); i++) {
                if(i < x.length()-4 && x.charAt(i) == '<' && x.charAt(i+1) == 'b' && x.charAt(i+2) == 'r' && x.charAt(i+3) == '>') {
                    i += 3;
                    res += "\n";
                }
                else {
                    res += x.charAt(i);
                }
            }
            return res;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SDUstories.this);
            pDialog.setMessage("Loading posts. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            String url = "https://api.vk.com/method/wall.get";

            params.add(new BasicNameValuePair("owner_id", "-53746469"));
            params.add(new BasicNameValuePair("count", "10"));

            JSONObject json = jParser.makeHttpRequest(url, "GET", params);

            Log.d("ALL POSTS", json.toString());

            JSONArray allposts = null;

            try {
                allposts = json.getJSONArray("response");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Log.d("ARRAY", allposts.toString());

            for(int i = 1; i < allposts.length(); i++) {
                JSONObject post;
                try {
                    post = allposts.getJSONObject(i);

                    int id = post.getInt("id");
                    String text = handleBR(post.getString("text"));
                    int comments = post.getJSONObject("comments").getInt("count");
                    int likes = post.getJSONObject("likes").getInt("count");
                    int reposts = post.getJSONObject("reposts").getInt("count");


                    ArrayList<Photo> photos = new ArrayList<Photo>();

                    JSONArray att = null;
                    try {
                        att = post.getJSONArray("attachments");

                        for(int j = 0; j < att.length(); j++) {
                            JSONObject one = att.getJSONObject(j);
                            String type = one.getString("type");

                            if(type.equals("photo")) {

                                JSONObject obj = one.getJSONObject("photo");

                                int width = obj.getInt("width");
                                int height = obj.getInt("height");
                                String src = obj.getString("src_big");

                                //ImageView image = null;

                                //Bitmap bm = null;

                                //new DownloadImageTask(bm).execute(src);

                                //imgs.add(bm);

                                Photo newone = new Photo(src, width, height);

                                //Log.d("PARAMS: " + id + " ." + j, newone.src);

                                photos.add(newone);
                            }

                            else if(type.equals("video")) {
                                JSONObject obj = one.getJSONObject("video");

                                String title = obj.getString("title");
                                text += "[VIDEO]\n" + title;
                                String src = obj.getString("image_big");

                                Photo newone = new Photo(src, 0, 0);

                                photos.add(newone);
                            }

                        }
                    } catch(Exception e) {

                    }

                    Post story = new Post(text, likes, comments, reposts, id);
                    story.addPhotos(photos);

                    allStories.add(story);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result) {

            //Log.d("ALL STORIES", allStories.get(4).photos.size() + "");

            pDialog.dismiss();

            lv.setAdapter(new MyAdapter());

            super.onPostExecute(result);
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return allStories.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int pos, View view, ViewGroup parent) {

            Post bir = allStories.get(pos);

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.one_post, parent, false);

            TextView tvText = (TextView) row.findViewById(R.id.text);
            TextView tvCom = (TextView) row.findViewById(R.id.com_count);
            TextView tvRepost = (TextView) row.findViewById(R.id.repost_count);
            TextView tvLikes = (TextView) row.findViewById(R.id.like_count);

            tvText.setText(bir.text);
            tvCom.setText(Integer.toString(bir.comments_num));
            tvRepost.setText(Integer.toString(bir.reposts));
            tvLikes.setText(Integer.toString(bir.likes));

            //Log.d("NUMBER OF PHOTOS", bir.photos.size() + "");

            LinearLayout main = (LinearLayout) row.findViewById(R.id.main);

            if(bir.photos.size() > 0) {

                //Log.d("PHOTOS ARE ", "PRESENT");

                for(int j = 0; j < bir.photos.size(); j++) {
                    Photo one = bir.photos.get(j);
					/*Log.d("PHOTOS ARE ", "PRESENT");
					Bitmap one = bir.imgs.get(j);
					image.setImageBitmap(one);*/
                    ImageView image = (ImageView) row.findViewById(R.id.image);
                    new DownloadImageTask(image, one).execute(one.src);

                }
            }
            return row;
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        Photo sizes;

        public DownloadImageTask(ImageView bmImage, Photo x) {
            this.bmImage = bmImage;
            this.sizes = x;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                //mIcon11.setHeight(sizes.height);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
