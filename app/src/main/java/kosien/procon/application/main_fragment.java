package kosien.procon.application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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


        //データベースオープン
        infoTravelDao travelHelper = new infoTravelDao(getContext());

        //旅行中データの照会
        boolean travelFlag = travelHelper.checkTravel();

        if(!travelFlag){

            if(first) {
            }
            else {
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment_travelcreate recordFragment = new fragment_travelcreate();
                fragmentTransaction.replace(R.id.my_recycler_view,recordFragment);
                fragmentTransaction.commit();


            }
//
//            button2.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //テキスト入力を受け付けるビューを作成します。
//                    final EditText editView = new EditText(getContext());
//                    new AlertDialog.Builder(getContext())
//                            //.setIcon(android.R.drawable.ic_dialog_info)
//                            .setTitle("旅行に名前をつけてください。")
//                            //setViewにてビューを設定します。
//                            .setView(editView)
//                            .setPositiveButton("決定!", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int whichButton) {
//                                    travelName = editView.getText().toString();
//                                }
//                            })
//                            .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int whichButton) {
//                                }
//                            })
//                            .show();
//
//                }
//            });

        }else{
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment_travel_core fragmentTravel = new fragment_travel_core();

            Bundle bundle = new Bundle();
            bundle.putSerializable("infoTravel",travelHelper.getNowTravel());
            fragmentTransaction.replace(R.id.my_recycler_view,fragmentTravel);
            fragmentTravel.setArguments(bundle);
            fragmentTransaction.commit();
        }

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
