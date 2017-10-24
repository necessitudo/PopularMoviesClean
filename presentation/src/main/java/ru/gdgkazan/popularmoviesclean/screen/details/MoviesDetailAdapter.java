package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.R;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;

public class MoviesDetailAdapter extends RecyclerView.Adapter {

    private List<Review> mReviews;



    public static class MovieDetailsHolder extends RecyclerView.ViewHolder {

        public TextView review_text;

        public MovieDetailsHolder(View itemView) {
            super(itemView);
            review_text = (TextView) itemView.findViewById(R.id.review_text);
        }
    }

    public MoviesDetailAdapter(List<Review> reviews){
        mReviews = reviews;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_reviews, parent, false);
        return new MovieDetailsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Review review = mReviews.get(position);

        MovieDetailsHolder mHolder = (MovieDetailsHolder) holder;
        mHolder.review_text.setText(review.getAuthor());
    }


    @Override
    public int getItemCount() {
        return mReviews.size();
    }



}
