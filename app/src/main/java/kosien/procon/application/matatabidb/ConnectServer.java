package kosien.procon.application.matatabidb;

/**
 * Created by procon-kyougi on 2017/09/22.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.RouteInfo;


//サーバークラス
public class ConnectServer extends AsyncTask<String, Integer, String> {
    public ArrayList<ArrayList<RouteInfo>> resultData;
    private AsyncCallback _asyncCallback = null;
        /*
        * Activityへコールバック用InterFace
        * */


    public interface AsyncCallback {
        //      void onPreExecute();
        void onPostExecute(String result);
        //      void onProgressUpdate(int progress);
        //  void onCancelled();
    }

    public ConnectServer(AsyncCallback asyncCallback) {
        this._asyncCallback = asyncCallback;
    }

    protected String doInBackground(String... urls) {


        return httpGet(urls[0]);
    }

//       // @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            this._asyncCallback.onPreExecute();
//            return ;
//        }

    //@Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            this._asyncCallback.onProgressUpdate(values[0]);
//        }

    //@Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this._asyncCallback.onPostExecute(result);
    }

//       // @Override
//        protected void onCancelled() {
//            super.onCancelled();
//            this._asyncCallback.onCancelled();
//        }


    /*
    * httpGet通信処理
    * */
    protected String httpGet(String urls) {

        HttpURLConnection urlCon;
        InputStream in;

        //Httpコネクションを確立し、URLを叩いて情報を取得
        try {
            System.out.println(urls);
            urlCon = (HttpURLConnection) new URL(urls).openConnection();
            urlCon.setRequestMethod("GET");
            urlCon.setDoInput(true);
            urlCon.connect();

            String str_json = new String();
            in = urlCon.getInputStream();
            InputStreamReader objReader = new InputStreamReader(in);
            BufferedReader objBuf = new BufferedReader(objReader);
            StringBuilder strBuilder = new StringBuilder();
            String sLine;
            while ((sLine = objBuf.readLine()) != null) {
                strBuilder.append(sLine);
            }
            str_json = strBuilder.toString();
            in.close();

            return str_json;

        } catch (IOException e) {
            e.printStackTrace();
            return "network_error";
        }
    }


    //パースデータ取得
    public ArrayList<ArrayList<RouteInfo>> getParseData() {
        return resultData;
    }


}



