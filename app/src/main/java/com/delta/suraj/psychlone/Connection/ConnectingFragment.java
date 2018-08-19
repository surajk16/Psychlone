package com.delta.suraj.psychlone.Connection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.delta.suraj.psychlone.Game.GameActivity;
import com.delta.suraj.psychlone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnectingFragment extends Fragment {

    @BindView(R.id.player_name)
    TextView playerName;

    @BindView(R.id.player_type)
    TextView playerType;

    @BindView(R.id.players_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.start_game)
    MaterialButton startButton;

    private List<Endpoint> playersList;
    private PlayerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View retView = inflater.inflate(R.layout.fragment_connecting, container);
        ButterKnife.bind(this, retView);

        playerName.setText(ConnectionUtilities.getPlayerName());
        playerType.setText(ConnectionUtilities.getPlayerType());

        playersList = new ArrayList<>();
        playersList.add(new Endpoint("com.delta.suraj.psychlone", "testName"));

        if (ConnectionUtilities.getPlayerType().equals("HOST"))
            hostConnection();
        else
            playerConnection();

        mAdapter = new PlayerAdapter(playersList);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        return retView;
    }

    private void hostConnection () {
        startButton.setVisibility(View.VISIBLE);
        playersList = ((GameActivity) Objects.requireNonNull(getActivity())).getConnectedEndpoints();
    }

    private void playerConnection () {
        startButton.setVisibility(View.GONE);
        playersList = ((GameActivity) Objects.requireNonNull(getActivity())).getDiscoveredEndpoints();
    }

    public void refreshConnectedList () {
        playersList = ((GameActivity) Objects.requireNonNull(getActivity())).getConnectedEndpoints();
        mAdapter = new PlayerAdapter(playersList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void refreshDiscoveredList () {
        playersList = ((GameActivity) Objects.requireNonNull(getActivity())).getDiscoveredEndpoints();
        mAdapter = new PlayerAdapter(playersList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
