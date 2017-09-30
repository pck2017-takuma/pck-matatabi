package kosien.procon.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/24.
 */

public class Record extends Activity  {


    /**
     * アクティビティ生成時に呼ばれる
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.record_main);


        ImageButton a_button = (ImageButton) findViewById(R.id.option_button);
        a_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_3.class);
                startActivity(intent);
            }
        });

        ImageButton b_button = (ImageButton) findViewById(R.id.schedule_button);
        b_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton c_button = (ImageButton) findViewById(R.id.search_button);
        c_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_1.class);
                startActivity(intent);
            }
        });

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
//                Toast.makeText(Record.this,
//                        item, Toast.LENGTH_LONG
//                ).show();
//            }
//        });

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecordFragment recordFragment = new RecordFragment();
        fragmentTransaction.replace(R.id.fragment_record,recordFragment);
        fragmentTransaction.commit();



    }







}
