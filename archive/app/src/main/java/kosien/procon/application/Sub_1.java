package kosien.procon.application;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import su.heartlove.matatabi.R;


public class Sub_1 extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.sub);

        ImageButton a_button = (ImageButton) findViewById(R.id.album_button);
        a_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_2.class);
                startActivity(intent);
            }
        });

        ImageButton b_button = (ImageButton) findViewById(R.id.option_button);
        b_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_3.class);
                startActivity(intent);
            }
        });

        ImageButton c_button = (ImageButton) findViewById(R.id.schedule_button);
        c_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        ////検索ボタンのフラグメント呼び出し


    }

    public void onClick(View v) {

    }
}
