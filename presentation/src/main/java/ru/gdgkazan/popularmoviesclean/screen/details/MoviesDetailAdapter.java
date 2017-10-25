package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.popularmoviesclean.R;
import ru.gdgkazan.popularmoviesclean.domain.model.Movie;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;

public class MoviesDetailAdapter extends RecyclerView.Adapter<MoviesDetailAdapter.MovieDetailsHolder> {

    private static final String MAXIMUM_RATING = "10";

    private List<Review> mReviews;

    private String title;
    private String overview;
    private String rating;

    public  class MovieDetailsHolder extends RecyclerView.ViewHolder {

        public TextView review;

        public TextView mTitleTextView;

        public TextView mOverviewTextView;

        public TextView mRatingTextView;


        public MovieDetailsHolder(View itemView, int viewType) {
            super(itemView);

            switch (viewType){
                case R.id.title_id:
                    mTitleTextView = (TextView) itemView.findViewById(R.id.title);
                    mOverviewTextView = (TextView) itemView.findViewById(R.id.overview);
                    mRatingTextView = (TextView) itemView.findViewById(R.id.rating);
                    break;
                case R.id.review_id:
                    review = (TextView) itemView.findViewById(R.id.review_text);
                    break;
            }

        }
    }

    public MoviesDetailAdapter(List<Review> reviews, Movie movie){

        mReviews = reviews;

        String year = movie.getReleasedDate().substring(0, 4);
        title = R.string.movie_title+" "+movie.getTitle()+" "+year;
        overview = movie.getOverview();

        String average = String.valueOf(movie.getVoteAverage());
        average = average.length() > 3 ? average.substring(0, 3) : average;
        average = average.length() == 3 && average.charAt(2) == '0' ? average.substring(0, 1) : average;
        rating = R.string.rating+" "+average+" "+MAXIMUM_RATING;

    }

    @Override
    public MovieDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;

        switch (viewType) {
            case R.id.title_id:
                 itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.content_main_information, parent, false);
                break;
            case R.id.review_id:
                 itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.content_reviews, parent, false);
                break;
        }

        return new MovieDetailsHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(MovieDetailsHolder holder, int position) {

        int type = getItemViewType(position);

        switch (type){
            case R.id.title_id:
                holder.mTitleTextView.setText(title);
                holder.mOverviewTextView.setText(overview);
                holder.mRatingTextView.setText(rating);
                break;
            case  R.id.review_id:
                holder.review.setText(mReviews.get(position-1).getAuthor());
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return  R.id.title_id;
        }

        else {
            return R.id.review_id;
        }
    }


    @Override
    public int getItemCount() {
        return mReviews.size()+1;
    }



}
