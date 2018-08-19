package com.delta.suraj.psychlone.Game;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.delta.suraj.psychlone.Connection.ConnectingFragment;
import com.delta.suraj.psychlone.Connection.ConnectionActivity;
import com.delta.suraj.psychlone.Connection.ConnectionUtilities;
import com.delta.suraj.psychlone.Connection.Endpoint;
import com.delta.suraj.psychlone.R;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.Strategy;

import java.util.List;
import java.util.Set;

import butterknife.BindView;

public class GameActivity extends ConnectionActivity {

    private static final Strategy STRATEGY = Strategy.P2P_STAR;
    private static final String SERVICE_ID = "com.delta.suraj.psychlone";

    public enum State {
        UNKNOWN,
        DISCOVERING,
        ADVERTISING,
        CONNECTED
    }
    private State mState = State.UNKNOWN;

    ConnectingFragment connectingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        connectingFragment = (ConnectingFragment) getSupportFragmentManager()
                .findFragmentById(R.id.connecting_fragment);

        if (ConnectionUtilities.getPlayerType().equals("HOST"))
            hostGame();
        else
            discoverGame();
    }

    private void hostGame () {
        startAdvertising();
    }

    private void discoverGame () {
        startDiscovering();
    }

    @Override
    protected String getName() {
        return ConnectionUtilities.getPlayerName();
    }

    @Override
    protected String getServiceId() {
        return SERVICE_ID;
    }

    @Override
    protected Strategy getStrategy() {
        return STRATEGY;
    }

    @Override
    public List<Endpoint> getDiscoveredEndpoints() {
        return super.getDiscoveredEndpoints();
    }

    @Override
    public List<Endpoint> getConnectedEndpoints() {
        return super.getConnectedEndpoints();
    }

    @Override
    protected void onEndpointDiscovered(Endpoint endpoint) {
        super.onEndpointDiscovered(endpoint);
        connectingFragment.refreshDiscoveredList();
    }

    @Override
    protected void onEndpointDisconnected(Endpoint endpoint) {
        super.onEndpointDisconnected(endpoint);
        connectingFragment.refreshDiscoveredList();
    }

    @Override
    protected void onEndpointConnected(Endpoint endpoint) {
        super.onEndpointConnected(endpoint);
        assert connectingFragment != null;
        connectingFragment.refreshConnectedList();
    }

    @Override
    protected void onConnectionInitiated(Endpoint endpoint, ConnectionInfo connectionInfo) {
        super.onConnectionInitiated(endpoint, connectionInfo);
        acceptConnection(endpoint);
    }
}
