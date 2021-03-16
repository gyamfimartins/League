package com.gyamfimartins.soccerleague.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gyamfimartins.soccerleague.R;
import com.gyamfimartins.soccerleague.adapter.OpponentListAdapter;
import com.gyamfimartins.soccerleague.model.AwayTeamResult;
import com.gyamfimartins.soccerleague.model.SoccerResult;
import com.gyamfimartins.soccerleague.model.HomeTeamResult;
import com.gyamfimartins.soccerleague.viewmodel.SoccerResultViewModel;

import java.util.List;

public class ViewDetailsActivity extends AppCompatActivity {
    private SoccerResultViewModel soccerResultViewModel;
    private OpponentListAdapter opponentListAdapter;
    private RecyclerView rvopponent;
    private String HomeTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        HomeTeam = getIntent().getStringExtra("HomeTeam");
        getSupportActionBar().setTitle(HomeTeam);

        rvopponent = findViewById(R.id.rvopponent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvopponent.setHasFixedSize(true);
        rvopponent.setLayoutManager(linearLayoutManager);

        soccerResultViewModel = new ViewModelProvider(this).get(SoccerResultViewModel.class);
        opponentListAdapter = new OpponentListAdapter();

        getteams();
    }

    private void getteams() {
        soccerResultViewModel.getAllresult().observe(this, new Observer<List<HomeTeamResult>>() {
            @Override
            public void onChanged(List<HomeTeamResult> homeTeamResults) {
                List<SoccerResult> filteredList = null;
                for (HomeTeamResult result : homeTeamResults) {
                    String name = result.getHomeTeamName();
                    if (name.equals(HomeTeam)) {
                        filteredList = result.getSoccerResults();
                    }

                }
                getAwayteams(filteredList);

            }
        });
    }


    private void getAwayteams(List<SoccerResult> AwayList) {
        soccerResultViewModel.getAllawayresult(AwayList).observe(this, new Observer<List<AwayTeamResult>>() {
            @Override
            public void onChanged(List<AwayTeamResult> awayTeamResults) {
                opponentListAdapter.setResults(awayTeamResults);
                rvopponent.setAdapter(opponentListAdapter);
            }
        });
    }


}