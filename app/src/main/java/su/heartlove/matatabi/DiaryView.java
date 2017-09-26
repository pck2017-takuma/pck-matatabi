package su.heartlove.matatabi;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

/**
 * Created by procon-kyougi on 2017/09/23.
 */

public class DiaryView extends FragmentActivity {
    private TextView tv_date = null;
    private TextView tv_diary = null;
    private Button btn_cancel = null;

    final Calendar calendar = Calendar.getInstance();
    int year = 0;
    int month = 0;
    int day = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //リソースの割り当て
        tv_date = (TextView)findViewById(R.id.TextView_view_date);
        tv_diary= (TextView)findViewById(R.id.TextView_view_diary);
        btn_cancel = (Button)findViewById(R.id.Button_view_cancel);

        //引継ぎデータの取得
        Intent ReceiveIntent = this.getIntent();
        year = ReceiveIntent.getIntExtra("year",0);
        month = ReceiveIntent.getIntExtra("month",0);
        day = ReceiveIntent.getIntExtra("day",0);
        tv_date.setText(String.valueOf(year) + " / " + String.valueOf(month) + " / " + String.valueOf(day));
        tv_diary.setText(ReceiveIntent.getStringExtra("diary"));

        //キャンセル処理
        btn_cancel.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               finish();
           }
        });
    }
}
