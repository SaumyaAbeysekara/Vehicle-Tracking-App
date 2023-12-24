package com.example.fbasevedio;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ThinkSpeakApi {
    @GET("channels/your chanel/feeds.json?results=1&api_key=your ApI key")
    Call<ThingSpeakData> getChannelData();
}
