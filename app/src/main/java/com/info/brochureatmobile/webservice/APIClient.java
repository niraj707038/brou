package com.info.brochureatmobile.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.info.brochureatmobile.util.Constant;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static APIClient sApiClient;
    private ApiInterface mApiInterface;

    private APIClient() {


        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(100000, TimeUnit.MILLISECONDS)
                .readTimeout(100000, TimeUnit.MILLISECONDS)
                .connectTimeout(100000, TimeUnit.MILLISECONDS).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        mApiInterface = retrofit.create(ApiInterface.class);
    }


    public static APIClient getInstance() {
        if (sApiClient == null)
            sApiClient = new APIClient();
        return sApiClient;
    }

    public ApiInterface getApiInterface() {
        return mApiInterface;
    }

}
