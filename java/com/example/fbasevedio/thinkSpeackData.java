package com.example.fbasevedio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class thinkSpeackData extends AppCompatActivity {
//    private TextView textEntryId;
//    private TextView textField1;
//    private TextView textField2;
//    private TextView textCreatedAt;


    private ListView listView;

    private ArrayAdapter<String> adapter;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_think_speack_data);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://thingspeak.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        textEntryId = findViewById(R.id.textEntryId);
//        textField1 = findViewById(R.id.textField1);
//        textField2 = findViewById(R.id.textField2);
//        textCreatedAt = findViewById(R.id.textCreatedAt);



        ThinkSpeakApi thinkSpeakApi = retrofit.create(ThinkSpeakApi.class);

        Call<ThingSpeakData> call = thinkSpeakApi.getChannelData();
        call.enqueue(new Callback<ThingSpeakData>() {
            @Override
            public void onResponse(Call<ThingSpeakData> call, Response<ThingSpeakData> response) {
                if (response.isSuccessful()){
                    ThingSpeakData data = response.body();
                    List<ThingSpeakData.Feed> feeds =data.getFeeds();

                    List<String> dataList = new ArrayList<>();

//                    if (!feeds.isEmpty()) {
//                        ThingSpeakData.Feed feed = feeds.get(0);  // Assuming you want data from the first entry
//                        textEntryId.setText("Entry ID: " + feed.getEntry_id());
//                        textField1.setText("Field1: " + feed.getField1());
//                        textField2.setText("Field2: " + feed.getField2());
//                        textCreatedAt.setText("Created At: " + feed.getCreate_at());
//                    }

                    for (ThingSpeakData.Feed feed : feeds) {

                        if(feed.getField1() != null && feed.getField2() != null) {
                            String entry = "Entry ID: " + feed.getEntry_id() +
                                    "\nField1: " + feed.getField1() +
                                    "\nField2: " + feed.getField2() +
                                    "\nCreated At: " + feed.getCreate_at() + "\n";

                            dataList.add(entry);
                        }
                    }
                    adapter.clear();
                    adapter.addAll(dataList);



                }else {
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
