package com.buka.mooviz;

import android.app.Application;
import android.os.AsyncTask;

import com.buka.mooviz.Utilities.AppExecutors;
import com.buka.mooviz.database.DatabaseUtils;
import com.buka.mooviz.database.MovieDao;
import com.buka.mooviz.database.MovieDatabase;
import com.buka.mooviz.models.Movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

public class MovieDetailsViewModel extends AndroidViewModel {
    private MovieDao movieDao;
    private MutableLiveData<Movie> movieLiveData = new MutableLiveData<>();

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        MovieDatabase movieDatabase = Room.databaseBuilder(application.getApplicationContext(), MovieDatabase.class, DatabaseUtils.DATABASE_NAME).build();

        movieDao = movieDatabase.getMovieDao();
    }

    public void getMovieById(final int id) {
        AppExecutors.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Movie movie = movieDao.getMovieById(id);
                movieLiveData.postValue(movie);
            }
        });
    }

    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }
}
