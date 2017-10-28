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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import su.heartlove.matatabi.R;

/**
 * Created by procon-kyougi on 2017/10/25.
 */



public class myPreferenceFragment extends Fragment {

    //サーバー関連通知項目

    public static final String KEY_MY_NAME = "user_name";
    public static final String KEY_MY_ADDRESS = "user_address";
    public static final String KEY_MY_ID = "my_key";
    public static final String USE_ADD_LINK = "http://150.15.103.131/php/user_add.php";


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
                //こ↑こ↓からデータベースアップロード処理

                //queue
                RequestQueue getQueue=Volley.newRequestQueue(getContext());
                //Volleyによる通信開始　（GETかPOST、サーバーのURL、受信メゾット、エラーメゾット）
                JsonObjectRequest mRequest = new JsonObjectRequest(Request.Method.POST,USE_ADD_LINK,
                        // 通信成功
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //こ↑こ↓からJSON解析
                                try{
                                    final int user_id = response.getInt("user_id");
                                    PreferenceScreen screen;
                                    screen =  (PreferenceScreen)findPreference(KEY_MY_ID);

                                    //デバック用にuser_idを表示する
                                    Toast.makeText(getContext(),"登録されたID： " + String.valueOf(user_id),Toast.LENGTH_SHORT).show();


                                    screen.setKey(String.valueOf(user_id));

                                }catch(JSONException e){
                                    Toast.makeText(getContext(),"IDの読み込みに失敗しました",Toast.LENGTH_SHORT).show();

                                }


                            }
                        },

                        // 通信失敗
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(),"通信に失敗しました。",Toast.LENGTH_SHORT).show();
                            }
                        }
                );

                getQueue.add(mRequest);

            }

        }

    }
}