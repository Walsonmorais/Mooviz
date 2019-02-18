package com.buka.mooviz;

import com.buka.mooviz.models.Movie;

import java.util.ArrayList;

public class Page {

    private String totalPages;
    private String totalResults;
    private ArrayList<Movie> results;


    public Page(String totalPages, String totalResults, ArrayList<Movie> results) {
        this.totalPages = totalPages;
        this.totalResults = totalResults;
        this.results = results;
    }



    public String getTotalPages() {
        return totalPages;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }


}
