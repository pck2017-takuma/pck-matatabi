package kosien.procon.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import su.heartlove.matatabi.R;

public class MainActivity extends Activity {

    ListView _listView;
    SetRecordListAdapter adapter;
    Button button1;
    Button button2;
    String travelName = null;
    boolean first = false;
    ArrayList<SampleListItem> listItem;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        _listView = (ListView) findViewById(R.id.list_view);
        adapter = null;
        button1 = (Button) findViewById(R.id.add_button);
        button2 = (Button) findViewById(R.id.decide_button);

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
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        _listView.setVisibility(View.GONE);

        //データベースオープン
        infoTravelDao travelHelper = new infoTravelDao(this);

        //旅行中データの照会
        boolean travelFlag = travelHelper.checkTravel();

        if(!travelFlag){

            if(first) {
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                _listView.setVisibility(View.VISIBLE);
            }
            else {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                travelCreateFragment recordFragment = new travelCreateFragment();
                fragmentTransaction.replace(R.id.my_recycler_view,recordFragment);
                fragmentTransaction.commit();
            }

            button1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    button1.setVisibility(View.GONE);
                    button2.setVisibility(View.GONE);
                    _listView.setVisibility(View.GONE);

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    travelCreateFragment recordFragment = new travelCreateFragment();
                    fragmentTransaction.replace(R.id.my_recycler_view,recordFragment);
                    fragmentTransaction.commit();

                }
            });

            button2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //テキスト入力を受け付けるビューを作成します。
                    final EditText editView = new EditText(MainActivity.this);
                    new AlertDialog.Builder(MainActivity.this)
                            //.setIcon(android.R.drawable.ic_dialog_info)
                            .setTitle("旅行に名前をつけてください。")
                            //setViewにてビューを設定します。
                            .setView(editView)
                            .setPositiveButton("決定!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    travelName = editView.getText().toString();
                                }
                            })
                            .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            })
                            .show();

                    TravelDataWrite tra = new TravelDataWrite();
                    tra.dataWrite(getApplication(), travelName);

                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                }
            });

        }else{
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Bundle bundle = new Bundle();

            bundle.putSerializable("infoTravel",travelHelper.getNowTravel());



            fragment_travel recordFragment = new fragment_travel();
            recordFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.my_recycler_view,recordFragment);
            fragmentTransaction.commit();
        }

    }

    public void setListView(){

        TravelCreateFragment_2 tc1 = new TravelCreateFragment_2();
        placeInfomation info = tc1.infomation();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        listItem = new ArrayList<>();
        SampleListItem item = new SampleListItem(bmp, info.getPlaceName(), info.getPlacePostNumber());
        listItem.add(item);

        adapter = new SetRecordListAdapter(this, R.layout.samplelist_item, listItem);
        _listView.setAdapter(adapter);
    }

    public String itemName (int i) {
        return listItem.get(i).getTitle();
    }
}
