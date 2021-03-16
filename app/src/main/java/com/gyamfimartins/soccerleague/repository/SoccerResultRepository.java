package com.gyamfimartins.soccerleague.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gyamfimartins.soccerleague.model.SoccerResult;
import com.gyamfimartins.soccerleague.model.TeamResult;
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

    public LiveData<List<TeamResult>> getsoccerresults() {
        final MutableLiveData<List<TeamResult>> data = new MutableLiveData<>();
        final List<TeamResult> hometeamList = new ArrayList<>();

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
                    String name  = result.getHomeTeamName();
                    if(map.containsKey(name)){
                        List<SoccerResult> list = map.get(name);
                        list.add(result);

                    }else{
                        List<SoccerResult> list = new ArrayList<SoccerResult>();
                        list.add(result);
                        map.put(name, list);
                    }

                }
                for (Map.Entry mapElement : map.entrySet()) {
                    TeamResult teamResult = new TeamResult();
                    teamResult.setHomeTeamName((String)mapElement.getKey());
                    teamResult.setSoccerResults((List<SoccerResult>)mapElement.getValue());
                    hometeamList.add(teamResult);

                }




                Collections.sort(hometeamList, new Comparator<TeamResult>() {
                    public int compare(TeamResult s1, TeamResult s2) {
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


  private void getd(){

  }

}
