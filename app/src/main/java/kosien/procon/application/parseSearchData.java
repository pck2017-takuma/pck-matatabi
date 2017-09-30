package kosien.procon.application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.RouteInfo;


/**
 * Created by procon-kyougi on 2017/09/23.
 */

//時刻検索データパース




public class parseSearchData {

    //カラム名
    private static final String course = "Course";
    //金額
    private static final String price = "Price";
    /****************************************************************************************price配下****************************************************************************************/
    private static final String type = "Type";
    /********************************************************************************************************************************************************************************/

    //Route
    private static final String route = "Route";
    /****************************************************************************************Route配下****************************************************************************************/

    private static final String line = "Line";

    //Line配下
    private static final String arraival = "ArrivalState";
    private static final String departure = "DepartureState";
    //Line配下ここまで

    /********************************************************************************************************************************************************************************/

    private static final String point = "Point";

    //とりあえず出発駅・到着駅・時刻を返す
    private ArrayList<ArrayList<RouteInfo>>routeInfo;



    //受け取ったJSONオブジェクト
    JSONObject jsonObject = new JSONObject();
    parseSearchData(JSONObject srcObject){
        jsonObject = srcObject;
        ParseObject();
    }


    private void ParseObject(){
        //指定の階層まですすんでそれぞれをJSONArrayに入れる
        //course
        JSONArray courceJSONarray = null;

        try {
            courceJSONarray = jsonObject.getJSONArray(course);
        }catch(JSONException e){
            //エラーが出たら詰むしかない
        }

        //course配下のオブジェクトを取得
        int courceCnt = courceJSONarray.length();

        //courceの要素を１つずつ配列に格納する
        JSONObject[] cource = new JSONObject[courceCnt];

       for(int i = 0; i < courceCnt;i++){
           try{
               cource[i] = courceJSONarray.getJSONObject(i);

           }catch (JSONException e){

           }
       }
       ParseJSONCourse(cource,courceCnt);
    }

    private void ParseJSONCourse(JSONObject[] srcData,int arraySize){
        //RouteとPriceがそれぞれドヴァーと生成される
        ArrayList<JSONArray>priceJSONobject = new ArrayList<>();
        ArrayList<JSONObject>routeJSONobject = new ArrayList<>();

        for(int i = 0; i < arraySize;i++){
            try{
                routeJSONobject.add(srcData[i].getJSONObject(route));
                priceJSONobject.add(srcData[i].getJSONArray(price));
            }catch(JSONException e){
                //隠し機能
                continue;
            }
        }

        //routeのパース

        for(JSONObject x:routeJSONobject){
            int getCount = 0;
            try {
                getCount = x.getInt("transferCount");
            }catch(JSONException e){
                //オプション
            }

            if(getCount == 0) {

                //乗り換え回数が０の時はすべてJSONObjectになる
                parseRouteObject(x);

            }else{
                ParseJSONroute(x);

            }

        }

        //priceのパース（自前で料金計算アルゴリズムが完成するまではスルー）
        //検索候補分の料金情報に対して
        for(JSONArray x:priceJSONobject){

            //１検索候補についてあげられる料金オプションが全件取得される
            JSONObject[] getPriceObject = ParseJSONprice(x);
        }
        
    }

    //乗り換え回数が０の時
    private void parseRouteObject(JSONObject routeJSON){
        ArrayList<RouteInfo>data = new ArrayList<RouteInfo>();
        ArrayList<String>stationData = new ArrayList<String>();


        JSONObject lineJSONObject = new JSONObject();
        JSONObject arrivalJSONObject = new JSONObject();
        JSONObject depatureJSONObject = new JSONObject();
        //ここはarrayで問題ないと思う
        JSONArray pointJSONArray = new JSONArray();

        try{
            lineJSONObject = routeJSON.getJSONObject(line);
            arrivalJSONObject = routeJSON.getJSONObject(arraival);
            depatureJSONObject = routeJSON.getJSONObject(departure);
            pointJSONArray = routeJSON.getJSONArray(point);

        }catch(JSONException e){

        }

        JSONObject[] parsePoint = ParseJSONpoint(pointJSONArray);

        //pointから駅名を取得
        for(int i = 0; i < parsePoint.length;i++){
            try {
                JSONArray station = parsePoint[i].getJSONArray("Station");
                //stationの２番目に駅名が入っているはず
                stationData.add(station.getString(1));
            }catch(JSONException e){

            }
        }
        String trainName = null;
        JSONArray arrival = null;
        JSONArray depature = null;
        JSONArray depatureDatetime = null;
        JSONArray arrivalDateTime = null;

        RouteInfo pushData = new RouteInfo();

        //順番に列車の発車時刻・到着時刻・列車名を取得する
        try {
            trainName = routeJSON.getString("Name");
            arrival = routeJSON.getJSONArray("ArrivalState");
            depature = routeJSON.getJSONArray("DepartureState");

        }catch(JSONException e){

        }

        try {
            arrivalDateTime = arrival.getJSONArray(2);
            depatureDatetime = depature.getJSONArray(2);
        }catch(JSONException e){

        }


        String arriveTime = null;
        String depatureTime = null;


        try {
            arriveTime = arrivalDateTime.getString(0);
            depatureTime = depatureDatetime.getString(0);
        }catch(JSONException e){

        }
        pushData.setRouteDeparture(stationData.get(0));
        pushData.setRouteDestination(stationData.get(1));
        pushData.setRouteArvtime(arriveTime);
        pushData.setRouteDepttime(depatureTime);
        pushData.setRouteTrain(trainName);

        data.add(pushData);

        routeInfo.add(data);
    }//関数ここまで


    private void ParseJSONroute(JSONObject routeJSON){

        JSONArray lineJSONarray = new JSONArray();
        JSONArray arrivalJSONarray = new JSONArray();
        JSONArray depatureJSONarray = new JSONArray();
        JSONArray pointJSONarray = new JSONArray();


        try{
            lineJSONarray = routeJSON.getJSONArray(line);
            arrivalJSONarray = routeJSON.getJSONArray(arraival);
            depatureJSONarray = routeJSON.getJSONArray(departure);
            pointJSONarray = routeJSON.getJSONArray(point);

        }catch(JSONException e){

        }

        //それぞれのデータをパースする
        JSONObject[] parsePoint = ParseJSONpoint(pointJSONarray);
        JSONObject[] parseLine = ParseJSONline(lineJSONarray);

        ArrayList<RouteInfo>data = new ArrayList<RouteInfo>();
        ArrayList<String>stationData = new ArrayList<String>();

        //pointから駅名を取得
        for(int i = 0; i < parsePoint.length;i++){
            try {
                JSONArray station = parsePoint[i].getJSONArray("Station");
                //stationの２番目に駅名が入っているはず
                stationData.add(station.getString(1));
            }catch(JSONException e){

            }
        }

        //列車情報を取得
        int i = 0;
        for(JSONObject x:parseLine){


            String trainName = null;
            JSONArray arrival = null;
            JSONArray depature = null;
            JSONArray depatureDatetime = null;
            JSONArray arrivalDateTime = null;

            RouteInfo pushData = new RouteInfo();

            //順番に列車の発車時刻・到着時刻・列車名を取得する
            try {
                trainName = x.getString("Name");
                arrival = x.getJSONArray("ArrivalState");
                depature = x.getJSONArray("DepartureState");

            }catch(JSONException e){

            }

            try {
                arrivalDateTime = arrival.getJSONArray(2);
                depatureDatetime = depature.getJSONArray(2);
            }catch(JSONException e){

            }


            String arriveTime = null;
            String depatureTime = null;


            try {
                arriveTime = arrivalDateTime.getString(0);
                depatureTime = depatureDatetime.getString(0);
            }catch(JSONException e){

            }
            pushData.setRouteDeparture(stationData.get(i));
            pushData.setRouteDestination(stationData.get(i + 1));
            pushData.setRouteArvtime(arriveTime);
            pushData.setRouteDepttime(depatureTime);
            pushData.setRouteTrain(trainName);
            data.add(pushData);
        }
        routeInfo.add(data);
    }


    //lineはこれで必要データが整う、ちなみにpriceでも同様に出来ると思われる
    private JSONObject[] ParseJSONline(JSONArray JSONline){

        int lineCnt = JSONline.length();
        JSONObject[] lineObject = new JSONObject[lineCnt];

        for(int i = 0; i < lineCnt;i++) {
            try {
                lineObject[i] = JSONline.getJSONObject(i);

            }catch(JSONException e){
                continue;
            }
        }
        return lineObject;
    }

    //lineはこれで必要データが整う、ちなみにpriceでも同様に出来ると思われる
    private JSONObject[] ParseJSONprice(JSONArray JSONprice){

        int priceCnt = JSONprice.length();
        JSONObject[] lineObject = new JSONObject[priceCnt];

        for(int i = 0; i < priceCnt;i++) {
            try {
                lineObject[i] = JSONprice.getJSONObject(i);

            }catch(JSONException e){
                continue;
            }
        }
        return lineObject;
    }



    private JSONObject[] ParseJSONpoint(JSONArray JSONpoint){

        int pointCnt = JSONpoint.length();
        JSONObject[] pointObject = new JSONObject[pointCnt];

        for(int i = 0; i < pointCnt;i++) {
            try {
               pointObject[i] = JSONpoint.getJSONObject(i);

            }catch(JSONException e){
                continue;
            }
        }

        return pointObject;
    }



}
