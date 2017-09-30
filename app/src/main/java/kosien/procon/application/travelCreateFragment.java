package kosien.procon.application;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/29.
 */

public class travelCreateFragment extends Fragment {

    private EditText mEdit;
    private Button mButton;

    private placeInfoDao xxx;
    private placeInfomation placeInfomation;
    private ArrayList<placeInfomation> yyy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.search_1,container,false);
    }

    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        xxx = new placeInfoDao(getContext());
        Button btn = (Button) view.findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = mEdit.getText().toString();
                if(xxx.findPlaceInfo(search,placeInfomation.PLACE_NAME)){
                    yyy = xxx.getSearchData();

                }


            }
        });
    }
}
