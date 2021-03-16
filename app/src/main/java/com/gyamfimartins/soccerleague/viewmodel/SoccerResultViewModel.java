package com.gyamfimartins.soccerleague.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gyamfimartins.soccerleague.model.AwayTeamResult;
import com.gyamfimartins.soccerleague.model.HomeTeamResult;
import com.gyamfimartins.soccerleague.model.SoccerResult;
import com.gyamfimartins.soccerleague.repository.SoccerResultRepository;

import java.util.List;


public class SoccerResultViewModel extends AndroidViewModel {
    private SoccerResultRepository soccerResultRepository;

    public SoccerResultViewModel(@NonNull Application application) {
        super(application);
        soccerResultRepository = new SoccerResultRepository();
    }

    public LiveData<List<HomeTeamResult>> getAllresult() {
        return soccerResultRepository.getsoccerresults();
    }

    public LiveData<List<AwayTeamResult>> getAllawayresult(List<SoccerResult> soccerResults) {
        return soccerResultRepository.getAwayresults(soccerResults);
    }

}
