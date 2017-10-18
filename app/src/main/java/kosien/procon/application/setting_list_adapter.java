package kosien.procon.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import su.heartlove.matatabi.R;

/**
 * Created by Owner on 2017/10/18.
 */

public class setting_list_adapter extends ArrayAdapter<setting_list_item> {

    LayoutInflater layoutInflater;
    ArrayList<setting_list_item> settingListItem;
    setting_list_item item;
    Context context;


    public setting_list_adapter(Context context, int resource, ArrayList<setting_list_item> objects) {
        super(context, resource, objects);
        settingListItem = objects;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setSettingListItem(ArrayList<setting_list_item>listItem){
        this.settingListItem = listItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.fragment_setting_list,null);
        }

        //リストのアイテム取得
        item = this.getItem(position);

        //テキストビュー（クリック機能付き）
        TextView clickText = (TextView)convertView.findViewById(R.id.setting_button);
        //リスナーセット
        clickText.setOnClickListener(new clickTextListener());

        //テキストビューステータス表示
        TextView statusText = (TextView)convertView.findViewById(R.id.setting_status);

        //デバック用
        statusText.setText("Hello");

        //本当はこちら
        if(statusText != null){
            statusText.setText(item.getStatusText());

        }

        return convertView;

    }

    //クリックイベント用リスナー
    class clickTextListener implements View.OnClickListener{
        public void onClick(View v){
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
    }




}
