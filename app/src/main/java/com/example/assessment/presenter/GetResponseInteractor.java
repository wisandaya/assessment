package com.example.assessment.presenter;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.assessment.Global.CustomTrust;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class GetResponseInteractor implements GetResponse.Interactor {
    private Context context;
    public GetResponseInteractor(Context context) {
        this.context = context;
    }

    @Override
    public void getResponse(Context context, final OnlineDataListener listener) {

        CustomTrust customTrust = new CustomTrust(context);
        OkHttpClient client2 = customTrust.getClient();
        AndroidNetworking.get("https://run.mocky.io/v3/3bd4b91a-07b8-4d90-adf0-9949659ac6d8")
                .setPriority(Priority.HIGH)
                .setOkHttpClient(client2)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {

                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String sample = response.getString("response");
                            listener.onGetResponseSuccess(sample);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        listener.onGetResponseError(String.valueOf(anError));
                    }
                });

    }
    }
