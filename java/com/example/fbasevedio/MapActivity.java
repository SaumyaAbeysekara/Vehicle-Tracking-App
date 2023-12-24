package com.example.fbasevedio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private TextView driverTextView;
    private TextView driverMobileTextView;
    private TextView driverNameTextView;
    private View bottomSheet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);






    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {

        googleMap = map;
        fetchThingSpeakData();

//
//        LatLng location = new LatLng(4.5709,74.2973);
//
//        googleMap.addMarker(new MarkerOptions()
//                .position(location)
//                .title("Marker Title") // You can set a title for the marker
//                .snippet("Marker Snippet") // You can set additional information
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))); // Set marker color
//
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
//
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,1f));


    }

    private void fetchThingSpeakData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://thingspeak.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ThinkSpeakApi thinkSpeakApi = retrofit.create(ThinkSpeakApi.class);

        Call<ThingSpeakData> call = thinkSpeakApi.getChannelData();
        call.enqueue(new Callback<ThingSpeakData>() {

            @Override
            public void onResponse(Call<ThingSpeakData> call, Response<ThingSpeakData> response) {
                if (response.isSuccessful()) {
                    ThingSpeakData data = response.body();
                    List<ThingSpeakData.Feed> feeds = data.getFeeds();

                    googleMap.clear();

                    for (ThingSpeakData.Feed feed : feeds) {
                        String field1 = feed.getField1();
                        String field2 = feed.getField2();
                        String entryDate = feed.getCreate_at();


                            if (field1 != null && !field1.isEmpty() && field2 != null && !field2.isEmpty()) {
                                try {
                                    double latitude = Double.parseDouble(field1);
                                    double longitude = Double.parseDouble(field2);

                                    LatLng markerPosition = new LatLng(latitude, longitude);
                                    googleMap.addMarker(new MarkerOptions().position(markerPosition)
                                            .title("Entry ID:" + feed.getEntry_id())
                                            .snippet("Field1: " + field1 + "\n Field2:" + field2)
                                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                                } catch (NumberFormatException e) {
                                    Log.e("ThingSpeak", "Error parsing latitude or longitude: " + e.getMessage());


                            }
                        }


                    }

                    if (!feeds.isEmpty()) {
                        ThingSpeakData.Feed lastFeed = feeds.get(feeds.size() - 1);
                        String lastLatitude = lastFeed.getField1();
                        String lastLongitude = lastFeed.getField2();

                        if (lastLatitude != null && !lastLatitude.isEmpty() && lastLongitude != null && !lastLongitude.isEmpty()) {
                            try {
                                double lastLat = Double.parseDouble(lastLatitude);
                                double lastLon = Double.parseDouble(lastLongitude);
                                LatLng lastMarkerPosition = new LatLng(lastLat, lastLon);
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastMarkerPosition, 10f));
                            } catch (NumberFormatException e) {
                                Log.e("ThingSpeak", "Error parsing last latitude or longitude: " + e.getMessage());
                            }
                        }
                    }

                } else {
                    Log.e("ThingSpeak", "Error: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<ThingSpeakData> call, Throwable t) {
                t.printStackTrace();

            }
        });


    }



}