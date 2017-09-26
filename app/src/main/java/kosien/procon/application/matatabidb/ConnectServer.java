package kosien.procon.application.matatabidb;

/**
 * Created by procon-kyougi on 2017/09/22.
 */

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


//サーバークラス
public class ConnectServer extends AsyncTask<Void, Void, String> {
    private String JSON_LINK = new String();
    private JSONObject jsonData = null;

    ConnectServer(String url){
        JSON_LINK = new String(url);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // doInBackground前処理
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // doInBackground後処理
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection con = null;
        URL url = null;

        try {
            // URLの作成
            url = new URL(JSON_LINK);
            // 接続用HttpURLConnectionオブジェクト作成
            con = (HttpURLConnection)url.openConnection();
            // リクエストメソッドの設定
            con.setRequestMethod("POST");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            // URL接続からデータを読み取る場合はtrue
            con.setDoInput(true);
            // URL接続にデータを書き込む場合はtrue
           // con.setDoOutput(true);

            // 接続
            con.connect(); // ①
            InputStream in = con.getInputStream();
            String readSt = readInputStream(in);

            jsonData = new JSONObject(readSt);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }

        return null;
    }


    public String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while((st = br.readLine()) != null)
        {
            sb.append(st);
        }
        try
        {
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public JSONObject getObject(){
        return jsonData;
    }





}