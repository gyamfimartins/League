package com.gyamfimartins.soccerleague.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyamfimartins.soccerleague.R;
import com.gyamfimartins.soccerleague.model.SoccerResult;
import com.gyamfimartins.soccerleague.model.TeamResult;

import java.util.ArrayList;
import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {
    private List<TeamResult> resultList;
    private OnItemClickListener listener;
    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerow, parent, false);
        return new TeamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        TeamResult currentResult = resultList.get(position);
      holder.textView_teamname.setText(currentResult.getHomeTeamName());
       List<SoccerResult> soccerResults = currentResult.getSoccerResults();
       int win =0;
       int loss =0;
       int draw = 0;
        double totalmatches= soccerResults.size();
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
         double percentage = Math.round((win/totalmatches) * 100);
         holder.textView_wins.setText(String.valueOf(win));
        holder.textView_losses.setText(String.valueOf(loss));
        holder.textView_draws.setText(String.valueOf(draw));
        holder.textView_percent.setText(String.valueOf(percentage));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public void setResults(List<TeamResult> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    class TeamViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_teamname,textView_wins,textView_losses,textView_draws,textView_percent;
        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_teamname =  itemView.findViewById(R.id.textView_teamname);
            textView_wins =  itemView.findViewById(R.id.textView_wins);
            textView_losses =  itemView.findViewById(R.id.textView_losses);
            textView_draws =  itemView.findViewById(R.id.textView_draws);
            textView_percent =  itemView.findViewById(R.id.textView_percent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(resultList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TeamResult teamResult);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
