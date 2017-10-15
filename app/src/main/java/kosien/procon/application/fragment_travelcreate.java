package kosien.procon.application;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kosien.procon.application.matatabidb.mydatabase.placeInfoDao;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/09/29.
 */

public class fragment_travelcreate extends Fragment {

    private EditText mEdit;
    private placeInfoDao xxx;

    Bundle placeBundle = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.search_1,container,false);
    }

    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        Bundle tmp = getArguments();
        if(tmp != null && tmp.containsKey(fragmnet_travelcreate_list.listKey)){
            placeBundle = tmp.getBundle(fragmnet_travelcreate_list.listKey);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        mEdit = (EditText) getActivity().findViewById(R.id.edit_text);
        xxx = new placeInfoDao(getContext());

        // ボタンを取得して、ClickListenerをセット
        Button btn = (Button) getActivity().findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = mEdit.getText().toString();
                if(xxx.findPlaceInfo(search, placeInfomation.PLACE_NAME)){
                    placeInfomation tmp = xxx.getSearchResult().get(0);

                    Bundle bundle = new Bundle();

                    bundle.putSerializable("test",tmp);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmnet_travelcreate_list recordFragment = new fragmnet_travelcreate_list();

                    recordFragment.setArguments(bundle);

                    if(placeBundle != null){
                        bundle.putBundle(fragmnet_travelcreate_list.listKey,placeBundle);
                    }

                    fragmentTransaction.replace(R.id.my_recycler_view,recordFragment);
                    fragmentTransaction.commit();

                }
                else {
                    Toast.makeText(getActivity(), "候補が見つかりませんでした。", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
