package kosien.procon.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.ConnectServer;
import kosien.procon.application.matatabidb.basicTimeSearch;
import kosien.procon.application.matatabidb.mydatabase.RouteInfo;
import kosien.procon.application.matatabidb.mydatabase.RouteInfoDaoItem;
import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import kosien.procon.application.matatabidb.mydatabase.travelScheduleDao;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/01.
 */

public class fragment_travel extends Fragment{
    //フラグメントで表示する内容
    private TextView mTextView;
    //スケジュールデータベース
    travelScheduleDao scheduleDB;
    //現在の行程
    ArrayList<RouteInfo>routeList;
    //スケジュール一覧
    ArrayList<travelSchedule>travelList;
    //現在の旅行
    infoTravel bundleData;
    //現在の場所
    travelSchedule nowPlace;
    //観光地データベースにアクセス
    placeInfoDao placeInfoDB;
    //行程データベース更新
    RouteInfoDaoItem routeDB;
    //訪れる場所の情報
    placeInfomation nowPlaceData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.fragment_travel, container, false);
    }



    @Override
    public void onViewCreated(final View view, Bundle saveInstanceState) {
        scheduleDB = new travelScheduleDao(getContext());
        //バンドルされた旅行データを取得
        Bundle bundle = getArguments();
        bundleData = (infoTravel)bundle.getSerializable("infoTravel");
        //観光地データベースオープン
        placeInfoDB = new placeInfoDao(getContext());
        routeDB = new RouteInfoDaoItem(getContext());

        //訪問先データ全件取得
        if(scheduleDB.findSchedule(bundleData.gettravelNum())){
            travelList = scheduleDB.getNowTravelList();
        }

        //現在の進んだ行程があるか確認する

        if (scheduleDB.findNowSchedule(bundleData.gettravelNum())) {
            nowPlace = scheduleDB.getNowTravel();
        } else {
            //ない場合はトラベルナンバーが１番目のscheduleをアクティブにしてデータベースに登録する

            for (travelSchedule x : travelList) {
                if (x.getTravelNum() == 0) {
                    x.setFlag(1);
                    scheduleDB.sava_diary(x);
                    nowPlace = x;
                }
            }
        }

        //観光地の情報を取得する
        if(placeInfoDB.findPlaceInfo(nowPlace.getPlaceName(),placeInfomation.PLACE_NAME)){
            //１つしかデータが見つからないという信頼の上で
            nowPlaceData = placeInfoDB.getSearchResult().get(0);
        }else{
            Toast.makeText(getContext(),"ヤバい、可笑しいことになった！",Toast.LENGTH_SHORT).show();
        }

        //起点を決める（将来的：現在位置　現状：香川高等専門学校詫間キャンパス学生課）
        String startStation = "33.311139,134.010361";
        String goalStation = nowPlaceData.getPlaceLatitude() + "," + nowPlaceData.getPlaceLongitude();
        basicTimeSearch url = new basicTimeSearch(startStation, goalStation);



        //経路検索を行いその結果を格納する
        routeList = getJsonFromAsync(url.getSearchLink()).get(0);
        for(int i = 0; i < routeList.size();i++){
            RouteInfo tmp = routeList.get(i);
            //スケジュールとどの行程かを記憶する
            tmp.setTravelNum(bundleData.getTravelNum());
            tmp.setScheduleNum(nowPlace.getRouteNum());
            //データベース登録
            routeDB.sava_diary(tmp);
        }



        // ボタンを生成
        super.onViewCreated(view, saveInstanceState);
        Button accept_button = (Button) view.findViewById(R.id.button1);
        Button view_button = (Button)view.findViewById(R.id.button2);

        //前の時刻表示
        accept_button.setText("前へ");
        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //次の時刻表示
        view_button.setText("次へ");
        view_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


            }
        });




    }

    private ArrayList<ArrayList<RouteInfo>> getJsonFromAsync(String url) {

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
        return asyncGet.getParseData();
    }

//    boolean moveNextStation(){
//
//    }
//
//    boolean moveBeforeStation(){
//
//    }



}
