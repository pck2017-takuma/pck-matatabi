package kosien.procon.application;

import android.content.Context;
import android.preference.PreferenceScreen;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;

/**
 * Created by i15317 on 2017/10/26.
 */


//スケジュールの受送信処理クラス




public class schedule_online {

    //PHPリンク
    public static final String GET_LIST_LINK = "http://150.15.103.131/php/schedule/xxxx.php";

    //呼び出しスケジュール
    ArrayList<ArrayList<travelSchedule>>schedule_list;
    ArrayList<infoTravel>travelList;

    //呼び出し元フラグメントから送られたコンテキスト
    Context myContext;

    //コンストラクタ：この段階でリストの取得も行う
    public schedule_online(Context context){
        //コンテキスト代入
        this.myContext = context;


        //queue
        RequestQueue getQueue= Volley.newRequestQueue(myContext);
        //Volleyによる通信開始　（GETかPOST、サーバーのURL、受信メゾット、エラーメゾット）
        JsonObjectRequest mRequest = new JsonObjectRequest(Request.Method.POST,GET_LIST_LINK,
                // 通信成功
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //レスポンスデータをパースする
                        ParseSchedule(response);
                    }
                },
                // 通信失敗
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(myContext,"通信に失敗しました。",Toast.LENGTH_SHORT).show();
                    }
                }
        );

        getQueue.add(mRequest);
    }


    private void ParseSchedule(JSONObject response) {
        //スケジュールリストのパース処理を行う
        try {

            JSONArray getResponse = response.getJSONArray("list");

            for (int i = 0; i < getResponse.length(); i++) {
                JSONObject getList = getResponse.getJSONObject(i);
                ArrayList<travelSchedule>schedule;
                infoTravel infotravel = new infoTravel();

                JSONArray jsonArray = response.getJSONArray("schedule_list");

                //


                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);

                    //ここからスケジュールの取得処理
                    try {
                        //訪れる順番
                        int routeNum = object.getInt("schedule_num");
                        //訪れる場y祖
                        String placeName = object.getString("schedule_name");


                    } catch (JSONException ee) {

                    }


                }


            }catch(JSONException e){
                Toast.makeText(myContext, "これはバグではない、仕様だ！", Toast.LENGTH_SHORT).show();

            }
        }catch (JSONException eee){

        }
    }
    private void UpLoadSchedule(travelSchedule schedule){

        //スケジュールのアップロード処理を行う


    }




}
