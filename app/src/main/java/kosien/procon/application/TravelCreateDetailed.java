package kosien.procon.application;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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

public class TravelCreateDetailed extends Fragment {


    private placeInfomation info;
    private Bitmap bmp;
    private Bundle placeBundle = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        super.onCreateView(inflater,container,saveInstanceState);
        return inflater.inflate(R.layout.travel_create_detailed,container,false);
    }



    @Override
    public void onViewCreated(View view, final Bundle saveInstanceState) {
        Bundle intent = getArguments();
        info = (placeInfomation)intent.getSerializable("place");

        if(intent != null && intent.containsKey(TravelCreateFragment_2.listKey)){
            placeBundle = intent.getBundle(TravelCreateFragment_2.listKey);
        }

        ImageView image = (ImageView)view.findViewById(R.id.image_veiw);
        TextView text1 = (TextView)view.findViewById(R.id.text_view1);
        TextView text2 = (TextView)view.findViewById(R.id.text_view2);
        TextView text3 = (TextView)view.findViewById(R.id.text_view3);
        Button button  = (Button)view.findViewById(R.id.button);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        image.setImageBitmap(bmp);
        text1.setText(info.getPlaceName());
        text2.setText(info.getPlaceAddress());
        text3.setText(info.getPlaceColumn());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle insertBundle = new Bundle();

                Bundle bundle = new Bundle();
                //travelCreate呼び出し
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TravelCreateFragment_2 intent = new TravelCreateFragment_2();

                bundle.putSerializable(TravelCreateFragment_2.addPlace,info);


                if(placeBundle != null){

                    insertBundle.putBundle(TravelCreateFragment_2.listKey,placeBundle);
                }

                insertBundle.putBundle(TravelCreateFragment_2.addPlace,bundle);

                intent.setArguments(insertBundle);

                fragmentTransaction.replace(R.id.my_recycler_view,intent);
                fragmentTransaction.commit();

            }
        });









    }
}
