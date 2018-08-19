package com.delta.suraj.psychlone.Home;

public class HomePresenter implements HomeInteractor.OnFinishListener {

    private HomeView homeView;
    private HomeInteractor homeInteractor;

    HomePresenter (HomeView homeView, HomeInteractor homeInteractor) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    public void hostGame (String name) {
        if (homeView != null)
            homeView.showProgress();

        homeInteractor.hostGame(name, this);
    }

    public void playGame (String name) {
        if (homeView != null)
            homeView.showProgress();

        homeInteractor.playGame(name, this);
    }

    @Override
    public void onNameError() {
        if (homeView != null) {
            homeView.setNameError();
            homeView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (homeView != null)
            homeView.navigateToConnections();
    }
}
