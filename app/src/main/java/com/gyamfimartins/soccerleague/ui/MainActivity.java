package com.gyamfimartins.soccerleague.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.gyamfimartins.soccerleague.R;
import com.gyamfimartins.soccerleague.adapter.TeamListAdapter;
import com.gyamfimartins.soccerleague.model.HomeTeamResult;
import com.gyamfimartins.soccerleague.viewmodel.SoccerResultViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SoccerResultViewModel soccerResultViewModel;
    private TeamListAdapter teamListAdapter;
    private RecyclerView rvteams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvteams = findViewById(R.id.rvteams);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvteams.setHasFixedSize(true);
        rvteams.setLayoutManager(linearLayoutManager);

        soccerResultViewModel = new ViewModelProvider(this).get(SoccerResultViewModel.class);
        teamListAdapter = new TeamListAdapter();

        teamListAdapter.setOnItemClickListener(new TeamListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HomeTeamResult homeTeamResult) {
                Intent intent = new Intent(MainActivity.this, ViewDetailsActivity.class);
                intent.putExtra("HomeTeam", homeTeamResult.getHomeTeamName());
                startActivity(intent);
            }
        });


        getTeams();
    }

    private void getTeams() {
        soccerResultViewModel.getAllresult().observe(this, new Observer<List<HomeTeamResult>>() {
            @Override
            public void onChanged(List<HomeTeamResult> homeTeamResults) {
                teamListAdapter.setResults(homeTeamResults);
                rvteams.setAdapter(teamListAdapter);
            }
        });
    }


}