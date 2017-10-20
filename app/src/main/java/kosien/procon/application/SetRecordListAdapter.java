package kosien.procon.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kosien.procon.application.SampleListItem;
import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/26.
 */


public class SetRecordListAdapter extends ArrayAdapter<SampleListItem> {

    //表示内容
    private int mResource;
    private List<SampleListItem> mItems;
    private LayoutInflater mInflater;


    /**
     * コンストラクタ
     *
     * @param context  コンテキスト
     * @param resource リソースID
     * @param items    リストビューの要素
     */

    public SetRecordListAdapter(Context context, int resource, List<SampleListItem> items) {
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
        // リストビューに表示する要素を取得
        SampleListItem item = mItems.get(position);

        // サムネイル画像を設定
        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        thumbnail.setImageBitmap(item.getThumbnail());

        // タイトルを設定
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(item.getTitle());

        TextView nakami2 = (TextView) view.findViewById(R.id.nakami);
        nakami2.setText(item.getNalami());


        return view;
    }

}


