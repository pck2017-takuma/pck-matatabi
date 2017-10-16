package kosien.procon.application;

import android.content.Context;

import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.travelScheduleDao;

/**
 * Created by i15317 on 2017/10/16.
 */

//旅行情報管理支援クラス

public class travelStatusInfoHelper {

    //コンテキスト
    Context myContext;

    //データベース
    //旅行管理
    infoTravelDao travelHelper;
    //観光地情報
    placeInfoDao placeHelper;
    //旅行行程
    travelScheduleDao scheduleHelper;





    //ここから状態保持






    //コンストラクタ
    travelStatusInfoHelper(Context context){
        myContext = context;
    }

    //現在の旅行情報を取得する



    //現在訪れている観光地の情報を取得する




    //現在の行程を確認する




    //この旅行の行程を一括で投げる



    //旅行の開始処理




    //旅行の終了処理





    //近くの飲食店の情報をArrayListで返す






    //デバック＆システム処理用

    //行程の一括消去

    //全旅行の一括終了



}
