package com.example.imdbsearch.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imdbsearch.model.Movie;
import com.example.imdbsearch.repositories.SearchRepository;

import java.net.MulticastSocket;
import java.util.List;

import static com.example.imdbsearch.repositories.SearchRepository.getInstance;

public class SearchViewModel extends ViewModel {

    private SearchRepository sRepo;

    private MutableLiveData<List<Movie>> moviesList = new MutableLiveData<>();

    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();


    public void init() {
        sRepo = getInstance();
        sRepo.init();
    }


    public void searchMovie(String title) {
        if (sRepo == null) init();
        isUpdating.postValue(true);
        sRepo.searchMovie(title, new SearchRepository.SearchCallBack() {
            @Override
            public void onSuccess(List<Movie> movies) {
                moviesList.postValue(movies);
                isUpdating.postValue(false);
                error.postValue(null);
            }

            @Override
            public void onFail(String str) {
                moviesList.postValue(null);
                isUpdating.postValue(false);
                error.postValue(str);
            }
        });
    }


    public LiveData<List<Movie>> getMovies() {
        return moviesList;
    }

    public LiveData<Boolean> isUpdating() {
        return isUpdating;
    }

    public LiveData<String> getError() {
        return error;
    }
}
