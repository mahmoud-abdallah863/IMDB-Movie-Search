package com.example.imdbsearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    @SerializedName("imdbID")
    private String imdb_ID;

    private String title;

    @SerializedName("image")
    private String small_Poster;
    private String poster;
    private String video_Link;

    private String rating;
    private Double imdb_rating;
    private String time;
    private String date_Published;

    private List<String> genres = new ArrayList<>();
    private List<String> directors = new ArrayList<>();
    private List<String> writers = new ArrayList<>();
    private List<String> stars = new ArrayList<>();

    public Movie(String title) {
        this.title = title;
    }

    private String summary;
    private String storyLine;

    public String getImdb_ID() {
        return imdb_ID;
    }

    public void setImdb_ID(String imdb_ID) {
        this.imdb_ID = imdb_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmall_Poster() {
        return small_Poster;
    }

    public void setSmall_Poster(String small_Poster) {
        this.small_Poster = small_Poster;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getVideo_Link() {
        return video_Link;
    }

    public void setVideo_Link(String video_Link) {
        this.video_Link = video_Link;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Double getImdb_rating() {
        return imdb_rating;
    }

    public void setImdb_rating(Double imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate_Published() {
        return date_Published;
    }

    public void setDate_Published(String date_Published) {
        this.date_Published = date_Published;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getStars() {
        return stars;
    }

    public void setStars(List<String> stars) {
        this.stars = stars;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }
}
