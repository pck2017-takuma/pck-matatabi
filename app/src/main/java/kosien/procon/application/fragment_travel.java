package kosien.procon.application;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.basicTimeSearch;
import kosien.procon.application.matatabidb.mydatabase.RecordDaoItem;
import kosien.procon.application.matatabidb.mydatabase.RecordItem;
import kosien.procon.application.matatabidb.mydatabase.RouteInfo;
import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.fragment_travel, container, false);
    }

    //ビューを生成し終わった後に呼ばれるメソッド

    @Override
    public void onViewCreated(final View view, Bundle saveInstanceState) {
        scheduleDB = new travelScheduleDao(getContext());
        //バンドルされた旅行データを取得
        bundleData = (infoTravel)saveInstanceState.getSerializable("travelNum");



        //現在の行程を取得
        if(scheduleDB.findSchedule(bundleData.gettravelNum())){
            nowPlace = scheduleDB.getNowTravel();
        }else{

            //全件取得して１つ目の要素をtrueにしてデータベースに登録する


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



}
