package com.example.imdbsearch.repositories;


import com.example.imdbsearch.model.Movie;
import com.example.imdbsearch.model.myRetrofit;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchRepository {

    private static SearchRepository instance;


    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;


    public static SearchRepository getInstance() {
        if (instance == null)
            instance = new SearchRepository();
        return instance;
    }

    public void init() {
        retrofit = myRetrofit.getInstance();
        retrofitInterface = myRetrofit.getInterface();
    }


    public void searchMovie(String title, final SearchCallBack searchCallBack) {
        Call<List<Movie>> call = retrofitInterface.searchMovie(title);

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    searchCallBack.onSuccess(response.body());
                } else {
                    try {
                        searchCallBack.onFail(response.errorBody().string());
                    } catch (IOException e) {
                        searchCallBack.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                searchCallBack.onFail(t.toString());
            }
        });
    }


    public interface SearchCallBack {
        void onSuccess(List<Movie> movies);

        void onFail(String str);
    }
}
