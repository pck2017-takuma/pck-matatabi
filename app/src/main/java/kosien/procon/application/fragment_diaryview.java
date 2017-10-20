package kosien.procon.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.Calendar;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/23.
 */

public class fragment_diaryview extends Fragment {
    private TextView tv_date = null;
    private TextView tv_diary = null;
    private Button btn_cancel = null;

    final Calendar calendar = Calendar.getInstance();
    int year = 0;
    int month = 0;
    int day = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.diary_view, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        super.onViewCreated(view, savedInstanceState);

        //リソースの割り当て
        tv_date = (TextView) view.findViewById(R.id.TextView_view_date);
        tv_diary = (TextView) view.findViewById(R.id.TextView_view_diary);
        btn_cancel = (Button) view.findViewById(R.id.Button_view_cancel);

        //引継ぎデータの取得
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);
        tv_date.setText(String.valueOf(year) + " / " + String.valueOf(month) + " / " + String.valueOf(day));
        tv_diary.setText(bundle.getString("diary"));

        //キャンセル処理
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(fragment_diaryview.this).commit();
            }
        });
    }
}
