package kosien.procon.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/24.
 */

public class RecordFragment extends Fragment {

    //フラグメントで表示する内容
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.fragment_record,container,false);
    }

    //ビューを生成し終わった後に呼ばれるメソッド

    @Override
    public void onViewCreated(View view,Bundle saveInstanceState) {
        super.onViewCreated(view,saveInstanceState);

        //ここに表示内容を指定して表示する
        //mTextView = (TextView)view.findViewById(R.id.textView);


        //クリックイベントはこちら




    }





}
