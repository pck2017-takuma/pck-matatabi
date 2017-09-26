package kosien.procon.application;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import su.heartlove.matatabi.R;


public class Sub_1 extends AppCompatActivity implements OnClickListener {
    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.sub);

        ImageButton a_button = (ImageButton) findViewById(R.id.search_button);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Set Menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.search_menu_search_view);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        // whether display Magnifying Glass Icon at first
        mSearchView.setIconifiedByDefault(true);

        // whether display Submit Button
        mSearchView.setSubmitButtonEnabled(false);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText){
                if(newText != null && !newText.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("NAME", newText);
                    SearchFragment fragment = new SearchFragment();
                    fragment.setArguments(bundle);
                    //TAGいるかな？
                    getFragmentManager().beginTransaction().replace(R.id.fragment_search, fragment).commit();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


       return super.onCreateOptionsMenu(menu);
    }



    public void onClick(View v) {

    }
}
