package com.gyamfimartins.soccerleague.network;


import com.gyamfimartins.soccerleague.model.SoccerResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface SoccerApi {
    @GET("soccer_data.json")
    Call<List<SoccerResult>> getSoccerResults();
}


