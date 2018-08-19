package com.delta.suraj.psychlone.Home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.delta.suraj.psychlone.Connection.ConnectionActivity;
import com.delta.suraj.psychlone.R;

import butterknife.BindView;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.name)
    private TextInputEditText name;

    @BindView(R.id.host_button)
    private MaterialButton hostButton;

    @BindView(R.id.play_button)
    private MaterialButton playButton;

    @BindView(R.id.progress)
    private ProgressBar progressBar;

    private HomePresenter presenter;

    Drawable error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        error = getResources().getDrawable(R.drawable.ic_error_outline_red_a700_24dp);

        hostButton.setOnClickListener(v-> hostGame());
        playButton.setOnClickListener(v-> playGame());

        presenter = new HomePresenter(this, new HomeInteractor());
    }

    @Override
    public void setNameError() {
        name.setError("Please enter your name to continue", error);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void navigateToConnections() {
        startActivity(new Intent(this, ConnectionActivity.class));
        finish();
    }

    private void hostGame () {
        presenter.hostGame(name.getText().toString());
    }

    private void playGame () {
        presenter.playGame(name.getText().toString());
    }
}
