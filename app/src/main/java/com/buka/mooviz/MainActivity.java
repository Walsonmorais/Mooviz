package com.buka.mooviz;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.buka.mooviz.api.TmdbApi;
import com.buka.mooviz.models.Movie;

import java.io.IOException;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.OnItemClickedListener{


    private ProgressBar loadingProgressBar;
    private MoviesAdapter moviesAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingProgressBar = findViewById(R.id.progressbar_loading);
        RecyclerView moviesRecyclerView = findViewById(R.id.recycleview_movies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        moviesRecyclerView.setLayoutManager(linearLayoutManager);

        moviesAdapter = new MoviesAdapter(this);
        moviesRecyclerView.setAdapter(moviesAdapter);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getPopularMoviesLiveData().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                moviesAdapter.setMovies(movies);
                loadingProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        loadingProgressBar.setVisibility(View.VISIBLE);
        mainViewModel.fetchPopularMovies();

    }

    @Override
    public void onItemClicked(Movie movie) {
        Intent intent = new Intent(this,MovieDatailsActivity.class);
        intent.putExtra(MovieDatailsActivity.EXTRA_MOVIE_TITLE,movie.getTitle());
        intent.putExtra(MovieDatailsActivity.EXTRA_MOVIE_REALESE_DATE, movie.getReleaseDate());
        intent.putExtra(MovieDatailsActivity.EXTRA_MOVIE_OVERVIEW, movie.getOverview());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId()== R.id.action_refresh){
            mainViewModel.fetchPopularMovies();
            loadingProgressBar.setVisibility(View.VISIBLE);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }

    }
}
