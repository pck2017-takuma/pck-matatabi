package kosien.procon.application;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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

public class travelCreateFragment extends Fragment {

    private EditText mEdit;
    private placeInfoDao xxx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.search_1,container,false);
    }

    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

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
                    Intent intent = new Intent(getContext(), TravelCreate.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "候補が見つかりませんでした。", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
