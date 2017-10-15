package kosien.procon.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;
import kosien.procon.application.matatabidb.mydatabase.travelScheduleDao;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/30.
 */

public class fragment_schedule extends Fragment {

    //フラグメントで表示する内容
    private TextView mTextView;
    private travelScheduleDao scheduleDB;
    private infoTravelDao travelDB;
    ArrayList<travelSchedule> getList = new ArrayList<>();

    //バンドルデータ
    private infoTravel getData = null;

    //スケジュールデータを表示する
    ArrayList<travelSchedule> scheduleData = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.fragment_schedule,container,false);
    }

    //ビューを生成し終わった後に呼ばれるメソッド

    @Override
    public void onViewCreated(View view,Bundle saveInstanceState) {


        //スケジュールデータベースオープン
        scheduleDB = new travelScheduleDao(getContext());
        travelDB = new infoTravelDao(getContext());

        //バンドルされたデータを取得する
        Bundle bundle = getArguments();
        getData = (infoTravel)bundle.getSerializable("infoTravel");
        //スケジュール一覧を取得する
        if(scheduleDB.findSchedule(getData.getTravelNum())){
            getList = scheduleDB.getNowTravelList();
        }



        // ボタンを生成
        super.onViewCreated(view,saveInstanceState);



        Button accept_button = (Button)view.findViewById(R.id.edit_buttonn);


        //acceptボタンの割り当て

        if(getData.getTravelFlag() == 0){
            accept_button.setText("旅行を開始");
            accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //_idが１のやつを旅行中に、現在の選択項目もアクティブにする（デバック中は一回一回アンインストールしないと不具合が生じる）
                getData.setTravelFlag(1);

                travelSchedule start = getList.get(0);
                //アクティブにする
                start.setFlag(1);

                //データベース登録

                travelDB.save_time(getData);
                scheduleDB.sava_diary(start);

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                main_fragment mainFragment = new main_fragment();
                transaction.replace(R.id.frame_layout,mainFragment);
                transaction.commit();
            }
        });

        }else{
            accept_button.setText("旅行を終了");
            accept_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ここで旅行をこのスケジュールを終了状態にする
                    getData.setTravelFlag(0);
                    travelDB.save_time(getData);

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    main_fragment mainFragment = new main_fragment();
                    transaction.replace(R.id.frame_layout,mainFragment);
                    transaction.commit();

                }
            });


        }

        ListView listView = (ListView)view.findViewById(R.id.sample_listvieww);
        ArrayList<SampleListItem> listItems = new ArrayList<SampleListItem>();

        if(getList.size() == 0){
            Toast.makeText(getContext(),"データが存在しません",Toast.LENGTH_SHORT).show();
        }else {
         for(travelSchedule x:getList){

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
                SampleListItem item = new SampleListItem(bmp, x.getPlaceName(), x.getPlaceName());
                listItems.add(item);

                // 出力結果をリストビューに表示
                SetRecordListAdapter adapter = new SetRecordListAdapter(getContext(), R.layout.samplelist_item, listItems);
                listView.setAdapter(adapter);

                // アイテムクリック時ののイベントを追加
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int pos, long id) {

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        //データをバンドル
                        Bundle bundle = new Bundle();

                        //バンドルデータを取得
                        travelSchedule tmp = getList.get(pos);
                        bundle.putSerializable("infoTravel",tmp);
                        //フラグメントを立ち上げる
                        //こ↑こ↓のボタンは無効化する
                        fragment_schedule_detail recordFragment = new fragment_schedule_detail();
                        recordFragment.setArguments(bundle);
                        fragmentTransaction.add(R.id.detail_schedule,recordFragment);
                        fragmentTransaction.commit();

                    }




                });

            }
        }


    }

}
