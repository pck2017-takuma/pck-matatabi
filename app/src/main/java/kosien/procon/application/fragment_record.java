package kosien.procon.application;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/24.
 */

public class fragment_record extends Fragment {

    //フラグメントで表示する内容
    private TextView mTextView;
    private infoTravelDao travelHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.fragment_record,container,false);
    }

    //ビューを生成し終わった後に呼ばれるメソッド

    @Override
    public void onViewCreated(View view,Bundle saveInstanceState) {
        travelHelper = new infoTravelDao(getContext());
        // ボタンを生成
        super.onViewCreated(view,saveInstanceState);
        Button btn = (Button)view.findViewById(R.id.edit_button);
        // レイアウトにボタンを追加



        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //スケジュール作成はとりあえずmainActivityに飛ばす


            }
        });

        //データベースからデータを取得する
        final ArrayList<infoTravel> travelData = travelHelper.load_item();

        ListView listView = (ListView)view.findViewById(R.id.sample_listview);
        ArrayList<SampleListItem> listItems = new ArrayList<SampleListItem>();

        if(travelData.size() == 0){
            Toast.makeText(getContext(),"データが存在しません",Toast.LENGTH_SHORT).show();
        }else {
            for (int i = 0; i < travelData.size(); i++) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
                SampleListItem item = new SampleListItem(bmp, travelData.get(i).getTravelTitle(), "No. " + String.valueOf(i));
                listItems.add(item);

                // 出力結果をリストビューに表示
                SetRecordListAdapter adapter = new SetRecordListAdapter(getContext(), R.layout.samplelist_item, listItems);
                listView.setAdapter(adapter);


                // アイテムクリック時ののイベントを追加
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int pos, long id) {

                        //リストビューの番号って０からよね？
                        infoTravel data = travelData.get(pos);

                        //詳細フラグメントに飛ぶ（人見氏実装後同様画面に飛ぶ、その際に＋でボタンを設置する
                        //こ↑こ↓のボタンは無効化する
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle bundle = new Bundle();

                        //とりあえずバンドルに一つ一つ値を入れていくｗｗ
                        bundle.putSerializable("infoTravel",data);

                        fragment_schedule recordFragment = new fragment_schedule();
                        recordFragment.setArguments(bundle);
                        fragmentTransaction.add(R.id.edit_diary,recordFragment);
                        fragmentTransaction.commit();

                    }




                });

            }
        }


    }

}


