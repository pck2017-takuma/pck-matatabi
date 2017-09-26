package kosien.procon.application;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import su.heartlove.matatabi.R;

public class Sub_2 extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_search);

        //フラグメントマネージャーを指定
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //サーチフラグメントを作成
        SearchFragment searchFragment = new SearchFragment();
        //フラグメントを組み込む
        transaction.replace(R.id.menu_search,searchFragment);
        //バックスタックに追加
        transaction.addToBackStack(null);

        ImageButton a_button = (ImageButton) findViewById(R.id.option_button);
        a_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_3.class);
                startActivity(intent);
            }
        });

        ImageButton b_button = (ImageButton) findViewById(R.id.schedule_button);
        b_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton c_button = (ImageButton) findViewById(R.id.search_button);
        c_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_1.class);
                startActivity(intent);
            }
        });


        //コミットする
        transaction.commit();


    }


    public void onClick(View v) {

    }
}