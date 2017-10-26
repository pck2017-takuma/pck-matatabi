package kosien.procon.application;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import kosien.procon.application.matatabidb.mydatabase.storeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.storeInfoTable;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import kosien.procon.application.matatabidb.mydatabase.travelScheduleDao;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/30.
 */

public class fragment_schedule extends Fragment {

    //フラグメントで表示する内容
    private TextView mTextView;
    private travelScheduleDao scheduleDB;
    private infoTravelDao travelDB;
    //観光地データベースオープン
    private placeInfoDao placeDB;
    private storeInfoDao storeDB;
    ArrayList<travelSchedule> getList = new ArrayList<>();
    //スイッチ
    private final int placeListNum = 0;
    private final int storeListNum = 1;
    //バンドルデータ
    private infoTravel getData = null;
    //スケジュールデータを表示する
    ArrayList<travelSchedule> scheduleData = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    //ビューを生成し終わった後に呼ばれるメソッド

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.sample_listvieww);
        // コンテンツの変化でRecyclerViewのサイズが変わらない場合は、
        // パフォーマンスを向上させることができる
        mRecyclerView.setHasFixedSize(true);

        //スケジュールデータベースオープン
        scheduleDB = new travelScheduleDao(getContext());
        travelDB = new infoTravelDao(getContext());
        placeDB = new placeInfoDao(getContext());
        storeDB = new storeInfoDao(getContext());
        //バンドルされたデータを取得する
        Bundle bundle = getArguments();
        getData = (infoTravel) bundle.getSerializable("infoTravel");
        //スケジュール一覧を取得する
        if (scheduleDB.findSchedule(getData.getTravelNum())) {
            getList = scheduleDB.getNowTravelList();
        }


        // ボタンを生成
        super.onViewCreated(view, saveInstanceState);
        Button accept_button = (Button) view.findViewById(R.id.edit_buttonn);
        Button upload_button = (Button)view.findViewById(R.id.edit_buttonn2);

        //acceptボタンの割り当て

        if (getData.getTravelFlag() == 0) {
            accept_button.setText("旅行を開始");
            accept_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getData.setTravelFlag(1);

                    travelSchedule start = getList.get(0);
                    //アクティブにする
                    start.setFlag(1);
                    //データベース登録
                    travelDB.save_time(getData);
                    scheduleDB.sava_diary(start);

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    main_fragment mainFragment = new main_fragment();
                    transaction.replace(R.id.frame_layout, mainFragment);
                    transaction.commit();
                }
            });
        } else {
            accept_button.setText("旅行を終了");
            accept_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ここで旅行をこのスケジュールを終了状態にする
                    getData.setTravelFlag(0);
                    travelDB.save_time(getData);

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    main_fragment mainFragment = new main_fragment();
                    transaction.replace(R.id.frame_layout, mainFragment);
                    transaction.commit();

                }
            });

            upload_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //アップロード処理

                    //旅行情報の登録処理から


                }
            });

        }

        //表示アイテム
        ArrayList<place_detail_item> listItems = new ArrayList<>();

        if (getList.size() == 0) {
            Toast.makeText(getContext(), "データが存在しません", Toast.LENGTH_SHORT).show();
        } else {
            for (travelSchedule x : getList) {
                place_detail_item listItem = new place_detail_item();
                //観光地orショップ情報を取得する
                if(placeDB.findPlaceInfo(x.getPlaceName(), placeInfomation.PLACE_NAME )){
                    //先頭要素だけを取り出せば十分
                    placeInfomation getPlaceInfo = placeDB.getSearchResult().get(0);
                    listItem.setPlaceTitle(getPlaceInfo.getPlaceName());
                    listItem.setPlaceColumn(getPlaceInfo.getPlaceColumn());
                    listItem.setPlaceCategory("観光地");
                    /**
                     * 画像読み込み実装をお願いします。
                     */
                    //listItem.setPlaceImage();

                    listItem.setPlaceAction("この場所の詳細を見る");
                    listItems.add(listItem);
                }else if(storeDB.findStoreInfo(x.getPlaceName(), storeInfoTable.STORE_NAME)){
                    //先頭要素だけ取得すれば十分
                    storeInfoTable getStoreInfo = storeDB.getSearchResult().get(0);
                    //先頭要素だけを取り出せば十分
                    listItem.setPlaceTitle(getStoreInfo.getStoreName());
                    listItem.setPlaceColumn(getStoreInfo.getStoreColumn());
                    listItem.setPlaceCategory(getStoreInfo.getStoreGenreName());
                    /**
                     * 画像読み込み実装をお願いします。
                     */
                    //listItem.setPlaceImage();

                    listItem.setPlaceAction("この場所の詳細を見る");
                    listItems.add(listItem);
                }
            }

            //            // アイテムクリック時ののイベントを追加
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent,
//                                        View view, int pos, long id) {
//
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                    //データをバンドル
//                    Bundle bundle = new Bundle();
//
//                    //バンドルデータを取得
//                    travelSchedule tmp = getList.get(pos);
//                    bundle.putSerializable("infoTravel", tmp);
//                    //フラグメントを立ち上げる
//                    //こ↑こ↓のボタンは無効化する
//                    fragment_schedule_detail recordFragment = new fragment_schedule_detail();
//                    recordFragment.setArguments(bundle);
//                    fragmentTransaction.add(R.id.detail_schedule, recordFragment);
//                    fragmentTransaction.commit();
//
//                }
//
//
//            });


            //アダプターにセット
            final CardRecyclerAdapter adapter = new CardRecyclerAdapter(getContext(),listItems) {
                @Override
                protected void onVersionClicked(@NonNull place_detail_item version, @NonNull int position) {
                    super.onVersionClicked(version,position);
                    // Activity 側でタップされたときの処理を行う
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();

                    fragment_schedule_detail introduce = new fragment_schedule_detail();

                    //バンドルデータ
                    Bundle bundle = new Bundle();
                    //こ↑こ↓にはなにをバンドルするのがベストかな？
                    bundle_schedule bundleData = new bundle_schedule();
                    bundleData.setRowId(getList.get(position).getRowid());
                    bundleData.setTravelNum(getList.get(position).getTravelNum());
                    bundleData.setPlaceName(version.getPlaceTitle());
                    bundleData.setPlaceCategory(version.getPlaceCategory());

                    bundle.putSerializable("infoTravel",bundleData);
                    //バンドル
                    introduce.setArguments(bundle);
                    fragmentTransaction.replace(R.id.frame_layout, introduce);
                    fragmentTransaction.addToBackStack(null);
                    Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
                    toolbar.getMenu().clear();
                    fragmentTransaction.commit();

                }
            };

            // LinearLayoutManagerを使用する
            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }


    }

}
