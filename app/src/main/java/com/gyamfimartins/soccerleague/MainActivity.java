package com.gyamfimartins.soccerleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.gyamfimartins.soccerleague.adapter.TeamListAdapter;
import com.gyamfimartins.soccerleague.model.SoccerResult;
import com.gyamfimartins.soccerleague.ui.ViewDetailsActivity;
import com.gyamfimartins.soccerleague.util.SimpleDividerItemDecoration;
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
        rvteams.addItemDecoration(new SimpleDividerItemDecoration(this));

        soccerResultViewModel = new ViewModelProvider(this).get(SoccerResultViewModel.class);
        teamListAdapter = new TeamListAdapter();

        teamListAdapter.setOnItemClickListener(new TeamListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SoccerResult soccerResult) {
                Intent intent = new Intent(MainActivity.this, ViewDetailsActivity.class);
                intent.putExtra("HomeTeam", soccerResult.getHomeTeamName());
                intent.putExtra("teamid", soccerResult.getHomeTeamId());
                startActivity(intent);
            }
        });




        getTeams();
    }

  private void getTeams(){
      soccerResultViewModel.getAllresult().observe(this, new Observer<List<SoccerResult>>() {
          @Override
          public void onChanged(List<SoccerResult> soccerResults) {
              teamListAdapter.setResults(soccerResults);
              rvteams.setAdapter(teamListAdapter);
              System.out.println(soccerResults.toString());
          }
      });
  }


}