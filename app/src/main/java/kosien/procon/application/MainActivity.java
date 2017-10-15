package kosien.procon.application;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import su.heartlove.matatabi.R;

/**
 * Created by Owner on 2017/10/15.
 */

public class MainActivity extends AppCompatActivity {



    /**
     * アクティビティ生成時に呼ばれる
     * @param savedInstanceState
     */


    //


    private Integer nowItem = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //フラグメントマネージャー

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Integer itemId = 0;

            if(nowItem != null){
                itemId = nowItem;
                nowItem = null;

            }else{
                itemId = item.getItemId();
            }

            switch (itemId) {
                case R.id.HomeUI:
                    main_fragment mainFragment = new main_fragment();
                    fragmentTransaction.replace(R.id.frame_layout,mainFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.SearchUI:
                    search_fragment searchFragment = new search_fragment();
                    fragmentTransaction.replace(R.id.frame_layout,searchFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.GalleryUI:
                    record_fragment recordFragment = new record_fragment();
                    fragmentTransaction.replace(R.id.frame_layout,recordFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.Garbage:
                    setting_fragment settingFragment = new setting_fragment();
                    fragmentTransaction.replace(R.id.frame_layout,settingFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Toolbarの定義（実装は各フラグメントごとに分ける）
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
       //アクションバーとしても動くようにする
       // setSupportActionBar(toolbar);





    }



}
