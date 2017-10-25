package kosien.procon.application;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/25.
 */



public class myPreferenceFragment extends Fragment {

    //サーバー関連通知項目

    public static final String KEY_MY_NAME = "user_name";
    public static final String KEY_MY_ADDRESS = "user_address";
    public static final String KEY_MY_ID = "my_key";


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //フラグメント読み出し
        getActivity().getFragmentManager().beginTransaction().replace(R.id.frame_layout,new PrefsFragment()).addToBackStack(null).commit();
    }

    public static class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);

            //サマリーを設定
            setMyNameSummaryFraction();
            setMyAddressSummaryFraction();
            setMyIdSummaryFranction();

        }
        // 設定値が変更されたときのリスナーを登録
        @Override
        public void onResume() {
            super.onResume();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.registerOnSharedPreferenceChangeListener(listener);
        }

        // 設定値が変更されたときのリスナー登録を解除
        @Override
        public void onPause() {
            super.onPause();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.unregisterOnSharedPreferenceChangeListener(listener);
        }

        // 設定変更時に、Summaryを更新
        private SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(KEY_MY_NAME)) {
                    setMyNameSummaryFraction();
                }
                if(key.equals(KEY_MY_ADDRESS)){
                    setMyAddressSummaryFraction();
                }
                if(key.equals(KEY_MY_ID)){
                    setMyIdSummaryFranction();
                }
            }
        };

        // Fraction の Summary を設定
        private void setMyNameSummaryFraction() {
            EditTextPreference pref;
            pref = (EditTextPreference) findPreference(KEY_MY_NAME);
            String user = pref.getText();
            if (user == null) {
                user = "あなたのお名前を教えてください";
            }
            pref.setSummary(String.format("Username: %s", user));
        }

        private void setMyAddressSummaryFraction() {
            EditTextPreference pref;
            pref = (EditTextPreference) findPreference(KEY_MY_ADDRESS);
            String user = pref.getText();
            if (user == null) {
                user = "メールアドレス・アカウント名などを入れてください";
            }

            pref.setSummary(String.format("あなたの紹介: %s", user));
        }

        private void setMyIdSummaryFranction(){
            PreferenceScreen screen;
            screen =  (PreferenceScreen)findPreference(KEY_MY_ID);
            String key = screen.getKey();
            if(key == null){

            }else{
                screen.setSummary(String.format("あなたのID：%s",key));
            }
        }


        //名前と自己紹介が両方入ったときのみに
        private void tryServerInsert(){

            EditTextPreference addressPref;
            addressPref = (EditTextPreference) findPreference(KEY_MY_ADDRESS);
            String addressKey = addressPref.getText();
            EditTextPreference namePref;
            namePref = (EditTextPreference) findPreference(KEY_MY_NAME);
            String nameKey = namePref.getText();

            if(nameKey != null && addressKey != null){
                //データベースアップロード処理






            }







        }

    }
}