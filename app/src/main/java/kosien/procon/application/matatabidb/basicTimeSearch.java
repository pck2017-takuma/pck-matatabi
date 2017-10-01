package kosien.procon.application.matatabidb;

import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import kosien.procon.application.matatabidb.makeLink;

/**
 * Created by procon-kyougi on 2017/09/22.
 */


//始発駅と終着駅だけ指定、時刻は現在時刻で勝手に処理

public class basicTimeSearch extends makeLink {

    //経路検索用
    String url = "https://api.apigw.smt.docomo.ne.jp/ekispertCorp/v1/searchCourseExtreme?APIKEY="+API_CODE;
    //駅コード取得用

    //最低限の検索機能
    String url_base = "&viaList=";


    private String startStation = null;
    private String endStation = null;
    private String endlatitude = null;
    private String endlongitude = null;
    private final String encoding = "UTF-8";

    public basicTimeSearch(String s,String b){
        startStation = s;
        endStation = b;
    }

    public void setDestination(String _latitude,String _longitude){
        this.endlatitude = _latitude;
        this.endlongitude = _longitude;
    }



    @Override
    public String getSearchLink(){

        //まずは最低限の検索機能

        //日本語をURLエンコード
        String startEncoding = null;
        String goalEncoding  =null;

        try {
            startEncoding = URLEncoder.encode(startStation, encoding);
            goalEncoding = URLEncoder.encode(endStation,encoding);
        }catch(UnsupportedEncodingException e){
            //例外を投げたらスルー
        }

        return url + url_base + startEncoding + ":"+goalEncoding;

    }

    //経由地をArrayList<String>で指定する

    public String getViaSearchLink(ArrayList<String> viaStation){

        //機能保存
        ArrayList<String>endodeString = new ArrayList<String>();
        String resultUrl = new String();

        //最低限の検索機能
        for(String x:viaStation){
            try {

                endodeString.add(URLEncoder.encode(x, encoding));

            }catch(UnsupportedEncodingException e){

            }

            //リンク生成


        }
        return resultUrl;

    }

}
