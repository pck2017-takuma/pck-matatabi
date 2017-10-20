package kosien.procon.application;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by procon-kyougi on 2017/10/13.
 */

public class myLocationListener implements LocationListener {

    //コンテキスト
    Context context;

    //Location Manager
    LocationManager locationManager;
    //プロバイダー
    String locationProvider;
    //ロケーション
    Location location;

    //    //緯度
//    private double latitude = 0.0;
//    //経度
//    private double longitude = 0.0;
    public double latitude = 0.0;
    public double longitude = 0.0;

    //位置情報を通知するための最小時間間隔（ミリ秒）
    private final long minTime = 500;

    //位置情報を通知するための最小距離間隔（メートル）
    private final long minDistance = 1;

    //コンストラクタでいろいろ生成
    public myLocationListener(Context context) {
        this.context = context;


    }

    public void getLocation() {
        //位置情報を管理しているマネージャーのインスタンスを生成
        locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);

        //GPSが利用可能になっているかチェックする
        assert locationManager != null;

        //GPSが利用可能になっているかチェックする
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationProvider = LocationManager.GPS_PROVIDER;
        }

        //GPSプロバイダーが有効になっていない場合は基地局情報が利用可能になっているかチェックする
        else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }

        //いずれも利用可能でない場合はGPSを設定する画面に状態遷移する
        else {
            //未実装
        }

        locationManager.requestLocationUpdates(locationProvider, minTime, minDistance, this);

        //位置情報を取得する
        location = locationManager.getLastKnownLocation(locationProvider);

        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    @Override
    public void onLocationChanged(Location location) {


    }

    //ロケーションプロバイダが利用不可能になるとコールバックされるメソッド
    @Override
    public void onProviderDisabled(String provider) {
        //ロケーションプロバイダーが使われなくなったらリムーブする必要がある
    }

    //ロケーションプロバイダが利用可能になるとコールバックされるメソッド
    @Override
    public void onProviderEnabled(String provider) {
        //プロバイダが利用可能になったら呼ばれる
    }

    //ロケーションステータスが変わるとコールバックされるメソッド
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // 利用可能なプロバイダの利用状態が変化したときに呼ばれる
    }


}
