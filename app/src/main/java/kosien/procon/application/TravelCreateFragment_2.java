package kosien.procon.application;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.search_2, container,false);
    }

    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        // レイアウトからリストビューを取得
        ListView listView = (ListView) getActivity().findViewById(R.id.travel_listview);
        Intent intent = getActivity().getIntent();
        placeInfomation test = (placeInfomation)intent.getSerializableExtra("test");
        xxx = new placeInfoDao(tcf.getContext());
        yyy.add(test);


        // リストビューに表示する要素を設定
        ArrayList<SampleListItem> listItems = new ArrayList<>();
        for (int i = 0; i < yyy.size(); i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            SampleListItem item = new SampleListItem(bmp, yyy.get(i).getPlaceName(), yyy.get(i).getPlacePostNumber());
            listItems.add(item);
        }

        // 出力結果をリストビューに表示
        SetRecordListAdapter adapter = new SetRecordListAdapter(getContext(), R.layout.samplelist_item, listItems);
        listView.setAdapter(adapter);


        // ListViewアイテムを選択した場合の動作
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mposition = position;
                Intent intent = new Intent(getContext(), TravelCreateDetailed.class);
                intent.putExtra("place",yyy.get(mposition));
                startActivity(intent);
            }
        });
    }

    public placeInfomation infomation() {
        return yyy.get(mposition);
    }
}
