package com.example.imdbsearch.repositories;



import com.example.imdbsearch.model.Movie;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {


    @GET("search/{title}")
    Call<List<Movie>> searchMovie(@Path("title") String title);

    @GET("movie/{imdbID}")
    Call<Movie> getMovie(@Path("imdbID") String imdbID);
}
