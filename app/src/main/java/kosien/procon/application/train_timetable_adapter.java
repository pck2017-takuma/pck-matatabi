package kosien.procon.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/20.
 */

public class train_timetable_adapter extends ArrayAdapter<train_timetable_list> {

    private int mResource;
    private ArrayList<train_timetable_list> mItems;
    private LayoutInflater mInflater;

    public train_timetable_adapter(Context context, int resource, ArrayList<train_timetable_list> items) {
        super(context, resource, items);
        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        train_timetable_list item = mItems.get(position);

        //こ↑こ↓からレイアウト割り当て
        //到着時刻
        TextView arriveTimeView = (TextView) view.findViewById(R.id.arrive_time);
        arriveTimeView.setText(item.getArriveTime());
        //発車時刻
        TextView departTime = (TextView) view.findViewById(R.id.depart_time);
        departTime.setText(item.getDepartTime());
        //駅名
        TextView stationName = (TextView) view.findViewById(R.id.station_name);
        stationName.setText(item.getStationName());
        //アイコン
        ImageView thumbnail = view.findViewById(R.id.station_icon);
        thumbnail.setImageBitmap(item.getStationIcon());

        return view;
    }


}
