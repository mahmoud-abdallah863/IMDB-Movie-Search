package com.example.imdbsearch.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.imdbsearch.R;
import com.example.imdbsearch.adapter.MovieClickListener;
import com.example.imdbsearch.adapter.MoviesAdapter;
import com.example.imdbsearch.model.Movie;
import com.example.imdbsearch.viewmodels.SearchViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ResultFragment extends Fragment implements MovieClickListener {

    private static final String TAG = "shit";
    @BindView(R.id.movieName_editText)
    public EditText movieName_editText;

    @BindView(R.id.search_btn)
    public Button search_btn;

    @BindView(R.id.recycleView)
    public RecyclerView recycleView;

    @BindView(R.id.progressBarLayout)
    public LinearLayout progressBarLayout;

    /**
     * Data
     */
    private SearchViewModel searchViewModel;

    private MoviesAdapter adapter;
    public static List<Movie> movies;

    private static String movieTitle;
    private boolean same = true;


    public ResultFragment(String movieName) {
        if (movieTitle != null) {
            same = movieTitle.equalsIgnoreCase(movieName);
        } else same = false;
        movieTitle = movieName;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setMovieName();

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.init();

        if (same) setRecycleView();
        else searchMovie();
        observes();
    }


    private void searchMovie() {
        searchViewModel.searchMovie(movieTitle);
    }

    private void observes() {
        searchViewModel.getMovies().observe(getViewLifecycleOwner(), _movies -> {
            if (_movies != null) {
                movies = _movies;
                setRecycleView();
            }
        });

        searchViewModel.isUpdating().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) showProgressBar();
            else hideProgressBar();
        });

        searchViewModel.getError().observe(getViewLifecycleOwner(), err -> {
            if (err != null && !err.isEmpty()) {
                // show the error
            }
        });
    }

    private void setMovieName() {
        movieName_editText.setText(movieTitle);
    }

    private void setRecycleView() {
        adapter = new MoviesAdapter(movies, this);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setHasFixedSize(true);
    }

    @Override
    public void onClick(int position) {
        Log.d("shit", "imdb-id : " + movies.get(position).getImdb_ID());
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ShowMovie(movies.get(position).getImdb_ID()))
                .addToBackStack("show")
                .commit();
    }

    @OnClick(R.id.back_btn)
    void backButtonClicked() {
        getActivity().getSupportFragmentManager().popBackStack();
    }


    @OnClick(R.id.search_btn)
    void searchButtonClicked() {
        String str = movieName_editText.getText().toString();
        if (!str.isEmpty()) {
            movieTitle = str;
            searchMovie();
        }

    }


    private void showProgressBar() {
        progressBarLayout.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBarLayout.setVisibility(View.GONE);
    }
}
