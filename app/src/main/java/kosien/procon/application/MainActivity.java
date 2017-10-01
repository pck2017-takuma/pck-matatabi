package kosien.procon.application;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import su.heartlove.matatabi.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);


        //こ↑こ↓画面遷移
        ImageButton a_button = (ImageButton) findViewById(R.id.search_button);
        a_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_1.class);
                startActivity(intent);
            }
        });

        ImageButton b_button = (ImageButton) findViewById(R.id.album_button);
        b_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Record.class);
                startActivity(intent);
            }
        });

        ImageButton c_button = (ImageButton) findViewById(R.id.option_button);
        c_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_3.class);
                startActivity(intent);
            }
        });


        //こ↑こ↓から表示内容の選択

        //データベースオープン
        infoTravelDao travelHelper = new infoTravelDao(this);

        //旅行中データの照会
        boolean travelFlag = false;

        //旅行中なら
        if(travelFlag){

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RecordFragment recordFragment = new RecordFragment();
            fragmentTransaction.replace(R.id.my_recycler_view,recordFragment);
            fragmentTransaction.commit();

        }else{

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            travelCreateFragment recordFragment = new travelCreateFragment();
            fragmentTransaction.replace(R.id.my_recycler_view,recordFragment);
            fragmentTransaction.commit();
        }

    }

    public void setListView(){

        TravelCreate tc1 = new TravelCreate();
        placeInfomation info = tc1.infomation();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ListView _listView = (ListView) findViewById(R.id.list_view);

        ArrayList<SampleListItem> listItem = new ArrayList<>();
        SampleListItem item = new SampleListItem(bmp, info.getPlaceName(), info.getPlacePostNumber());
        listItem.add(item);

        SetRecordListAdapter adapter = new SetRecordListAdapter(this, R.layout.samplelist_item, listItem);
        _listView.setAdapter(adapter);
    }
}
