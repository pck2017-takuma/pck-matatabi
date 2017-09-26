package kosien.procon.application;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import kosien.procon.application.matatabidb.SetRecordListAdapter;
import kosien.procon.application.matatabidb.mydatabase.StationInfoDao;
import kosien.procon.application.matatabidb.mydatabase.Station_Infomation;
import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;

import su.heartlove.matatabi.R;

/**
 * Created by i15317 on 2017/09/26.
 */

public class SearchFragment extends Fragment {

    //フラグメントで表示する内容
    private TextView mTextView;
    private StationInfoDao stationHelper;
    private String searchWord = new String();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        stationHelper = new StationInfoDao(getContext());
        return inflater.inflate(R.layout.fragment_record,container,false);


    }

    //ビューを生成し終わった後に呼ばれるメソッド

    @Override
    public void onViewCreated(View view,Bundle saveInstanceState) {

        // super.onViewCreated(view,saveInstanceState);
        //データベースからデータを取得する
        Bundle bundle = getArguments();
        searchWord = bundle.getString("NAME");

        ArrayList<Station_Infomation> stationData = new ArrayList<Station_Infomation>();

        if(stationHelper.findStationInfo(searchWord,Station_Infomation.STATION_NAME)){
            stationData = stationHelper.getSearchResult();
        }

        ListView listView = (ListView)view.findViewById(R.id.sample_listview);
        ArrayList<SampleListItem> listItems = new ArrayList<>();

        if(stationData.size() == 0){
            Toast.makeText(getContext(),"データが存在しません",Toast.LENGTH_SHORT).show();
        }else {
           // for (int i = 0; i < stationData.size(); i++) {
             for(Station_Infomation x:stationData){
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
                SampleListItem item = new SampleListItem(bmp, x.getStationname(), x.getBetname());
                listItems.add(item);
            }
            // 出力結果をリストビューに表示
            SetRecordListAdapter adapter = new SetRecordListAdapter(getContext(), R.layout.samplelist_item, listItems);
            listView.setAdapter(adapter);


            // アイテムクリック時ののイベントを追加
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View view, int pos, long id) {

                    // 選択アイテムを取得
                    ListView listView2 = (ListView)parent;
                    String item = (String)listView2.getItemAtPosition(pos);

                    // 通知ダイアログを表示
                    Toast.makeText(getContext(),
                            item, Toast.LENGTH_LONG
                    ).show();
                }




            });
        }


    }


}
