package ru.gdgkazan.popularmoviesclean.domain.usecase;

import java.util.List;

import ru.gdgkazan.popularmoviesclean.domain.MoviesRepository;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import rx.Observable;

/**
 * Created by olegdubrovin on 17/10/17.
 */

public class MoviesDetailUseCase {

    private final MoviesRepository mRepository;
    private final Observable.Transformer<List<Video>, List<Video>> mAsyncTransformerVideo;
    private final Observable.Transformer<List<Review>, List<Review>> mAsyncTransformerReview;
    private  final int mMovieId;

    public MoviesDetailUseCase(MoviesRepository repository,
                               Observable.Transformer<List<Video>, List<Video>> asyncTransformerVideo,
                               Observable.Transformer<List<Review>, List<Review>> asyncTransformerReview,
                               int movieId){

        mRepository = repository;
        mAsyncTransformerVideo = asyncTransformerVideo;
        mAsyncTransformerReview = asyncTransformerReview;
        mMovieId = movieId;
    }

    public Observable<List<Review>> getReviews(){
        return  mRepository.getReviews(mMovieId).compose(mAsyncTransformerReview);
    }

    public Observable<List<Video>> getVideos(){
        return  mRepository.getVideos(mMovieId).compose(mAsyncTransformerVideo);
    }
}
