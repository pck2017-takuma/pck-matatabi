package kosien.procon.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/20.
 */


public class timetable_list_adapter extends ArrayAdapter<timetable_list_item> {
    private int mResource;
    private ArrayList<timetable_list_item> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    //コンストラクタ
    public timetable_list_adapter(Context context, int resource, ArrayList<timetable_list_item> items) {
        super(context, resource, items);
        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //２つのImageViewと５つのTextViewを表示する

        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }


        //リストビューに表示する要素を取得する
        timetable_list_item item = mItems.get(position);

        //発駅時刻生成
//        TextView timeStart = view.findViewById(R.id.time_start);
//        timeStart.setText(item.getStartTime());
//        //発駅情報生成
//        TextView startStation = view.findViewById(R.id.time_start_station);
//        startStation.setText(item.getStartStation());
//        //着駅時刻生成
//        TextView timeEnd = view.findViewById(R.id.time_end);
//        timeEnd.setText(item.getEndTime());
//        //着駅情報生成
//        TextView endStation = view.findViewById(R.id.time_end_station);
//        endStation.setText(item.getEndStation());
//        //列車情報
//        TextView trainInfo = view.findViewById(R.id.train_column);
//        trainInfo.setText(item.getTrainInfo());

        return view;


    }

}



