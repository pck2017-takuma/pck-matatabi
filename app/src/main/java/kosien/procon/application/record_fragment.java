package kosien.procon.application;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/24.
 */

public class record_fragment extends Fragment {


    /**
     * アクティビティ生成時に呼ばれる
     *
     * @param savedInstanceState
     */


    private Integer nowItem = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.activity_record, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


//
//        // リストビューに表示する要素を設定
//        ArrayList<SampleListItem> listItems = new ArrayList<>();
//
//        if(travelData.size() == 0){
//            Toast.makeText(this,"データが存在しません",Toast.LENGTH_SHORT).show();
//        }else {
//            for (int i = 0; i < travelData.size(); i++) {
//                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
//                SampleListItem item = new SampleListItem(bmp, "TravelNum text No. " + String.valueOf(i), "sample No. " + String.valueOf(i));
//                listItems.add(item);
//            }
//        }
//
//        // 出力結果をリストビューに表示
//        SetRecordListAdapter adapter = new SetRecordListAdapter(this, R.layout.samplelist_item, listItems);
//        listView.setAdapter(adapter);
//
//
//        // アイテムクリック時ののイベントを追加
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent,
//                                    View view, int pos, long id) {
//
//                // 選択アイテムを取得
//                ListView listView2 = (ListView)parent;
//                String item = (String)listView2.getItemAtPosition(pos);
//
//                // 通知ダイアログを表示
//                Toast.makeText(record_fragment.this,
//                        item, Toast.LENGTH_LONG
//                ).show();
//            }
//        });

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment_record recordFragment = new fragment_record();
        fragmentTransaction.replace(R.id.fragment_record, recordFragment);
        fragmentTransaction.commit();


    }


}
