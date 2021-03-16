package com.gyamfimartins.soccerleague.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyamfimartins.soccerleague.R;
import com.gyamfimartins.soccerleague.model.SoccerResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OpponentListAdapter extends RecyclerView.Adapter<OpponentListAdapter.OpponentViewHolder>  {
    private List<SoccerResult> resultList;

    @NonNull
    @Override
    public OpponentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerow_results, parent, false);
        return new OpponentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OpponentViewHolder holder, int position) {
        SoccerResult currentResult = resultList.get(position);
      holder.textView_teamname.setText(currentResult.getHomeTeamName());
      holder.textView_wins.setText(String.valueOf(currentResult.getWins()));
        holder.textView_draws.setText(String.valueOf(currentResult.getDraws()));
        holder.textView_losses.setText(String.valueOf(currentResult.getLosses()));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public void setResults(List<SoccerResult> resultList) {

        this.resultList = resultList;
        notifyDataSetChanged();
    }

    class OpponentViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_teamname,textView_wins,textView_losses,textView_draws,textView_percent;
        public OpponentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_teamname =  itemView.findViewById(R.id.textView_teamname);
            textView_wins =  itemView.findViewById(R.id.textView_wins);
            textView_losses =  itemView.findViewById(R.id.textView_losses);
            textView_draws =  itemView.findViewById(R.id.textView_draws);
            textView_percent =  itemView.findViewById(R.id.textView_percent);
        }
    }




}
