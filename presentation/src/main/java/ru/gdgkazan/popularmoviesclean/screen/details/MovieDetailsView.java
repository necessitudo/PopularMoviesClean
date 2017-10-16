package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;

/**
 * Created by olegdubrovin on 16/10/17.
 */

public interface MovieDetailsView {

     void showTrailers(@NonNull List<Video> videos);

     void showReviews(@NonNull List<Review> reviews);

}
