package kosien.procon.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/20.
 */

public class fragment_schedule_create_adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    ArrayList<fragment_schedule_create_item> mItems;
    private int mResourceId;

    static class ViewHolder {
        TextView textView;
        ImageButton deleteButton;
    }

    public fragment_schedule_create_adapter(Context context, int resource, ArrayList<fragment_schedule_create_item> items) {
        this.mResourceId = resource;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItems = items;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mResourceId, parent, false);
            holder = new ViewHolder();
            holder.deleteButton = convertView.findViewById(R.id.delete_button);
            holder.textView = convertView.findViewById(R.id.place_name);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //リストのアイテムデータ取得
        holder.textView.setText(mItems.get(position).getPlaceName());

        //ボタンのリスナー
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView) parent).performItemClick(view, position, R.id.delete_button);
            }
        });
        return convertView;

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
