package com.example.imdbsearch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imdbsearch.R;
import com.example.imdbsearch.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.myViewHolder> {

    private List<Movie> movies;
    private MovieClickListener movieClickListener;

    public MoviesAdapter(List<Movie> movies, MovieClickListener movieClickListener) {
        this.movies = movies;
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_movie_layout, parent, false);
        return new myViewHolder(view, movieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Movie movie = movies.get(position);

//        Picasso.get()
//                .load(movie.getSmall_Poster())
//                .fit()
//                .into(holder.image);

        holder.movieName.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        public ImageView image;

        @BindView(R.id.movieName)
        public TextView movieName;

        public myViewHolder(@NonNull View itemView, MovieClickListener movieClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                movieClickListener.onClick(getAdapterPosition());
            });
        }
    }
}
