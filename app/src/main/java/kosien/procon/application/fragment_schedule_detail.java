package kosien.procon.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.RecordDaoItem;
import kosien.procon.application.matatabidb.mydatabase.RecordItem;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import kosien.procon.application.matatabidb.mydatabase.storeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.storeInfoTable;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/30.
 */

public class fragment_schedule_detail extends Fragment {

    //フラグメントで表示する内容
    private TextView mTextView;
    private infoTravelDao travelHelper;
    private placeInfoDao placeHelper;
    private storeInfoDao storeHelper;


    //リソース
    TextView placeNameView;
    TextView placeCategoryView;
    TextView placeUploadView;
    TextView placeActionView;
    ListView placeDetailList;
    /**
     * この下のやつはバンドルしてきたやつによって型が変わるよ
     */
    private bundle_schedule viewData = new bundle_schedule();
    private RecordDaoItem recordDB;
    private RecordItem myRecord;

    //リストビューの表示内容の数
    private final int listColumnNum = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_detail_schedule, container, false);
    }

    //ビューを生成し終わった後に呼ばれるメソッド

    @Override
    public void onViewCreated(final View view, Bundle saveInstanceState) {

        //データベースオープン
        placeHelper = new placeInfoDao(getContext());
        storeHelper = new storeInfoDao(getContext());

        // ボタンを生成
        super.onViewCreated(view, saveInstanceState);

        travelHelper = new infoTravelDao(getContext());
        recordDB = new RecordDaoItem(getContext());
        //バンドルされたデータを取得するz
        Bundle bundle = getArguments();
        viewData = (bundle_schedule) bundle.getSerializable("infoTravel");

        //リソース割り当て
        placeNameView = (TextView)view.findViewById(R.id.schedule_name_detail);
        placeCategoryView = (TextView)view.findViewById(R.id.schedule_cat_detail);
        placeUploadView = (TextView)
        placeActionView = (TextView)view.findViewById(R.id.schedule_action_detail);
        placeDetailList = (ListView)view.findViewById(R.id.schedule_list_detail);
        //バンドルデータにより訪れたところの詳細なデータを取得する
        switch(viewData.getPlaceCategory()){
            case "観光地":
                if(placeHelper.findPlaceInfo(viewData.getPlaceName(),placeInfomation.PLACE_NAME)) {
                    placeInfomation placeInfo = placeHelper.getSearchResult().get(0);
                    placeNameView.setText(placeInfo.getPlaceName());
                    placeCategoryView.setText("観光地");
                    placeActionView.setText(placeInfo.getPlaceAddress());

                }else{
                    placeNameView.setText("データの取得に失敗しました");
                    placeCategoryView.setText("デバック");
                    placeActionView.setText("データの取得に失敗したよ～");

                }
                break;
            default:
                //お店の場合はこちら
                if(storeHelper.findStoreInfo(viewData.getPlaceName(), storeInfoTable.STORE_NAME)){
                    storeInfoTable storeInfo = storeHelper.getSearchResult().get(0);
                    placeNameView.setText(storeInfo.getStoreName());
                    placeCategoryView.setText("観光地");
                    placeActionView.setText(storeInfo.getPlaceAddress());

                }else{
                    placeNameView.setText("データの取得に失敗しました");
                    placeCategoryView.setText("デバック");
                    placeActionView.setText("データの取得に失敗したよ～");

                }

        }

        /**
         * リストビューの中身
         * １番目：日記を見る
         * ２番目：日記の編集
         * ３番目：ついったー
         * ４番目：ばなな（嘘です）
         */

        //リストビューの表示内容の決定

        ArrayList<place_detail_list_item> listItem = new ArrayList<>();
        //表示内容
        final String firstColumn = "この目的地で記録した日記を見る";
        final String secondColumn = "この目的地での記録を作成する";
        final String thirdColumn = "ツイッターに日記を投稿します";

        //表示内容の作成

        place_detail_list_item firstItem = new place_detail_list_item(firstColumn);
        place_detail_list_item secondItem = new place_detail_list_item(secondColumn);
        place_detail_list_item thirdItem = new place_detail_list_item(thirdColumn);

        /**
         *画像が出来たらよろしくナス
         */

        listItem.add(firstItem);
        listItem.add(secondItem);
        listItem.add(thirdItem);

        final place_detail_list_adapter adapter = new place_detail_list_adapter(getContext(),R.layout.place_detail_list,listItem);
        placeDetailList.setAdapter(adapter);

        //クリックリスナー定義
        placeDetailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        switch(view.getId()){
                            case R.id.list_colum:
                                updateRecord();
                                //日記の作成
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragment_diaryview recordFragment = new fragment_diaryview();
                                /* 参照画面で表示情報を引き継ぎ */
                                Bundle bundle = new Bundle();
                                bundle.putInt("year", myRecord.getDiaryYear());
                                bundle.putInt("month", myRecord.getDiaryMon());
                                bundle.putInt("day", myRecord.getDiaryDay());
                                bundle.putString("diary", myRecord.getDiaryRecord());
                                recordFragment.setArguments(bundle);
                                fragmentTransaction.replace(R.id.frame_layout, recordFragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                                break;
                        }
                        break;
                    case 1:
                        switch(view.getId()){
                            case R.id.list_colum:
                                updateRecord();
                                //日記の作成
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragment_diaryedit recordFragment = new fragment_diaryedit();
                                //旅行番号をバンドルする
                                Bundle bundle = new Bundle();
                                bundle.putInt("schedule", viewData.getRowId());
                                bundle.putInt("travel", viewData.getTravelNum());
                                recordFragment.setArguments(bundle);
                                fragmentTransaction.replace(R.id.frame_layout, recordFragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();

                        }
                        break;
                    case 2:
                        switch(view.getId()){
                            case R.id.list_colum:
                                //ツイッターフラグメントの呼び出しはこ↑こ↓から


                                //こ↑こ↓までで
                                break;
                        }
                        break;
                }
            }
        });





    }

    private void updateRecord() {
        if (recordDB.findRecord(viewData.getRowId(), viewData.getTravelNum())) {
            myRecord = recordDB.getRecord().get(0);
        }

    }


}
