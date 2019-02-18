package com.buka.mooviz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.buka.mooviz.models.Movie;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter <MoviesAdapter.MovieViewHolder> {
    private ArrayList<Movie> movies = new ArrayList<>();

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
        movieViewHolder.titleTextView.setText(movie.getTitle());



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(ArrayList<Movie> movies) {

        if (movies != null){
            this.movies = movies;
            notifyDataSetChanged();
        }
    }
    interface  OnItemClickedListener{
        void onItemClicked(Movie movie);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView titleTextView;
        private TextView dateTextView;



        public  MovieViewHolder(View movieView){
            super(movieView);
            titleTextView = movieView.findViewById(R.id.textview_title);


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
