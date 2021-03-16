package com.gyamfimartins.soccerleague.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gyamfimartins.soccerleague.model.AwayTeamResult;
import com.gyamfimartins.soccerleague.model.SoccerResult;
import com.gyamfimartins.soccerleague.model.HomeTeamResult;
import com.gyamfimartins.soccerleague.network.RetrofitRequest;
import com.gyamfimartins.soccerleague.network.SoccerApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoccerResultRepository {


    public SoccerResultRepository() {

    }

    public LiveData<List<HomeTeamResult>> getsoccerresults() {
        final MutableLiveData<List<HomeTeamResult>> data = new MutableLiveData<>();
        final List<HomeTeamResult> hometeamList = new ArrayList<>();

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
                Map<String, List<SoccerResult>> map = new HashMap<>();
                for (SoccerResult result : resultList) {
                    String name = result.getHomeTeamName();
                    if (map.containsKey(name)) {
                        List<SoccerResult> list = map.get(name);
                        list.add(result);

                    } else {
                        List<SoccerResult> list = new ArrayList<SoccerResult>();
                        list.add(result);
                        map.put(name, list);
                    }

                }
                for (Map.Entry mapElement : map.entrySet()) {
                    HomeTeamResult homeTeamResult = new HomeTeamResult();
                    homeTeamResult.setHomeTeamName((String) mapElement.getKey());
                    homeTeamResult.setSoccerResults((List<SoccerResult>) mapElement.getValue());
                    hometeamList.add(homeTeamResult);

                }


                Collections.sort(hometeamList, new Comparator<HomeTeamResult>() {
                    public int compare(HomeTeamResult s1, HomeTeamResult s2) {
                        return String.valueOf(s1.getHomeTeamName()).compareTo(s2.getHomeTeamName());
                    }
                });

                data.setValue(hometeamList);

            }

            @Override
            public void onFailure(Call<List<SoccerResult>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });


        return data;
    }


    public LiveData<List<AwayTeamResult>> getAwayresults(List<SoccerResult> awayteamlist) {
        final MutableLiveData<List<AwayTeamResult>> data = new MutableLiveData<>();
        final List<AwayTeamResult> awayTeamResults = new ArrayList<>();

        Map<String, List<SoccerResult>> map = new HashMap<>();
        for (SoccerResult result : awayteamlist) {
            String name = result.getAwayTeamName();
            if (map.containsKey(name)) {
                List<SoccerResult> list = map.get(name);
                list.add(result);

            } else {
                List<SoccerResult> list = new ArrayList<SoccerResult>();
                list.add(result);
                map.put(name, list);
            }

        }

        for (Map.Entry mapElement : map.entrySet()) {
            AwayTeamResult awayTeamResult = new AwayTeamResult();
            awayTeamResult.setAwayTeamName((String) mapElement.getKey());
            awayTeamResult.setSoccerResults((List<SoccerResult>) mapElement.getValue());
            awayTeamResults.add(awayTeamResult);
        }
        data.setValue(awayTeamResults);

        return data;
    }

}
