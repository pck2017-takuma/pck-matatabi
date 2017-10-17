package kosien.procon.application;

import android.content.Context;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import kosien.procon.application.matatabidb.mydatabase.travelScheduleDao;

/**
 * Created by i15317 on 2017/10/16.
 */

//旅行情報管理支援クラス

public class travelStatusInfoHelper {

    //コンテキスト
    Context myContext;

    //データベース//
    //旅行管理
    infoTravelDao travelHelper;
    //観光地情報
    placeInfoDao placeHelper;
    //旅行行程
    travelScheduleDao scheduleHelper;
    
    //ここから状態保持//
    //現在の旅行情報を取得する
    infoTravel nowTravel;
    //現在訪れている観光地の情報を取得する
    //行程一覧
    ArrayList<travelSchedule> scheduleList;
    //現在の行程
    travelSchedule nowSchedule;

    //コンストラクタ
    travelStatusInfoHelper(Context context){
        myContext = context;
        //データベースオープン
        travelHelper = new infoTravelDao(myContext);
        placeHelper = new placeInfoDao(myContext);
        scheduleHelper = new travelScheduleDao(myContext);
    }


    //現在の行程取得などの初期処理を行う
    public void travelInitializer(){
        
        //現在の旅行データが存在するか照会する
        boolean travelFlag = travelHelper.checkTravel();
        if(travelFlag){
            //現在の旅行情報を取得する
            nowTravel = travelHelper.getNowTravel();
            //訪問先データの全件取得処理
            if(scheduleHelper.findSchedule(nowTravel.gettravelNum())){
                //データベースから現在の旅行の行程をすべて取得する
                scheduleList = scheduleHelper.getNowTravelList();  
            }
            //今タイムリーで訪れている旅行先があるかを確認する
            if(scheduleHelper.findNowSchedule(nowTravvel.gettravelNum())){
                //訪れている場所がある場合の処理
                nowSchedule = scheduleHelper.getNowTravel();
            }else{
                //いま訪れている場所がない場合は一か所目に訪れる場所をアクティブにする
                
                //どれが最初かわからないのでgetRouteNumが０の場所をリスト総舐めで調べる
                for(travelSchedule x:travelList){
                    if(x.getRouteNum() == 0{
                        //旅行中のフラグを立てる
                        x.setFlag(1);
                        //その情報をデータベースに保存する
                        scheduleHelper.save_diary(x);
                        //現在のスケジュールにxを代入する
                        nowSchedule = x;
                        //これ以上ループ処理の必要がないのでブレイクする
                        break;
                    }
                }
            }
            //次に観光地のデータを取得する
            if(placeHelper.findePlaceInfo(nowSchedule.getPlaceName(),placeInfomation.PLACE_NAME)){
                //1つのデータしか見つからないという信頼の上で
                nowPlaceData = placeInfoDB.getSearchResult().get(0);
                
            }else{
                //ここに入ることはあり得ない
            }
        }//旅行がある場合の処理の終端(if文の終端)
    }

    //旅行の開始処理
    public void onStartTravel(){

        
        
        




    }


    //旅行の終了処理

    public void onEndTravel(){

    }




    //行程を進める



    //行程を逆戻り（非推奨）


    //近くの飲食店の情報をArrayListで返す






    //こ↑こ↓からデバック＆システム処理用

    //行程情報・旅行情報のフラグをすべて０にする（メンテナンス用）
    public void all_travelFlag_false(){
        //データベース照会

        //こ↑こ↓からデータベースのヘルパークラスにデータベース内の全件情報を取得するメソッドを追加してから内容を書く


    }


}
