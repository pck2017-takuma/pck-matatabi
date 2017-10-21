package kosien.procon.application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import kosien.procon.application.matatabidb.mydatabase.storeInfoTable;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/16.
 */

public class fragment_place_introduce extends Fragment {


    //観光地データベース
    placeInfoDao placeHelper;
    placeInfomation infomation;

    //リソース
    TextView placeNameView;
    TextView placeCategoryView;
    TextView placeActionView;
    ListView placeDetailList;

    //ちず

    //表示データ
    placeInfomation placeInfo = null;
    storeInfoTable storeInfo = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);

        return inflater.inflate(R.layout.fragment_place_detail, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //リソース割り当て
        placeNameView = (TextView)view.findViewById(R.id.place_name);
        placeCategoryView = (TextView)view.findViewById(R.id.place_cat);
        placeActionView = (TextView)view.findViewById(R.id.place_action);
        placeDetailList = (ListView)view.findViewById(R.id.place_list);

        //バンドルデータげっと
        Bundle bundle = getArguments();

        if(bundle != null && bundle.containsKey("placeBundle")){
            placeInfo = (placeInfomation)bundle.getSerializable("placeBundle");
            //表示処理
            placeNameView.setText(placeInfo.getPlaceName());
            placeNameView.setText("観光地");

            //リストビュー




        }else if(bundle != null && bundle.containsKey("storeBundle")){
            storeInfo = (storeInfoTable)bundle.getSerializable("storeBundle");
            //表示処理
            String genre = bundle.getString("Genre");

            placeNameView.setText(storeInfo.getStoreName());
            //ジャンル取得
            placeCategoryView.setText(genre);

            //リストビュー表示
        }


        //actionViewにリスナーを持たせる
        placeActionView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //スケジュールバンドル処理


            }
        });



    }


}
