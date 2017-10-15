package kosien.procon.application;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/16.
 */

public class fragment_place_introduce extends Fragment {


    //観光地データベース
    placeInfoDao placeHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);

        return inflater.inflate(R.layout.fragment_place_introduce, container, false);

    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        //ここでボタン等の各要素をセットする

        //データベースオープン
        placeHelper = new placeInfoDao(getContext());




    }


}
