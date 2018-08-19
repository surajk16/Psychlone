package com.delta.suraj.psychlone.Home;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.delta.suraj.psychlone.Connection.ConnectionActivity;
import com.delta.suraj.psychlone.Game.GameActivity;
import com.delta.suraj.psychlone.R;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.name_edit_text)
    TextInputEditText name;

    @BindView(R.id.host_button)
    MaterialButton hostButton;

    @BindView(R.id.play_button)
    MaterialButton playButton;

    @BindView(R.id.root_layout)
    RelativeLayout rootLayout;

    @BindDrawable(R.drawable.ic_error_outline_red)
    Drawable error;

    private HomePresenter presenter;
    Dialog progressDialog;
    private static final String[] REQUIRED_PERMISSIONS =
            new String[] {
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            };

    private static final int REQUEST_CODE_REQUIRED_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initiateProgressDialog();

        error.setBounds(0, 0, error.getIntrinsicWidth(), error.getIntrinsicHeight());
        hostButton.setOnClickListener(v-> hostGame());
        playButton.setOnClickListener(v-> playGame());

        presenter = new HomePresenter(this, new HomeInteractor());

    }

    @Override
    public void setNameError() {
        name.setError("Please enter your name to continue", error);
    }

    @Override
    public void showProgress() { progressDialog.show(); }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void navigateToConnections() {
        startActivity(new Intent(this, GameActivity.class));
    }

    private void hostGame () {
        presenter.hostGame(name.getText().toString());
    }

    private void playGame () {
        presenter.playGame(name.getText().toString());
    }

    private void initiateProgressDialog () {
        progressDialog = new Dialog(this);
        progressDialog.setContentView(R.layout.loading_dialog);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!hasPermissions(this, REQUIRED_PERMISSIONS)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_REQUIRED_PERMISSIONS);
            }
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != REQUEST_CODE_REQUIRED_PERMISSIONS) {
            return;
        }

        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                Snackbar.make(rootLayout, "Permission denied. Please grant permission to continue.", Snackbar.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_REQUIRED_PERMISSIONS);
                }
            }
        }
    }
}
