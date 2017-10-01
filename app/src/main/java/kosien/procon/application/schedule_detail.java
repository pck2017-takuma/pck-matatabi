package kosien.procon.application;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.RecordDaoItem;
import kosien.procon.application.matatabidb.mydatabase.RecordItem;
import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/30.
 */

public class schedule_detail extends Fragment {

    //フラグメントで表示する内容
    private TextView mTextView;
    private infoTravelDao travelHelper;
    private travelSchedule viewData = new travelSchedule();
    private RecordDaoItem recordDB;
    private RecordItem myRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.fragment_detail_schedule, container, false);
    }

    //ビューを生成し終わった後に呼ばれるメソッド

    @Override
    public void onViewCreated(final View view, Bundle saveInstanceState) {
        travelHelper = new infoTravelDao(getContext());
        recordDB = new RecordDaoItem(getContext());
        //バンドルされたデータを取得する
        Bundle bundle = getArguments();
        infoTravel data = (infoTravel)bundle.getSerializable("infoTravel");

        // ボタンを生成
        super.onViewCreated(view, saveInstanceState);
        Button accept_button = (Button) view.findViewById(R.id.button1);
        Button view_button = (Button)view.findViewById(R.id.button2);

        //acceptボタンの割り当て
        accept_button.setText("日記を作成");
        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //日記の作成
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DiaryTop recordFragment = new DiaryTop();

                //旅行番号をバンドルする
                Bundle bundle = new Bundle();
                bundle.putInt("schedule",viewData.getRowid());
                bundle.putInt("travel",viewData.getTravelNum());
                fragmentTransaction.add(R.id.edit_diary, recordFragment);
                fragmentTransaction.commit();

                updateRecord();
            }
        });

        //日記を見るボタン
        view_button.setText("日記を見る");
        view_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateRecord();

                //日記の作成
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DiaryView recordFragment = new DiaryView();

                /* 参照画面で表示情報を引き継ぎ */
                Bundle bundle = new Bundle();
                bundle.putInt("year", myRecord.getDiaryYear());
                bundle.putInt("month", myRecord.getDiaryMon());
                bundle.putInt("day", myRecord.getDiaryDay());
                bundle.putString("diary", myRecord.getDiaryRecord());

                recordFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.fragment_record,recordFragment);
                fragmentTransaction.commit();

            }
        });



        ListView listView = (ListView) view.findViewById(R.id.sample_listview);
        ArrayList<SampleListItem> listItems = new ArrayList<SampleListItem>();

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
        SampleListItem item = new SampleListItem(bmp, "TravelNum text No. ", "sample No. " );
        listItems.add(item);


        // 出力結果をリストビューに表示
        SetRecordListAdapter adapter = new SetRecordListAdapter(getContext(), R.layout.fragment_searchlist, listItems);
        listView.setAdapter(adapter);

        // アイテムクリック時ののイベントを追加
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int pos, long id) {


                //詳細画面ではとりあえず何もしない（OK?）


            }


        });


    }

    private void updateRecord(){
        myRecord = recordDB.load_item(viewData.getRouteNum(),viewData.getTravelNum());

    }


}