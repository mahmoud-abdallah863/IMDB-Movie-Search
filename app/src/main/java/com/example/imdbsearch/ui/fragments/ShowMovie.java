package com.example.imdbsearch.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imdbsearch.R;
import com.example.imdbsearch.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShowMovie extends Fragment {

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


    /**
     * Data
     */
    private String imdb_ID;
    private Movie movie;

    public ShowMovie(String imdb_ID) {
        this.imdb_ID = imdb_ID;
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

        setData();
    }


    private void setData() {
        //dummy data
        movie = getMovie();

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

    private Movie getMovie() {
        return new Movie("Iron man", "smallPoster", "poster", "video_link", "rating", 8.0, "2h 1m", "2 May 2008",
                new ArrayList<String>(Arrays.asList("Action", "Sci-Fi")),
                new ArrayList<String>(Arrays.asList("director a", "director b")),
                new ArrayList<String>(Arrays.asList("writer a", "writer b")),
                new ArrayList<String>(Arrays.asList("robert jr.", "x")),
                "This is the summary of the film",
                "This is the story line of the entire film");
    }

    private String listToString(ArrayList<String> list) {
        StringBuffer str = new StringBuffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            str.append(list.get(i));
            if (i < size - 1)
                str.append(" | ");
        }
        return str.toString();
    }
}
