package kz.aituaraibol.insdu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kz.aituaraibol.insdu.R;
import kz.aituaraibol.insdu.utils.Item;

/**
 * Created by Aibol on 2/20/2015.
 */
public class GridViewAdapter extends ArrayAdapter<Item> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Item> data = new ArrayList<Item>();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<Item> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        RecordHolder holder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) v.findViewById(R.id.item_text);
            holder.itemImage = (ImageView) v.findViewById(R.id.item_image);
            v.setTag(holder);
        } else {
            holder = (RecordHolder) v.getTag();
        }
        Item item = data.get(position);
        holder.txtTitle.setText(item.getTitle());
        holder.itemImage.setImageBitmap(item.getImage());

        return v;

    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView itemImage;
    }

}
