package kosien.procon.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import su.heartlove.matatabi.R;

public class makeScheduleListAdapter extends ArrayAdapter<makeScheduleListItem> {

    private int mResource;
    private List<makeScheduleListItem> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */
    public makeScheduleListAdapter(Context context, int resource, List<makeScheduleListItem> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        }
        else {
            view = mInflater.inflate(mResource, null);
        }

        // リストビューに表示する要素を取得
        makeScheduleListItem item = mItems.get(position);

        // サムネイル画像を設定
//        ImageView thumbnail = view.findViewById(R.id.thumbnail);
//        thumbnail.setImageBitmap(item.getThumbnail());
//
//        // タイトルを設定
//        TextView title = view.findViewById(R.id.title);
//        title.setText(item.getTitle());
//
//        TextView nakami = view.findViewById(R.id.nakami);
//        nakami.setText(item.getNalami());
//
//        TextView time = view.findViewById(R.id.jikan);
//        time.setText(item.getTime());


        return view;
    }
}