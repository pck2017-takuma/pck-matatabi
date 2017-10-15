package kosien.procon.application;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.infoTravelDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import su.heartlove.matatabi.R;

public class main_fragment extends Fragment {

    ListView _listView;
    SetRecordListAdapter adapter;
    Button button1;
    Button button2;
    String travelName = null;
    boolean first = false;
    ArrayList<SampleListItem> listItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);

        //ActivityのToolbar呼び出し
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);

        //Toolbarの動作定義






        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle icicle) {
        super.onCreate(icicle);
        _listView = (ListView) view.findViewById(R.id.list_view);
        adapter = null;
        button1 = (Button)view. findViewById(R.id.add_button);
        button2 = (Button) view.findViewById(R.id.decide_button);


        //こ↑こ↓から表示内容の選択
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        _listView.setVisibility(View.GONE);

        //データベースオープン
        infoTravelDao travelHelper = new infoTravelDao(getContext());

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
                fragment_travelcreate recordFragment = new fragment_travelcreate();
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
                    fragment_travelcreate recordFragment = new fragment_travelcreate();
                    fragmentTransaction.replace(R.id.my_recycler_view,recordFragment);
                    fragmentTransaction.commit();

                }
            });

            button2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //テキスト入力を受け付けるビューを作成します。
                    final EditText editView = new EditText(getContext());
                    new AlertDialog.Builder(getContext())
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

        fragmnet_travelcreate_list tc1 = new fragmnet_travelcreate_list();
        placeInfomation info = tc1.infomation();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        listItem = new ArrayList<>();
        SampleListItem item = new SampleListItem(bmp, info.getPlaceName(), info.getPlacePostNumber());
        listItem.add(item);

        adapter = new SetRecordListAdapter(getContext(), R.layout.samplelist_item, listItem);
        _listView.setAdapter(adapter);
    }

    public String itemName (int i) {
        return listItem.get(i).getTitle();
    }


    @Override
    public void onDestroyView(){
        //Toolbar
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        //何かをセットしていた場合はここで破壊する





        super.onDestroyView();
    }

}
