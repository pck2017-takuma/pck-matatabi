package kosien.procon.application;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.ConnectServer;
import kosien.procon.application.matatabidb.mydatabase.StationInfoDao;
import kosien.procon.application.matatabidb.mydatabase.Station_Infomation;

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
        return inflater.inflate(R.layout.fragment_searchlist,container,false);


    }

    //ビューを生成し終わった後に呼ばれるメソッド
    @Override
    public void onViewCreated(View view,Bundle saveInstanceState) {
        stationHelper = new StationInfoDao(getContext());

        // super.onViewCreated(view,saveInstanceState);
        //データベースからデータを取得する
        Bundle bundle = getArguments();
        searchWord = bundle.getString("NAME");

        ArrayList<Station_Infomation> stationData = null;

        basicTimeSearch makelink = new basicTimeSearch("岡山","東京");
        System.out.println(makelink.getSearchLink());
       getJsonFromAsync(makelink.getSearchLink());


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
                SampleListItem item = new SampleListItem(bmp, x.getStationname(), x.getKananame());
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

    private void getJsonFromAsync(String url) {

        ConnectServer asyncGet = new ConnectServer(new ConnectServer.AsyncCallback() {
            //非同期通信が開始される前に呼び出される
            public void onPreExecute() {}
            //非同期通信が更新されたと時に呼び出される
            public void onProgressUpdate(int progress) {}
            //非同期通信がキャンセルされたt気に呼び出される
            public void onCancelled() {}
            //非同期通信が完了した時点で呼び出される
            public void onPostExecute(String result) {

                try {
                    JSONObject json = new JSONObject(result);
                    parseSearchData resultParse = new parseSearchData(json.getJSONObject("ResultSet"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        asyncGet.execute(url);
    }

}
