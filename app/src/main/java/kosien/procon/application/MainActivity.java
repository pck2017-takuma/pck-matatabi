package kosien.procon.application;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;

import su.heartlove.matatabi.R;

/**
 * Created by Owner on 2017/10/15.
 */

public class MainActivity extends AppCompatActivity {


    /**
     * アクティビティ生成時に呼ばれる
     *
     * @param savedInstanceState
     */

    //改行
    public static final String br = "\n";

    //二種類のフラグメントマネージャーが混在しているので取り扱いには要注意

    private Integer nowItem = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //フラグメントマネージャー

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Integer itemId = 0;

            if (nowItem != null) {
                itemId = nowItem;
                nowItem = null;

            } else {
                itemId = item.getItemId();
            }

            switch (itemId) {
                case R.id.HomeUI:
                    getFragmentManager().popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    main_fragment mainFragment = new main_fragment();
                    fragmentTransaction.replace(R.id.frame_layout, mainFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.SearchUI:
                    getFragmentManager().popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    search_fragment searchFragment = new search_fragment();
                    fragmentTransaction.replace(R.id.frame_layout, searchFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.GalleryUI:
                    getFragmentManager().popBackStack("setting", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    record_fragment recordFragment = new record_fragment();
                    fragmentTransaction.replace(R.id.frame_layout, recordFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.Garbage:
                    //設定画面を開いたら色々情報が変わるのでバックスタックの内容をすべて消去（本当の理由は違う）
                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    myPreferenceFragment mypreferencefragment = new myPreferenceFragment();
                    fragmentTransaction.replace(R.id.frame_layout,mypreferencefragment);
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //アクションバーとしても動くようにする
        // setSupportActionBar(toolbar);


    }


//    //Preferenceフラグメント
//    public static class PrefsFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.preference);
//        }
//    }


}
