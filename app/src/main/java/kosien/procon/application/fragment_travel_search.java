package kosien.procon.application;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Queue;

import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import kosien.procon.application.matatabidb.mydatabase.storeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.storeInfoTable;
import su.heartlove.matatabi.R;

import static kosien.procon.application.MainActivity.br;

/**
 * Created by Owner on 2017/10/21.
 */

public class fragment_travel_search extends Fragment {



    //スイッチ
    private final int placeListNum = 0;
    private final int storeListNum = 1;


    //観光地データベース検索結果一覧
    private ArrayList<placeInfomation> placeList;
    //飲食店データベース検索結果一覧
    private ArrayList<storeInfoTable>storeList;
    //観光地データベース
    placeInfoDao placeHelper;
    storeInfoDao storeHelper;

    //りすとびゅー
    ListView listView;
    //元データの位置確認（第1：place or store,第2：リストの配列番号）
    ArrayList<Pair<Integer,Integer>>tmpList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);

        //アクティビティのtoolbarにサーチビューを持ってくる
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_search);
        SearchView searchView = (SearchView)toolbar.getMenu().findItem(R.id.search_menu_search_view).getActionView();

        //サーチビューの動作定義
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //エンターキーを押したとき


                /**
                 *
                 * データベース更新後にここを実装する
                 *
                 */




                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {



                /**
                 *
                 * データベース更新後にここを実装する
                 *
                 */

                return false;

            }
        });


        return inflater.inflate(R.layout.place_info_detail,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        //リスト初期化
        placeList = new ArrayList<>();
        storeList = new ArrayList<>();

        //データベースオープン
        placeHelper = new placeInfoDao(getContext());
        storeHelper = new storeInfoDao(getContext());

        //リストビュー
        listView = (ListView)view.findViewById(R.id.place_search_list);

    }

    //リストビューに値をセットする
    private void onUpgradeList(){

        final ArrayList<place_detail_item>listItems = new ArrayList<>();


        int cnt = 0;
        //観光地データを検索結果に入れる
        for(placeInfomation x:placeList){
            place_detail_item tmp = new place_detail_item();

            //画像データを引っ張ってくる
            Bitmap bitmap;

            tmp.setPlaceTitle(x.getPlaceName());
            tmp.setPlaceCategory("観光地");
            tmp.setPlaceColumn(x.getPlaceColumn());
            tmp.setPlaceImage(bitmap);

            listItems.add(tmp);

            tmpList.add(new Pair<Integer,Integer>(placeListNum,cnt));
            cnt++;
        }

        //周辺のお店情報の検索
        cnt = 0;
        for(storeInfoTable x:storeList){
            place_detail_item tmp = new place_detail_item();

            //お店のジャンル取得
            String storeGenre = new String();
            Bitmap storeImage;
            tmp.setPlaceTitle(x.getStoreName());
            tmp.setPlaceColumn(x.getStoreOpenTime() + br + x.getStoreCloseTime());
            tmp.setPlaceCategory(storeGenre);
            tmp.setPlaceImage(storeImage);

            tmpList.add(new Pair<Integer,Integer>(storeListNum,cnt));
            listItems.add(tmp);
            cnt++;
        }

        //アダプターにセット
        place_search_adapter adapter = new place_search_adapter(getContext(), R.layout.fragment_schedule_create_list, listItems);
        //アダプター更新
        listView.setAdapter(adapter);

        //リストビューの削除ボタンを実装する
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (view.getId()) {
                    case R.id.place_action:
                        //このテキストを表示したら画面遷移
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = manager.beginTransaction();
                        fragment_place_introduce introduce = new fragment_place_introduce();

                        //バンドルデータ
                        Bundle bundle = new Bundle();
                        Pair<Integer,Integer> tmp = tmpList.get(position);

                        //バンドルデータ
                        switch(tmp.first){
                            case placeListNum:
                                bundle.putSerializable("placeBundle",placeList.get(tmp.second));

                                break;
                            case storeListNum:
                                //カテゴリー取得
                                String genre = null;
                                bundle.putSerializable("storeBundle",storeList.get(tmp.second));
                                bundle.putString("Genre",genre);
                                break;
                        }

                        //バンドル
                        introduce.setArguments(bundle);
                        fragmentTransaction.replace(R.id.place_search_list, introduce);
                        fragmentTransaction.commit();

                        break;
                }
            }
        });

    }



    //フラグメントのウィンドウを破壊するとき
    @Override
    public void onDestroyView(){
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.getMenu().clear();

        super.onDestroyView();
    }



}
