package com.example.tommy.mapsapp;

import android.content.Intent;
import android.os.Debug;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng seikei = new LatLng(35.7118, 139.573);
        mMap.addMarker(new MarkerOptions().position(seikei).title("Marker in Seikei"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seikei));
        mMap.moveCamera(CameraUpdateFactory.zoomTo( 15));


        // 地図タップイベント
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
               @Override
               public void onMapClick(LatLng point) {

                   // タップした位置の表示
                   Toast.makeText(getApplicationContext(),
                           "タップ位置\n緯度：" + point.latitude + "\n経度:" + point.longitude, Toast.LENGTH_LONG).show();

                   // TODO 緯度経度によってクイズに飛べるように
                   // 成蹊学園かどうか判定
                   if((point.latitude < 35.72 && point.latitude > 35.70)
                           && (point.longitude < 139.59 && point.longitude > 139.57) ){

                       Log.d("成蹊?","seikei!!!");

                       // TODO ここからToast処理
//                       Intent intent = new Intent(getApplicationContext(),StartActivity.class);
//                       startActivity(intent);


                   }

                   // マーカーを追加
//                   LatLng latLng = new LatLng(point.latitude, point.longitude);
//                   mMap.addMarker(new MarkerOptions().position(latLng).title("test"));

               }
           }
        );

        // 長押しのリスナーをセット
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng longpushLocation) {
                LatLng newlocation = new LatLng(longpushLocation.latitude, longpushLocation.longitude);
//                mMap.addMarker(new MarkerOptions().position(newlocation).title(""+longpushLocation.latitude+" :"+ longpushLocation.longitude));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newlocation, 15));

                // 緯度経度確認用
                Toast.makeText(getApplicationContext(), "タップ位置\n緯度：" + longpushLocation.latitude + "\n経度:" + longpushLocation.longitude, Toast.LENGTH_LONG).show();

            }
        });


    }






}
