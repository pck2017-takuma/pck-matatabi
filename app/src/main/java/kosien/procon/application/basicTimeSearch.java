package kosien.procon.application;

import android.net.Uri;

import kosien.procon.application.matatabidb.makeLink;

/**
 * Created by procon-kyougi on 2017/09/22.
 */


//始発駅と終着駅だけ指定、時刻は現在時刻で勝手に処理

public class basicTimeSearch extends makeLink {

    //
    // リンクテンプレート


    private int startStatioCode;
    private int endStationCode;

    //パラメータとか
    String scheme = "http";
    String authority = "api.rakuten.co.jp";
    String path = "/rws/3.0/json";

    String developerId = "?????????";
    String operation = "BooksBookSearch";
    String version = "2010-03-18";
    String size = "0";
    String hits = "10";
    String sort = "sales";



    //駅コードを指定する
    basicTimeSearch(int start_code,int end_code){
        startStatioCode = start_code;
        endStationCode = end_code;
    }



    @Override
    public String getSearchLink(){
        Uri.Builder uriBuilder = new Uri.Builder();

        uriBuilder.scheme(scheme);
        uriBuilder.authority(authority);
        uriBuilder.path(path);
        uriBuilder.appendQueryParameter("developerId", developerId);
        uriBuilder.appendQueryParameter("operation", operation);
        uriBuilder.appendQueryParameter("version", version);
        uriBuilder.appendQueryParameter("size", size);
        uriBuilder.appendQueryParameter("hits", hits);
        uriBuilder.appendQueryParameter("sort", sort);
        
        return uriBuilder.toString();
    }
}
