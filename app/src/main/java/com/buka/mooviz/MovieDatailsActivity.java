package com.buka.mooviz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MovieDatailsActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_TITLE = "extra_movie_title";
    public  static final  String EXTRA_MOVIE_REALESE_DATE = "extra_movie_realese_date";
    public  static final  String EXTRA_MOVIE_OVERVIEW="extra_movie_overview";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_datails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titleTextView = findViewById(R.id.texview_title2);
        TextView realiseDateTextView = findViewById(R.id.release_date);
        TextView overviewTextView = findViewById(R.id.overview);


        Intent intent = getIntent();
        if (intent != null){
            String movieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE);
            String realeseDate = intent.getStringExtra(EXTRA_MOVIE_REALESE_DATE);
            String overview = intent.getStringExtra(EXTRA_MOVIE_OVERVIEW);


            titleTextView.setText(movieTitle);
            realiseDateTextView.setText(realeseDate);
            overviewTextView.setText(overview);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);

        }
        return super.onOptionsItemSelected(item);
    }
}



