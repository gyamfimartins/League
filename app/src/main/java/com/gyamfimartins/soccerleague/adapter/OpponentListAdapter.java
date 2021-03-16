package com.gyamfimartins.soccerleague.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyamfimartins.soccerleague.R;
import com.gyamfimartins.soccerleague.model.AwayTeamResult;
import com.gyamfimartins.soccerleague.model.SoccerResult;

import java.util.List;

public class OpponentListAdapter extends RecyclerView.Adapter<OpponentListAdapter.OpponentViewHolder>  {
    private List<AwayTeamResult> resultList;

    @NonNull
    @Override
    public OpponentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerow_results, parent, false);
        return new OpponentViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull OpponentViewHolder holder, int position) {
        AwayTeamResult currentResult = resultList.get(position);
        String currentname = currentResult.getAwayTeamName();
       holder.textView_teamname.setText(currentname);
        List<SoccerResult> soccerResults = currentResult.getSoccerResults();
        int win =0;
        int loss =0;
        int draw = 0;
        int totalmatches= soccerResults.size();
        for(int i = 0; i< soccerResults.size(); i++){
            int awayscore= soccerResults.get(i).getAwayScore();
            int homescore = soccerResults.get(i).getHomeScore();
            if(homescore > awayscore){
                win++;
            }
            else if(homescore < awayscore){
                loss++;
            }else{
                draw++;
            }
        }

        holder.textView_wins.setText(String.valueOf(win));
        holder.textView_losses.setText(String.valueOf(loss));
        holder.textView_draws.setText(String.valueOf(draw));
        holder.textView_total.setText(String.valueOf(totalmatches));

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public void setResults(List<AwayTeamResult> resultList) {

        this.resultList = resultList;
        notifyDataSetChanged();
    }

    class OpponentViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_teamname,textView_wins,textView_losses,textView_draws,textView_total;
        public OpponentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_teamname =  itemView.findViewById(R.id.textView_teamname);
            textView_wins =  itemView.findViewById(R.id.textView_wins);
            textView_losses =  itemView.findViewById(R.id.textView_losses);
            textView_draws =  itemView.findViewById(R.id.textView_draws);
            textView_total =  itemView.findViewById(R.id.textView_total);
        }
    }




}
