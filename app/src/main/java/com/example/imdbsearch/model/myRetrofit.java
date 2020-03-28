package com.example.imdbsearch.model;

import com.example.imdbsearch.repositories.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.imdbsearch.utils.Util.BASE_URL;

public class myRetrofit {

    private static Retrofit instance;
    private static OkHttpClient okHttpClient;
    private static RetrofitInterface retrofitInterface;

    public static Retrofit getInstance() {
        if (okHttpClient == null)
            initOkHttp();
        if (instance == null)
            initRetrofit();
        return instance;
    }

    public static RetrofitInterface getInterface() {
        if (retrofitInterface == null)
            instance = getInstance();
        return retrofitInterface;
    }

    private static void initRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        instance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        retrofitInterface = instance.create(RetrofitInterface.class);
    }

    private static void initOkHttp() {
        int REQUEST_TIMEOUT = 30;
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        okHttpClient = httpClient.build();
    }
}
