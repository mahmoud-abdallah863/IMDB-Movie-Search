package com.example.imdbsearch.repositories;


import android.util.Log;

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

    private static final String TAG = "shit";
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


    public void getMovie(String imdb_ID, final GetMovieCallBack getMovieCallBack) {
        Call<Movie> call = retrofitInterface.getMovie(imdb_ID);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    getMovieCallBack.onSuccess(response.body());
                    Log.d(TAG, "success");
                } else {
                    try {
                        getMovieCallBack.onFail(response.errorBody().string());
                        Log.d(TAG, "fail" + response.errorBody().string());
                    } catch (IOException e) {
                        getMovieCallBack.onFail(e.getMessage());
                        Log.d(TAG, "fail > catch : " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                getMovieCallBack.onFail(t.toString());
                Log.d(TAG, "failer : " + t.getMessage());
            }
        });
    }


    public interface SearchCallBack {
        void onSuccess(List<Movie> movies);

        void onFail(String str);
    }

    public interface GetMovieCallBack {
        void onSuccess(Movie movie);

        void onFail(String str);
    }
}
