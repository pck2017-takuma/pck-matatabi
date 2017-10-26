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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kosien.procon.application.matatabidb.mydatabase.infoTravel;
import kosien.procon.application.matatabidb.mydatabase.travelSchedule;

/**
 * Created by i15317 on 2017/10/26.
 */


//スケジュールの受送信処理クラス




public class schedule_online {

    //PHPリンク
    public static final String GET_LIST_LINK = "http://150.15.103.131/php/schedule/schedule_get_list.php";
    public static final String GET_UPLOAD_LINK = "http://150.15.103.131/php/schedule/schedule_add_core.php";
    public static final String SET_USER_SCHEDULE = "http://150.15.103.131/php/schedule/schedule_add_main.php";
    //呼び出しスケジュール
    private ArrayList<ArrayList<travelSchedule>>schedule_list;
    private ArrayList<infoTravel>travelList;

    //呼び出し元フラグメントから送られたコンテキスト
    Context myContext;


    private int travelNum;

    //コンストラクタ：この段階でリストの取得も行う
    public schedule_online(Context context){
        //コンテキスト代入
        this.myContext = context;
    }


    public void getScheduleList(){

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
                ArrayList<travelSchedule>schedule = new ArrayList<>();
                JSONArray jsonArray = response.getJSONArray("schedule_list");

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject object = jsonArray.getJSONObject(i);

                    //ここからスケジュールの取得処理
                    try {
                        travelSchedule travelschedule = new travelSchedule();

                        //訪れる順番
                        int routeNum = object.getInt("schedule_num");
                        //訪れる場所
                        String placeName = object.getString("schedule_name");
                        //旅行番号
                        int travelNum = object.getInt("_id");

                        travelschedule.setPlaceName(placeName);
                        travelschedule.setFlag(0);
                        travelschedule.setTravelNum(travelNum);
                        travelschedule.setRouteNum(routeNum);
                        schedule.add(travelschedule);
                    } catch (JSONException ee) {
                        Toast.makeText(myContext,"JSONの取得に失敗しました。",Toast.LENGTH_SHORT).show();
                    }
                }
                schedule_list.add(schedule);
            }
        }catch (JSONException eee){

        }
    }
    public void UpLoadSchedule(final travelSchedule schedule){
        //スケジュールのアップロード処理を行う
        //queue
        RequestQueue postQueue = Volley.newRequestQueue(myContext);

        StringRequest stringReq=new StringRequest(Request.Method.POST,SET_USER_SCHEDULE,

                //通信成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(myContext,"スケジュールの登録に成功しました。",Toast.LENGTH_SHORT).show();
                        //リスト取得？
                    }
                },

                //通信失敗
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(myContext,"通信に失敗しました。",Toast.LENGTH_SHORT).show();
                    }
                }){

            //送信するデータを設定
            @Override
            protected Map<String,String> getParams(){
                //今回は[FastText：名前]と[SecondText：内容]を設定
                Map<String,String> params = new HashMap<String,String>();
                params.put("schedule_id",String.valueOf(schedule.getTravelNum()));
                params.put("schedule_num",String.valueOf(schedule.getRouteNum()));
                params.put("schedule_place",String.valueOf(schedule.getPlaceName()));
                return params;
            }
        };

        postQueue.add(stringReq);
    }

    public void UpLoadTraverlInfo(final infoTravel data){
        //queue
        RequestQueue postQueue = Volley.newRequestQueue(myContext);

        StringRequest stringReq=new StringRequest(Request.Method.POST,GET_UPLOAD_LINK,

                //通信成功
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(myContext,"通信に成功しました。",Toast.LENGTH_SHORT).show();
                        //レスポンス文字列をJSONにパースする
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            //トラベルナンバーを取得する
                            travelNum = jsonObject.getInt("_id");

                        }catch(JSONException e){
                            Toast.makeText(myContext,"JSONの取得に失敗しました。",Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                //通信失敗
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(myContext,"通信に失敗しました。",Toast.LENGTH_SHORT).show();
                    }
                }){

            //送信するデータを設定
            @Override
            protected Map<String,String> getParams(){
                //今回は[FastText：名前]と[SecondText：内容]を設定
                Map<String,String> params = new HashMap<String,String>();
                params.put("FastText",data.getTravelTitle().toString());
                return params;
            }
        };

        postQueue.add(stringReq);
    }

    //ゲッター
    public int getTravelNum(){
        return this.travelNum;
    }



}
