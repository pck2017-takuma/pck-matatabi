package kosien.procon.application;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/24.
 */

public class Record extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_main);

        //リストビュー用のフラグメントをセット

        RecordFragment mainFragment = new RecordFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.record_main_fragment,mainFragment);
        transaction.commit();
    }





}
