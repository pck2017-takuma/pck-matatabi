package kosien.procon.application;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import kosien.procon.application.matatabidb.mydatabase.storeInfoTable;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import kosien.procon.application.matatabidb.mydatabase.travelScheduleDao;
import su.heartlove.matatabi.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by procon-kyougi1 on 2017/10/02.
 */

public class fragment_travelcreate_list extends Fragment {

    //行先決定リストの中身がstoreかplaceか判別
    public static final String STORE_KEY = "store_key";
    public static final String PLACE_KEY = "place_key";


    private int mposition = 0;

    private placeInfoDao xxx;
    private fragment_travelcreate tcf = new fragment_travelcreate();
    private ListView listView;

    public static final String listKey = "placeList";
    public static final String addPlace = "addPlace";
    public static final String addStore = "addStore";

    ArrayList<fragment_schedule_create_item> listItems = new ArrayList<>();

    //一時保存
    SharedPreferences pref;
    //セーブキー
    private final String VISIT_SAVE_KEY = "place_save_key";

    //決定した行先
    private ArrayList<Pair<Object,String>>decideList = new ArrayList<>();


    //バンドルされる追加された行先
    private placeInfomation AddVisitPlace = new placeInfomation();


    //表示リスト一覧


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //これまでに保存したデータが存在するか
        Bundle addBundle = getArguments();

//        if (addBundle != null && addBundle.containsKey(listKey)) {
//            Bundle tmpBundle = addBundle.getBundle(listKey);
//            decidePlace = (ArrayList<placeInfomation>) tmpBundle.getSerializable(listKey);
//        }

        //プリファレンスからスケジュールの作成途中のデータを取得する

        pref = getActivity().getSharedPreferences("pref",MODE_PRIVATE);
        Gson gson = new Gson();
        String placeJson = pref.getString(VISIT_SAVE_KEY,"");
        if(placeJson.equals("[]")){
            //空の時
        }else{
            //空でないとき復元
            decideList = gson.fromJson(placeJson,new TypeToken<ArrayList<Pair<Object,String>>>(){}.getType());
        }


        //詳細フラグメントからのデータが存在するかどうか
        if (addBundle != null && addBundle.containsKey(addPlace)) {
            Bundle tmpBundle = addBundle.getBundle(addPlace);
            placeInfomation AddPlace = (placeInfomation) tmpBundle.getSerializable(addPlace);
            decideList.add(new Pair<Object,String>(AddPlace,PLACE_KEY));
        }else if (addBundle != null && addBundle.containsKey(addStore)) {

            Bundle tmpBundle = addBundle.getBundle(addStore);
            storeInfoTable AddStore = (storeInfoTable) tmpBundle.getSerializable(addStore);
            decideList.add(new Pair<Object,String>(addStore,STORE_KEY));

        }
        return inflater.inflate(R.layout.search_2, container, false);


    }

    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {


        super.onViewCreated(view, saveInstanceState);
        FloatingActionButton actionButton = (FloatingActionButton)view.findViewById(R.id.search_button);
        Button acceptButton = (Button) getActivity().findViewById((R.id.accept_button));

        //ボタンのリスナーを生成

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //decidePlaceが空かどうか？
                if(decideList != null && !decideList.isEmpty()){
                    //既存のpreferenceを削除
                    pref.edit().remove(VISIT_SAVE_KEY);

                    Gson gson = new Gson();
                    pref.edit().putString(VISIT_SAVE_KEY,gson.toJson(decideList)).apply();
                    //フラグメント
                    fragment_travel_search searchFragment = new fragment_travel_search();
                    getFragmentManager().beginTransaction().replace(R.id.travel_list,searchFragment).commit();

                }else{
                    fragment_travel_search searchFragment = new fragment_travel_search();
                    getFragmentManager().beginTransaction().replace(R.id.travel_list,searchFragment).commit();

                }

            }
        });


        // レイアウトからリストビューを取得
        listView = (ListView) getActivity().findViewById(R.id.travel_listview);




        // リストビューに表示する要素を設定
        if(decideList != null){
            if (decideList.size() == 0) {
                fragment_schedule_create_item item = new fragment_schedule_create_item("スケジュールがまだ何も決まっていません");
                listItems.add(item);

            } else {
                for (Pair<Object, String> x : decideList) {
                    //decideListの方によって条件分岐
                    switch (x.second) {
                        case PLACE_KEY:
                            placeInfomation tmpPlace = (placeInfomation) x.first;
                            fragment_schedule_create_item item = new fragment_schedule_create_item(tmpPlace.getPlaceName());
                            listItems.add(item);
                            break;
                        case STORE_KEY:
                            storeInfoTable tmpStore = (storeInfoTable) x.first;
                            fragment_schedule_create_item item2 = new fragment_schedule_create_item(tmpStore.getStoreName());
                            listItems.add(item2);
                            break;
                    }
                }
            }
        }else{
            fragment_schedule_create_item item = new fragment_schedule_create_item("スケジュールがまだ何も決まっていません");
            listItems.add(item);
        }



        // 出力結果をリストビューに表示
        final fragment_schedule_create_adapter adapter = new fragment_schedule_create_adapter(getContext(), R.layout.fragment_schedule_create_list, listItems);
        listView.setAdapter(adapter);


        //リストビューの削除ボタンを実装する
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (view.getId()) {
                    case R.id.delete_button:
                        //ボタンを押したら該当リストビューを削除する
                        listItems.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });






        //スケジュールの確定処理

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //スケジュールデータの確定処理
                dataWrite();

                //ここにダイアログを確定したとダイアログを表示

                //トップに飛ぶ
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                main_fragment mainFragmnet = new main_fragment();
                transaction.replace(R.id.frame_layout, mainFragmnet);
                transaction.commit();
            }
        });


    }

//
//    @Override
//    public void onStart() {
//
//        super.onStart();
//
//
//
//
//
//    }

    //
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putSerializable(listKey, decideList);
//        super.onSaveInstanceState(outState);
//
//    }

    private void dataWrite() {

        infoTravelDao xxx = new infoTravelDao(getContext());
        travelScheduleDao decidePlaceDB = new travelScheduleDao(getContext());
        infoTravel zzz = new infoTravel();

        //旅行のタイトルをとりあえず勝手に設定する（旅行名＋現在時刻）

        //現在時刻の取得


        zzz.setTravelTitle("テスト旅行" + getTimeListener.getNowDate());
        zzz.setTravelFlag(0);
        zzz = xxx.save_time(zzz);
        int aaa = zzz.getRowid();

        travelSchedule bbb = new travelSchedule();
        int i = 0;
//        for (placeInfomation x : decidePlace) {
//            bbb.setPlaceName(x.getPlaceName());
//            bbb.setRouteNum(i);
//            bbb.setTravelNum(aaa);
//            bbb.setFlag(0);
//            //スケジュール情報をデータベースに登録
//            decidePlaceDB.sava_diary(bbb);
//            ++i;
//        }


    }


}
