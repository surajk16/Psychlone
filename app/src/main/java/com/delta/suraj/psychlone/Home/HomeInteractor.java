package com.delta.suraj.psychlone.Home;

import android.text.TextUtils;

import com.delta.suraj.psychlone.Connection.ConnectionUtilities;

public class HomeInteractor {

    interface OnFinishListener {
        void onNameError();
        void onSuccess();
    }

    public void hostGame (final String name, final OnFinishListener listener) {

        if (TextUtils.isEmpty(name)) {
            listener.onNameError();
            return;
        }

        ConnectionUtilities.setPlayerName(name.trim());
        ConnectionUtilities.setPlayerType("HOST");

        listener.onSuccess();
    }

    public void playGame (final String name, final OnFinishListener listener) {

        if (TextUtils.isEmpty(name)) {
            listener.onNameError();
            return;
        }

        ConnectionUtilities.setPlayerName(name.trim());
        ConnectionUtilities.setPlayerType("PLAYER");

        listener.onSuccess();
    }
}
