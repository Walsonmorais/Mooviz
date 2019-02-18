package com.buka.mooviz.models;

import com.google.gson.annotations.SerializedName;

public class Movie {


    private int id;
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    private double popularity;
    @SerializedName("vote_count")
    private double voteCount;
    @SerializedName("release_date")
    private String releaseDate;
    private  String overview;


    public Movie(int id, String title, String posterPath, double popularity, double voteCount, String releaseDate, String overview) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getVoteCount() {
        return voteCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() { return overview; }
}

