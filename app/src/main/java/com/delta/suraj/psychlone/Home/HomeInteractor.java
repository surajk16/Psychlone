package com.delta.suraj.psychlone.Home;

import android.text.TextUtils;

import com.delta.suraj.psychlone.GameUtilities;

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

        GameUtilities.setPlayerName(name);
        GameUtilities.setPlayerType("HOST");

        listener.onSuccess();
    }

    public void playGame (final String name, final OnFinishListener listener) {

        if (TextUtils.isEmpty(name)) {
            listener.onNameError();
            return;
        }

        GameUtilities.setPlayerName(name);
        GameUtilities.setPlayerType("PLAYER");

        listener.onSuccess();
    }
}
