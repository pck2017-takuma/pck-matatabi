package kosien.procon.application;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;

import su.heartlove.matatabi.R;

/**
 * Created by i15317 on 2017/10/19.
 */

public class Preference extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    //Preferenceフラグメント
    public static class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }


}
