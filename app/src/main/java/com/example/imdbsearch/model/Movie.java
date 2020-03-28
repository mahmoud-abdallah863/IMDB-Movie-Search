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

    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> directors = new ArrayList<>();
    private ArrayList<String> writers = new ArrayList<>();
    private ArrayList<String> stars = new ArrayList<>();

    private String summary;
    private String storyLine;

    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, String small_Poster, String poster, String video_Link,
                 String rating, Double imdb_rating, String time, String date_Published, ArrayList<String> genres,
                 ArrayList<String> directors, ArrayList<String> writers, ArrayList<String> stars, String summary,
                 String storyLine) {
        this.title = title;
        this.small_Poster = small_Poster;
        this.poster = poster;
        this.video_Link = video_Link;
        this.rating = rating;
        this.imdb_rating = imdb_rating;
        this.time = time;
        this.date_Published = date_Published;
        this.genres = genres;
        this.directors = directors;
        this.writers = writers;
        this.stars = stars;
        this.summary = summary;
        this.storyLine = storyLine;
    }

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

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getWriters() {
        return writers;
    }

    public void setWriters(ArrayList<String> writers) {
        this.writers = writers;
    }

    public ArrayList<String> getStars() {
        return stars;
    }

    public void setStars(ArrayList<String> stars) {
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
