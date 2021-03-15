package com.gyamfimartins.soccerleague.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyamfimartins.soccerleague.R;
import com.gyamfimartins.soccerleague.model.SoccerResult;

import java.util.ArrayList;
import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {
    private List<SoccerResult> resultList = new ArrayList<>();
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
        SoccerResult currentResult = resultList.get(position);

        if (position == 0) {
            holder.tvteam_name.setText(currentResult.getHomeTeamName());
        } else {
            SoccerResult previousResult = resultList.get(position - 1);
            String previousName = previousResult.getHomeTeamName();
            if (previousName.equals(currentResult.getHomeTeamName())) {
                holder.tvteam_name.setVisibility(View.GONE);
            } else {
                holder.tvteam_name.setVisibility(View.VISIBLE);
                holder.tvteam_name.setText(currentResult.getHomeTeamName());
            }
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setResults(List<SoccerResult> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    class TeamViewHolder extends RecyclerView.ViewHolder {
        private TextView tvteam_name;
        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvteam_name = itemView.findViewById(R.id.tvteam_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SoccerResult soccerResult);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
