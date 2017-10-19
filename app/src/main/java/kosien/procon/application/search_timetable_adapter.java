package kosien.procon.application;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/20.
 */

public class search_timetable_adapter extends ArrayAdapter<search_timetable_list> {

    private int mResource;
    private ArrayList<search_timetable_list>mItems;
    private LayoutInflater mInflater;

    public search_timetable_adapter(Context context,int resource,ArrayList<search_timetable_list>item){
        super(context,resource,item);

        mResource = resource;
        mItems = item;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;
        if(convertView != null){
            view = convertView;
        }else{
            view = mInflater.inflate(mResource,null);
        }

        //リストビューに表示する要素の選択
        search_timetable_list item = mItems.get(position);

        //こ↑こ↓からリソース割り当て
        //列車時刻
        TextView trainTimeView = (TextView)view.findViewById(R.id.train_time);
        //値をセットする
        trainTimeView.setText(item.getTrainTime());

        //列車詳細情報
        TextView trainDetailView = (TextView)view.findViewById(R.id.train_detail);
        trainDetailView.setText(item.getTrainDetail());

        //列車行き先
        TextView trainDepatureView = (TextView)view.findViewById(R.id.train_depature);
        trainDepatureView.setText(item.getTrainDepature() + "　行");


        return view;


    }


}
