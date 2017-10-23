package kosien.procon.application;

import android.content.Context;
import android.media.Image;
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
 * Created by procon-kyougi on 2017/10/23.
 */

public class place_detail_list_adapter extends ArrayAdapter<place_detail_list_item>{

    private LayoutInflater mInflater;
    ArrayList<place_detail_list_item> mItems;
    private int mResourceId;

    static class ViewHolder{
        //アイコン
        ImageView image;
        TextView column;
    }

    //コンストラクタ
    public place_detail_list_adapter(Context context, int resource, ArrayList<place_detail_list_item>items){
        super(context,resource,items);
        this.mResourceId = resource;
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItems = items;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.place_detail_list, parent, false);
            holder.image = (ImageView)convertView.findViewById(R.id.list_image);
            holder.column = (TextView)convertView.findViewById(R.id.list_colum);

            convertView.setTag(holder);
        } else {
            holder = (place_detail_list_adapter.ViewHolder) convertView.getTag();
        }

        place_detail_list_item item = mItems.get(position);
        holder.column.setText(item.getColumnName());

        /**
         * 画像が出来たら
         */

       // holder.imageをゴニョゴニョしてね



        //テキストビューにリスナーを持たせる
        holder.column.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.delete_button);
            }
        });

        return convertView;
    }







}
