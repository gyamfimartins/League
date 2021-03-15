package com.gyamfimartins.soccerleague.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.gyamfimartins.soccerleague.model.SoccerResult;
import com.gyamfimartins.soccerleague.network.RetrofitRequest;
import com.gyamfimartins.soccerleague.network.SoccerApi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoccerResultRepository {


    public SoccerResultRepository() {

    }

    public LiveData<List<SoccerResult>> getsoccerresults() {
        final MutableLiveData<List<SoccerResult>> data = new MutableLiveData<>();

        SoccerApi soccerApi = RetrofitRequest.getRetrofitInstance().create(SoccerApi.class);
        Call<List<SoccerResult>> call = soccerApi.getSoccerResults();

        call.enqueue(new Callback<List<SoccerResult>>() {
            @Override
            public void onResponse(Call<List<SoccerResult>> call, Response<List<SoccerResult>> response) {
                if (!response.isSuccessful()) {
                  //  data.setValue(null);
                    return;
                }
                List<SoccerResult> resultList = response.body();


                Collections.sort(resultList, new Comparator<SoccerResult>() {
                    public int compare(SoccerResult s1, SoccerResult s2) {
                        return String.valueOf(s1.getHomeTeamName()).compareTo(s2.getHomeTeamName());
                    }
                });

                data.setValue(resultList);

            }

            @Override
            public void onFailure(Call<List<SoccerResult>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });



        return data;
    }





}
