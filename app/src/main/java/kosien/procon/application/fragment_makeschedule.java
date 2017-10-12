package kosien.procon.application;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.RouteInfo;
import su.heartlove.matatabi.R;

public class fragment_makeschedule extends AppCompatActivity {

    /**
     * アクティビティ生成時に呼ばれる
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //経路情報取得
        ArrayList<RouteInfo> routeInfo = new ArrayList<RouteInfo>();

        // レイアウトからリストビューを取得
        ListView listView = (ListView)findViewById(R.id.sample_listview);

        // リストビューに表示する要素を設定
        ArrayList<makeScheduleListItem> listItems = new ArrayList<>();

        for(RouteInfo x:routeInfo){
        //for (int i = 0; i < 3; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            //タイトル、中身、時間
            makeScheduleListItem item = new makeScheduleListItem(bmp,x.getRouteTrain(),x.getRouteDeparture(),x.getrouteDepttime());
            listItems.add(item);
        }

        // 出力結果をリストビューに表示
        makeScheduleListAdapter adapter = new makeScheduleListAdapter(this, R.layout.samplelist_item, listItems);
        listView.setAdapter(adapter);
    }

}