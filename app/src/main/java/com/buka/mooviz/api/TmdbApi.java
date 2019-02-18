package com.buka.mooviz.api;

import com.buka.mooviz.Page;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TmdbApi {

    @GET("discover/movie?api_key=ef4fdd76966284c0b1e722ce0f194aac")
    Call<Page> getPopularMovies();
}
