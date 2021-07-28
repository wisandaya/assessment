package com.example.assessment.presenter;

import android.content.Context;

import com.example.assessment.MainActivity;

public class GetResponsePresenter implements GetResponse.Presenter, GetResponse.Interactor.OnlineDataListener {

    private MainActivity view;
    private GetResponseInteractor interactor;
    private Context context;

    public GetResponsePresenter(MainActivity view, Context context) {
        this.view = view;
        this.interactor = new GetResponseInteractor(context);
        this.context = context;
    }

    @Override
    public void getResponse() {
        interactor.getResponse(context, this);
    }

    @Override
    public void onGetResponseSuccess(String message) {
        view.showSuccessDialog(message);

    }

    @Override
    public void onGetResponseError(String message) {
        view.showErrorDialog(message);
    }
}
