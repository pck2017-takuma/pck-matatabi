package kosien.procon.application;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi1 on 2017/09/30.
 */

public class TravelCreateDetailed extends Activity {


    private placeInfomation info;
    private Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    private MainActivity main;

    @Override
    protected void onCreate(Bundle icicle) {
        Intent intent = getIntent();
        info = (placeInfomation)intent.getSerializableExtra("place");
        super.onCreate(icicle);
        setContentView(R.layout.travel_create_detailed);

        ImageView image = (ImageView) findViewById(R.id.image_veiw);
        TextView text1 = (TextView) findViewById(R.id.text_view1);
        TextView text2 = (TextView) findViewById(R.id.text_view2);
        TextView text3 = (TextView) findViewById(R.id.text_view3);
        Button button  = (Button) findViewById(R.id.button);

        image.setImageBitmap(bmp);
        text1.setText(info.getPlaceName());
        text2.setText(info.getPlaceAddress());
        text3.setText(info.getPlaceColumn());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setListView();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
