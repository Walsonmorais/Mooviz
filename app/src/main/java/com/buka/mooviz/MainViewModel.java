package com.buka.mooviz;

import android.os.AsyncTask;
import android.view.View;

import com.buka.mooviz.api.TmdbApi;
import com.buka.mooviz.models.Movie;

import java.io.IOException;
import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList <Movie>> popularMovieLiveData  = new MutableLiveData<>();
    private  TmdbApi api;

    public MainViewModel(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(TmdbApi.class);
    }

    public void fetchPopularMovies (){
        PopularMoviesRequestTask popularMoviesRequestTask = new PopularMoviesRequestTask();
        popularMoviesRequestTask.execute();

    }
    public LiveData <ArrayList<Movie>> getPopularMoviesLiveData (){
        return  popularMovieLiveData;

    }

    class PopularMoviesRequestTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {


            try {
                Response<Page> response = api.getPopularMovies().execute();

                if (response.isSuccessful()) {
                    Page page = response.body();
                    return page.getResults();

                } else {
                    return null;
                }
            } catch (IOException exception) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            popularMovieLiveData.setValue(movies);
        }
    }
}
