package com.gyamfimartins.soccerleague.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gyamfimartins.soccerleague.R;
import com.gyamfimartins.soccerleague.adapter.OpponentListAdapter;
import com.gyamfimartins.soccerleague.model.SoccerResult;
import com.gyamfimartins.soccerleague.viewmodel.SoccerResultViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewDetailsActivity extends AppCompatActivity {
    private SoccerResultViewModel soccerResultViewModel;
    private OpponentListAdapter opponentListAdapter;
    private RecyclerView rvopponent;
    private  String teamid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        String HomeTeam = getIntent().getStringExtra("HomeTeam");
         teamid = getIntent().getStringExtra("teamid");
        getSupportActionBar().setTitle(HomeTeam);

        rvopponent = findViewById(R.id.rvopponent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvopponent.setHasFixedSize(true);
        rvopponent.setLayoutManager(linearLayoutManager);

        soccerResultViewModel = new ViewModelProvider(this).get(SoccerResultViewModel.class);
        opponentListAdapter = new OpponentListAdapter();


    }





}