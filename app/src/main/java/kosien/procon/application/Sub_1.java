package kosien.procon.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Sub_1 extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.sub);

        ImageButton a_button = (ImageButton) findViewById(R.id.album_button);
        a_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_2.class);
                startActivity(intent);
            }
        });

        ImageButton b_button = (ImageButton) findViewById(R.id.option_button);
        b_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Sub_3.class);
                startActivity(intent);
            }
        });

        ImageButton c_button = (ImageButton) findViewById(R.id.schedule_button);
        c_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View v) {

    }
}
