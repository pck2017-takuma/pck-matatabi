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

public class fragmnet_travelcreate_list extends Fragment {

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
    private final String PLACE_SAVE_KEY = "place_save_key";
    private final String STORE_SAVE_KEY = "store_save_key";

    //決定した行先
    private ArrayList<placeInfomation> decidePlace = new ArrayList<>();

    private ArrayList<storeInfoTable> decideStore = new ArrayList<>();


    //バンドルされる追加された行先
    private placeInfomation AddPlace = new placeInfomation();
    private storeInfoTable AddStore = new storeInfoTable();


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
        String placeJson = pref.getString(STORE_SAVE_KEY,"");
        if(placeJson.equals("[]")){
            //空の時

        }else{
            //空でないとき復元
            decidePlace = gson.fromJson(placeJson,new TypeToken<ArrayList<placeInfomation>>(){}.getType());

        }
        String storeJson = pref.getString(STORE_SAVE_KEY,"");
        if(storeJson.equals("[]")){
            //空の時

        }else{
            //空でないとき復元
            decideStore = gson.fromJson(storeJson,new TypeToken<ArrayList<storeInfoTable>>(){}.getType());

        }

        //詳細フラグメントからのデータが存在するかどうか
        if (addBundle != null && addBundle.containsKey(addPlace)) {
            Bundle tmpBundle = addBundle.getBundle(addPlace);
            AddPlace = (placeInfomation) tmpBundle.getSerializable(addPlace);
            decidePlace.add(AddPlace);
        }else if (addBundle != null && addBundle.containsKey(addStore)) {
            Bundle tmpBundle = addBundle.getBundle(addStore);
            AddStore = (storeInfoTable) tmpBundle.getSerializable(addStore);
            decideStore.add(AddStore);
        }
        return inflater.inflate(R.layout.search_2, container, false);


    }

    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {


        super.onViewCreated(view, saveInstanceState);
        FloatingActionButton actionButton = (FloatingActionButton)view.findViewById(R.id.setting_button);

        //ボタンのリスナーを生成

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //decidePlaceが空かどうか？
                if(!decidePlace.isEmpty()){
                    //既存のpreferenceを削除
                    pref.edit().remove(PLACE_SAVE_KEY);

                    Gson gson = new Gson();
                    pref.edit().putString(PLACE_SAVE_KEY,gson.toJson(decidePlace)).apply();

                    //フラグメント
                    fragment_travel_search searchFragment = new fragment_travel_search();

                    getFragmentManager().beginTransaction().replace(R.id.travel_list,searchFragment).commit();

                }
                //decideStoreが空かどうか
                if(!decideStore.isEmpty()){
                    //既存のpreferenceを削除
                    pref.edit().remove(STORE_SAVE_KEY);
                    Gson gson = new Gson();
                    pref.edit().putString(STORE_SAVE_KEY,gson.toJson(decideStore)).apply();
                    //フラグメント
                    fragment_travel_search searchFragment = new fragment_travel_search();
                    getFragmentManager().beginTransaction().replace(R.id.travel_list,searchFragment).commit();

                }

            }
        });

    }


    @Override
    public void onStart() {

        super.onStart();

        Button acceptButton = (Button) getActivity().findViewById((R.id.accept_button));

        // レイアウトからリストビューを取得
       listView = (ListView) getActivity().findViewById(R.id.travel_listview);

        Bundle intent = getArguments();
        placeInfomation test = (placeInfomation) intent.getSerializable("test");



        xxx = new placeInfoDao(tcf.getContext());
        if (test != null)
            decidePlace.add(test);


        // リストビューに表示する要素を設定

        if (decidePlace.size() == 0) {
            fragment_schedule_create_item item = new fragment_schedule_create_item("スケジュールがまだ何も決まっていません");
            listItems.add(item);

        } else {
            for (int i = 0; i < decidePlace.size(); i++) {
                fragment_schedule_create_item item = new fragment_schedule_create_item(decidePlace.get(i).getPlaceName());
                listItems.add(item);
            }
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(listKey, decidePlace);
        super.onSaveInstanceState(outState);

    }

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
        for (placeInfomation x : decidePlace) {
            bbb.setPlaceName(x.getPlaceName());
            bbb.setRouteNum(i);
            bbb.setTravelNum(aaa);
            bbb.setFlag(0);
            //スケジュール情報をデータベースに登録
            decidePlaceDB.sava_diary(bbb);
            ++i;
        }


    }


}
