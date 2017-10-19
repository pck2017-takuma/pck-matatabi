package kosien.procon.application;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import su.heartlove.matatabi.R;

/**
 * Created by Owner on 2017/10/15.
 */

public class setting_fragment extends Fragment {

    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.fragment_setting,container,false);

    }

    @Override
    public void onViewCreated(View view,Bundle saveInstanceState){

//        listView = (ListView)view.findViewById(R.id.setting_view);
//
//        setting_list_item item = new setting_list_item();
//        item.setClickText("testClicker");
//        item.setStatusText("setViewText");
//
//        ArrayList<setting_list_item> listItem = new ArrayList<>();
//        //アイテムセット
//        setting_list_item testItem = new setting_list_item();
//        testItem.setStatusText("textStatus");
//        testItem.setClickText("testClicker");
//        listItem.add(testItem);
//
//        //アダプター
//        setting_list_adapter adapter = new setting_list_adapter(getContext(),0,listItem);
//        listView.setAdapter(adapter);


    }

    private void setPreferenceValue(){
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(getContext());

        

    }



}
