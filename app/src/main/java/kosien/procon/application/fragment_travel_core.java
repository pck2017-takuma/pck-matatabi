package kosien.procon.application;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kosien.procon.application.matatabidb.ConnectServer;
import kosien.procon.application.matatabidb.basicTimeSearch;
import kosien.procon.application.matatabidb.mydatabase.RouteInfo;
import kosien.procon.application.matatabidb.mydatabase.RouteInfoDaoItem;
import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import kosien.procon.application.matatabidb.mydatabase.travelScheduleDao;
import su.heartlove.matatabi.R;

/**
 * Created by Owner on 2017/10/15.
 */

public class fragment_travel_core extends Fragment {
    private TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);

        //ホストのタブを使用する
        tabLayout = (TabLayout)getActivity().findViewById(R.id.tabLayout);

        return inflater.inflate(R.layout.fragment_travel, container, false);

    }



    @Override
    public void onViewCreated(final View view, Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);





    }







}
