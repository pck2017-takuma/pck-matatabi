package kosien.procon.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi1 on 2017/09/30.
 */

public class fragment_travelcreate_detail extends Fragment {


    private placeInfomation info;
    private Bitmap bmp;
    private Bundle placeBundle = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.place_info_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {
        Bundle intent = getArguments();
        info = (placeInfomation) intent.getSerializable("place");

        if (intent != null && intent.containsKey(fragmnet_travelcreate_list.listKey)) {
            placeBundle = intent.getBundle(fragmnet_travelcreate_list.listKey);
        }


        ImageView placeImage = (ImageView) view.findViewById(R.id.place_image);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView placeTitle = (TextView) view.findViewById(R.id.place_title);
        TextView placeEat = (TextView) view.findViewById(R.id.place_eat);
        TextView placeColumn = (TextView) view.findViewById(R.id.place_column);
        TextView actionText = (TextView)view.findViewById(R.id.place_action);
        Button button = (Button) view.findViewById(R.id.button);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        image.setImageBitmap(bmp);
        placeTitle.setText(info.getPlaceName());
        placeEat.setText(info.getPlaceAddress());
        placeColumn.setText(info.getPlaceColumn());



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle insertBundle = new Bundle();

                Bundle bundle = new Bundle();
                //travelCreate呼び出し
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmnet_travelcreate_list intent = new fragmnet_travelcreate_list();

                bundle.putSerializable(fragmnet_travelcreate_list.addPlace, info);


                if (placeBundle != null) {

                    insertBundle.putBundle(fragmnet_travelcreate_list.listKey, placeBundle);
                }

                insertBundle.putBundle(fragmnet_travelcreate_list.addPlace, bundle);

                intent.setArguments(insertBundle);

                fragmentTransaction.replace(R.id.my_recycler_view, intent);
                fragmentTransaction.commit();

            }
        });


    }
}
