package kosien.procon.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import su.heartlove.matatabi.R;

/**
 * Created by Owner on 2017/10/21.
 */

public class place_search_adapter extends ArrayAdapter<place_detail_item> {

    private int mResource;
    private ArrayList<place_detail_item> mItems;
    private LayoutInflater mInflater;


    public place_search_adapter(Context context, int resource, ArrayList<place_detail_item> items) {
        super(context, resource, items);
        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    static class ViewHolder {
        TextView placeTitleView;
        TextView placeCatView;
        TextView placeActionView;
        TextView placeColumnView;
        ImageView placeImageView;

    }

    @Override
    public View getView(final int position,View convertView,final ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.place_info_detail, parent, false);
            holder.placeTitleView = (TextView)convertView.findViewById(R.id.place_title);
            holder.placeCatView = (TextView)convertView.findViewById(R.id.place_cat);
            holder.placeActionView = (TextView)convertView.findViewById(R.id.place_action);
            holder.placeColumnView = (TextView)convertView.findViewById(R.id.place_column);
            holder.placeImageView = (ImageView)convertView.findViewById(R.id.place_image);
            convertView.setTag(holder);
        } else {
            holder = (place_search_adapter.ViewHolder) convertView.getTag();
        }

        place_detail_item item = mItems.get(position);


        //テキストビューにリスナーを持たせる
        holder.placeActionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.delete_button);
            }
        });

        return convertView;
    }




}
