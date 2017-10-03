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

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi1 on 2017/10/02.
 */

public class TravelCreateFragment_2 extends Fragment {

    private int mposition = 0;
    private ArrayList<placeInfomation> yyy = new ArrayList<>();
    private placeInfoDao xxx;
    private travelCreateFragment tcf = new travelCreateFragment();


    public static  final String listKey = "placeList";
    public static final String addPlace = "addPlace";


    //決定した行先
    private ArrayList<placeInfomation>decidePlace = new ArrayList<>();


    //バンドルされる追加された行先
    private placeInfomation AddPlace = new placeInfomation();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);

        //これまでに保存したデータが存在するか
        Bundle addBundle = getArguments();

        if(addBundle != null && addBundle.containsKey(listKey)){
            decidePlace = (ArrayList<placeInfomation>)addBundle.getSerializable(listKey);
        }


        //詳細フラグメントからのデータが存在するかどうか
        if(addBundle!= null && addBundle.containsKey(addPlace)){
            AddPlace = (placeInfomation)addBundle.getSerializable(addPlace);
            decidePlace.add(AddPlace);

        }


        return inflater.inflate(R.layout.search_2, container,false);


    }

    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {



        super.onViewCreated(view, saveInstanceState);




    }


    @Override
    public void onStart() {
        yyy = new ArrayList<>();

        super.onStart();
        Button searchButton = (Button)getActivity().findViewById(R.id.search_button4);

        // レイアウトからリストビューを取得
        ListView listView = (ListView) getActivity().findViewById(R.id.travel_listview);
        ListView listView2 = (ListView) getActivity().findViewById(R.id.decide_list);

        Bundle intent = getArguments();
        placeInfomation test = (placeInfomation)intent.getSerializable("test");
        xxx = new placeInfoDao(tcf.getContext());
        if(test != null)
            yyy.add(test);


        // リストビューに表示する要素を設定
        ArrayList<SampleListItem> listItems = new ArrayList<>();
        ArrayList<SampleListItem> listItems2 = new ArrayList<>();

        if(yyy.size() == 0){
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            SampleListItem item = new SampleListItem(bmp,"検索候補がありません", "データがありません");

        }else {
            for (int i = 0; i < yyy.size(); i++) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
                SampleListItem item = new SampleListItem(bmp, yyy.get(i).getPlaceName(), yyy.get(i).getPlacePostNumber());
                listItems.add(item);
            }
        }

        if(decidePlace.isEmpty()){
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            SampleListItem item = new SampleListItem(bmp,"検索候補がありません", "データがありません");
        }else {
            for (int i = 0; i < decidePlace.size(); i++) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
                SampleListItem item = new SampleListItem(bmp, decidePlace.get(i).getPlaceName(), decidePlace.get(i).getPlacePostNumber());
                listItems2.add(item);
            }
        }

        // 出力結果をリストビューに表示
        SetRecordListAdapter adapter = new SetRecordListAdapter(getContext(), R.layout.samplelist_item, listItems);
        listView.setAdapter(adapter);

        SetRecordListAdapter adapter2 = new SetRecordListAdapter(getContext(), R.layout.samplelist_item, listItems2);
        listView2.setAdapter(adapter2);


        // ListViewアイテムを選択した場合の動作
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mposition = position;
                Bundle bundle = new Bundle();
                bundle.putSerializable("place",yyy.get(mposition));

                Bundle placeBundle = new Bundle();
                placeBundle.putSerializable(listKey,decidePlace);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TravelCreateDetailed intent = new TravelCreateDetailed();

                intent.setArguments(bundle);
                fragmentTransaction.replace(R.id.my_recycler_view,intent);
                fragmentTransaction.commit();

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle placeBundle = new Bundle();
                placeBundle.putSerializable(listKey,decidePlace);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                travelCreateFragment intent = new travelCreateFragment();
                fragmentTransaction.replace(R.id.my_recycler_view,intent);
                fragmentTransaction.commit();

            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(listKey,decidePlace);
        super.onSaveInstanceState(outState);

    }



    public placeInfomation infomation() {
        return yyy.get(mposition);
    }
}
