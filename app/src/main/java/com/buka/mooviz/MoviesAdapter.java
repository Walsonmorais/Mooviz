package com.buka.mooviz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.buka.mooviz.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter <MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();

    private OnItemClickedListener listener;
    public  MoviesAdapter (OnItemClickedListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View movieView = inflater.inflate(R.layout.item_movie,parent,false);


        MovieViewHolder viewHolder = new MovieViewHolder(movieView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        Movie movie = movies.get(position);

        Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath()).into(movieViewHolder.posterImageView);


        movieViewHolder.movieTitleTextView.setText(movie.getTitle());
        movieViewHolder.descriptionTextView.setText(movie.getOverview());
        movieViewHolder.realeaseDateTextView.setText(movie.getReleaseDate());



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {

        if (movies != null){
            this.movies = movies;
            notifyDataSetChanged();
        }
    }
    interface  OnItemClickedListener{
        void onItemClicked(Movie movie);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView movieTitleTextView;
        private TextView descriptionTextView;
        private TextView realeaseDateTextView;
        private ImageView posterImageView;



        public  MovieViewHolder(View movieView){
            super(movieView);

            posterImageView = movieView.findViewById(R.id.imageView_poster);
            movieTitleTextView = movieView.findViewById(R.id.textView_movie_title);
            descriptionTextView = movieView.findViewById(R.id.textView_description);
            realeaseDateTextView = movieView.findViewById(R.id.textView_realese_date);


            movieView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedItemPosition = getAdapterPosition();
            Movie clickedMovie = movies.get(clickedItemPosition);
            listener.onItemClicked(clickedMovie);


        }
    }
}
