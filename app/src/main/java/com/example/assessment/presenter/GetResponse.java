package com.example.assessment.presenter;

import android.content.Context;

public interface GetResponse {
    interface view{
        void showSuccessDialog(String message);

        void showErrorDialog(String message);
    }

    interface Presenter{
        void getResponse();
    }

    interface Interactor{
        interface OnlineDataListener {
            void onGetResponseSuccess(String message);

            void onGetResponseError(String message);
        }

        void getResponse(Context context, OnlineDataListener listener);
    }
}
