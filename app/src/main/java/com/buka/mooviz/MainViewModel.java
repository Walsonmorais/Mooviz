package com.buka.mooviz;

import android.app.Application;
import android.os.AsyncTask;
import android.view.View;

import com.buka.mooviz.Utilities.AppExecutors;
import com.buka.mooviz.api.TmdbApi;
import com.buka.mooviz.database.DatabaseUtils;
import com.buka.mooviz.database.MovieDao;
import com.buka.mooviz.database.MovieDatabase;
import com.buka.mooviz.models.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel {
    private MovieDao movieDao;
    private  TmdbApi api;
    private MutableLiveData<List<Movie>> popularMovieLiveData = new MutableLiveData<>();


    public MainViewModel(Application application) {
        super(application);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(TmdbApi.class);
        MovieDatabase movieDatabase = Room.databaseBuilder(application.getApplicationContext(), MovieDatabase.class, DatabaseUtils.DATABASE_NAME).build();
        movieDao = movieDatabase.getMovieDao();



    }

    public void fetchPopularMovies (){
        AppExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                popularMovieLiveData.postValue(movieDao.getAll());
            }
        });
        AppExecutors.getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Response<Page> response = api.getPopularMovies().execute();

                    if (response.isSuccessful()) {
                        Page page = response.body();

                        final ArrayList<Movie> movies = page.getResults();
                        AppExecutors.getDiskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                movieDao.deleteAll();
                                movieDao.insertAll(movies);

                            }
                        });
                        popularMovieLiveData.postValue(movies);

                    } else {
                    }
                } catch (IOException exception) {
                }

            }
        });

    }

    public LiveData<List<Movie>> getPopularMoviesLiveData() {
        return popularMovieLiveData;

    }


}
