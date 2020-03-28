package com.example.imdbsearch.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imdbsearch.R;
import com.example.imdbsearch.model.Movie;
import com.example.imdbsearch.viewmodels.SearchViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShowMovie extends Fragment {

    private static final String TAG = "shit";
    @BindView(R.id.image)
    public ImageView poster;

    @BindView(R.id.name)
    public TextView name_textView;

    @BindView(R.id.date)
    public TextView date_textView;

    @BindView(R.id.imdb_rate)
    public TextView imdb_rate;

    @BindView(R.id.time)
    public TextView time_textView;

    @BindView(R.id.summaryText)
    public TextView summaryText;

    @BindView(R.id.genreText)
    public TextView genreText;

    @BindView(R.id.directorsText)
    public TextView directorsText;

    @BindView(R.id.writersText)
    public TextView writersText;

    @BindView(R.id.starsText)
    public TextView starsText;

    @BindView(R.id.storyLineText)
    public TextView storyLineText;

    @BindView(R.id.progressBarLayout)
    public LinearLayout progressBarLayout;

    @BindView(R.id.dataLayout)
    public NestedScrollView dataLayout;


    /**
     * Data
     */
    private SearchViewModel searchViewModel;

    private static String imdb_ID;
    private static Movie movie;

    private boolean same = false;

    public ShowMovie(String _imdb_ID) {
        if (imdb_ID != null)
            same = imdb_ID.equalsIgnoreCase(_imdb_ID);
        imdb_ID = _imdb_ID;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_movie, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.init();

        if (same) {
            setData();
            hideProgressBar();
        } else searchViewModel.getMovie(imdb_ID);
        observers();
    }

    private void observers() {
        searchViewModel.getMovie().observe(getViewLifecycleOwner(), _movie -> {
            if (_movie != null) {
                movie = _movie;
                setData();
            }
        });

        searchViewModel.isUpdating().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) showProgressBar();
            else hideProgressBar();
        });

        searchViewModel.getError().observe(getViewLifecycleOwner(), err -> {
            if (err != null && !err.isEmpty())
                Toast.makeText(getContext(), "Something wet wrong!!\nPlease try again later...", Toast.LENGTH_LONG).show();
        });
    }


    private void setData() {
        Picasso.get()
                .load(movie.getPoster())
                .fit()
                .into(poster);

        name_textView.setText(movie.getTitle());
        date_textView.setText(movie.getDate_Published());
        String str = movie.getImdb_rating() + "/10";
        imdb_rate.setText(str);
        time_textView.setText(movie.getTime());
        summaryText.setText(movie.getSummary());
        genreText.setText(listToString(movie.getGenres()));
        directorsText.setText(listToString(movie.getDirectors()));
        writersText.setText(listToString(movie.getWriters()));
        starsText.setText(listToString(movie.getStars()));
        storyLineText.setText(movie.getStoryLine());
    }

    private String listToString(List<String> list) {
        StringBuilder str = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            str.append(list.get(i));
            if (i < size - 1)
                str.append(" | ");
        }
        return str.toString();
    }

    private void showProgressBar() {
        progressBarLayout.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBarLayout.setVisibility(View.GONE);
    }
}
