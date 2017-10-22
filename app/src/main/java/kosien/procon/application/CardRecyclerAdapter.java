package kosien.procon.application;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import su.heartlove.matatabi.R;

/**
 * Created by Owner on 2017/10/22.
 */


public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder>{
    private ArrayList<place_detail_item>listItem;
    private Context context;

    public CardRecyclerAdapter(Context context,ArrayList<place_detail_item> data) {
        super();
        this.listItem = data;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        place_detail_item item = listItem.get(position);
        vh.placeTitleView.setText(item.getPlaceTitle());
        vh.placeColumnView.setText(item.getPlaceColumn());
        vh.placeImageView.setImageResource(R.mipmap.ic_launcher);
        vh.placeCatView.setText(item.getPlaceCategory());

        //リスナー


    }

    @Override
    public CardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.place_info_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);



        return viewHolder;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView placeTitleView;
        TextView placeCatView;
        TextView placeActionView;
        TextView placeColumnView;
        ImageView placeImageView;

        public ViewHolder(View convertView) {
            super(convertView);
            placeTitleView = (TextView)convertView.findViewById(R.id.place_title);
            placeCatView = (TextView)convertView.findViewById(R.id.place_cat);
            placeActionView = (TextView)convertView.findViewById(R.id.place_action);
            placeColumnView = (TextView)convertView.findViewById(R.id.place_column);
            placeImageView = (ImageView)convertView.findViewById(R.id.place_image);



        }
    }
}
