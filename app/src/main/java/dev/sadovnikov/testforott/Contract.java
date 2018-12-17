package dev.sadovnikov.testforott;

public interface Contract {

    interface Repository {

    }

    interface Presenter {

    }

    interface View {
        void showTours();

        void showLoading();

        void hideLoading();

        void showFlightsDialog();
    }

}
