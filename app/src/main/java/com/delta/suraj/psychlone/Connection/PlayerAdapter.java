package com.delta.suraj.psychlone.Connection;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delta.suraj.psychlone.R;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Endpoint> playersList;

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName;

        public PlayerViewHolder (View view) {
            super(view);
            playerName = view.findViewById(R.id.player_name_row);
        }
    }

    public PlayerAdapter (List<Endpoint> playersList) {
        this.playersList = playersList;
    }

    @NonNull
    @Override
    public PlayerAdapter.PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.player_list_row, viewGroup, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.PlayerViewHolder playerViewHolder, int i) {
        Endpoint endpoint = playersList.get(i);
        playerViewHolder.playerName.setText(endpoint.getName());
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }
}
