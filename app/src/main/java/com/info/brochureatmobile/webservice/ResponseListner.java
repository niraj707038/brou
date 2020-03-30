package com.info.brochureatmobile.webservice;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseListner {

    private OnResponseInterface onResponseInterface;
    private String message;

    public ResponseListner(OnResponseInterface onResponseInterface) {
        this.onResponseInterface = onResponseInterface;
    }

    /*public ResponseListner(NewsEvent newsEvent) {

    }*/

    public void getResponse(Call call) {
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                message = response.message();
                Log.d("TAG", "onResponse: " + message);
                if (response.code() == 200)
                    onResponseInterface.onApiResponse(response.body());
                else onResponseInterface.onApiFailure(message);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                message = t.getMessage();
                Log.d("TAG", "onFailure: " + message);
                onResponseInterface.onApiFailure(message);
            }
        });
    }
}
