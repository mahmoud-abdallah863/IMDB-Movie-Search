package com.example.imdbsearch.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imdbsearch.R;
import com.example.imdbsearch.adapter.MovieClickListener;
import com.example.imdbsearch.adapter.MoviesAdapter;
import com.example.imdbsearch.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultFragment extends Fragment implements MovieClickListener {

    @BindView(R.id.searchMovieName)
    public TextView searchMovieName;

    @BindView(R.id.recycleView)
    public RecyclerView recycleView;

    /**
     * Data
     */
    private MoviesAdapter adapter;

    private String movieName;
    private ArrayList<Movie> movies;


    public ResultFragment(String movieName) {
        this.movieName = movieName;
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

        makeMovies();
        initRecycleView();
    }

    private void setMovieName() {
        String str = "(" + this.movieName + ")";
        searchMovieName.setText(str);
    }

    private void initRecycleView() {
        adapter = new MoviesAdapter(movies, this);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setHasFixedSize(true);
    }

    @Override
    public void onClick(int position) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ShowMovie(movies.get(position).getTitle()))
                .addToBackStack("show")
                .commit();
    }


    private void makeMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Iron man"));
        movies.add(new Movie("Iron man2"));
        movies.add(new Movie("Iron man3"));
        movies.add(new Movie("The hulk"));
        movies.add(new Movie("The Imitation Game"));
        this.movies = movies;
    }
}
