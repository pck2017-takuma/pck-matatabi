package kosien.procon.application;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.StationInfoDao;
import kosien.procon.application.matatabidb.mydatabase.Station_Infomation;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;

import su.heartlove.matatabi.R;

/**
 * Created by i15317 on 2017/09/26.
 */

public class SearchFragment extends Fragment {

    private final SearchFragment self = this;

    private SearchView searchView;
    private String searchWord;

    StationInfoDao stationHelper = new StationInfoDao(this.getActivity());
    placeInfoDao placeHelper = new placeInfoDao(this.getActivity());

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_search,null);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);

        inflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu_search_view);
        this.searchView = (SearchView)MenuItemCompat.getActionView(menuItem);



        //ActionViewの取得
        this.searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        //虫眼鏡表示するか
        this.searchView.setIconifiedByDefault(true);

        //submitボタンを表示するかどうか
        this.searchView.setSubmitButtonEnabled(false);

        if(this.searchWord.equals("")){
            this.searchView.setQuery(this.searchWord,false);
        }else{
            String queryHint = self.getResources().getString(R.string.search_hint);
            this.searchView.setQueryHint(queryHint);
        }
        this.searchView.setOnQueryTextListener(self.onQueryTextListener);
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener(){
        @Override
        public boolean onQueryTextSubmit(String searchWord){
            //submitボタン
            return self.setSearchWord(searchWord);
        }

        @Override
        public boolean onQueryTextChange(String searchWord){
            //入力される度
            return false;
        }
    };

    private boolean setSearchWord(String searchWord){
        ActionBar actionBar = ((AppCompatActivity)this.getActivity()).getSupportActionBar();
        actionBar.setTitle(searchWord);
        actionBar.setTitle(searchWord);
        actionBar.setDisplayShowTitleEnabled(true);

        if(searchWord != null && !searchWord.equals("")){
            //nullチェックとスペースチェック
            this.searchWord = searchWord;
        }

        this.searchView.setIconified(false);
        this.searchView.onActionViewCollapsed();

        //こ↑こ↓からデータベース照会
        //駅データの該当データを取得する
        ArrayList<Station_Infomation> stationResult = stationHelper.findStationInfo(searchWord, Station_Infomation.STATION_NAME);





        return false;

    }


}
