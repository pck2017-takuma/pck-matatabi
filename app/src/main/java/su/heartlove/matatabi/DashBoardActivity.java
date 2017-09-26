package su.heartlove.matatabi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by procon-kyougi on 2017/09/24.
 */

public class DashBoardActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ListView表示用のフラグメント
        DashBoardFragment dashboardFragment = new DashBoardFragment();
        FragmentTransaction transactoin = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.dashboard_fragment,mainFragment);
        transaction.commit();
    }
}
