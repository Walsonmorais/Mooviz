package com.buka.mooviz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.buka.mooviz.models.Movie;

public class MovieDatailsActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_ID = "extra_movie_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_datails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView titleTextView = findViewById(R.id.texview_title2);
        final TextView realiseDateTextView = findViewById(R.id.release_date);
        final TextView overviewTextView = findViewById(R.id.overview);

        MovieDetailsViewModel movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);


        Intent intent = getIntent();
        if (intent != null){
            int id = intent.getIntExtra(EXTRA_MOVIE_ID, 0);
            movieDetailsViewModel.getMovieById(id);

        }
        movieDetailsViewModel.getMovieLiveData().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                titleTextView.setText(movie.getTitle());
                realiseDateTextView.setText(movie.getReleaseDate());
                overviewTextView.setText(movie.getOverview());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);

        }
        return super.onOptionsItemSelected(item);
    }
}



