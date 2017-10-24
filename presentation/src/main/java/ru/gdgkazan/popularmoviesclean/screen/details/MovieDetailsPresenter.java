package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.function.Function;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import ru.gdgkazan.popularmoviesclean.domain.usecase.MoviesDetailUseCase;
import ru.gdgkazan.popularmoviesclean.R;
import rx.functions.Func2;


public class MovieDetailsPresenter {


    private final MovieDetailsView mMoviesDetailView;
    private final MoviesDetailUseCase mMoviesDetailUseCase;
    private final LifecycleHandler mLifecycleHandler;

    public MovieDetailsPresenter(@NonNull MovieDetailsView moviesDetailView, @NonNull MoviesDetailUseCase moviesDetailUseCase,
                           @NonNull LifecycleHandler lifecycleHandler) {
        mMoviesDetailView = moviesDetailView;
        mMoviesDetailUseCase = moviesDetailUseCase;
        mLifecycleHandler = lifecycleHandler;
    }

   public void init() {

       rx.Observable<List<Review>> reviews = mMoviesDetailUseCase.getReviews()
               .compose(mLifecycleHandler.load(R.id.reviews_request_id));

       rx.Observable<List<Video>> videos = mMoviesDetailUseCase.getVideos()
               .compose(mLifecycleHandler.load(R.id.videos_request_id));


       rx.Observable.zip(reviews, videos, new Func2<List<Review>, List<Video>, Boolean>() {
                   @Override
                   public Boolean call(List<Review> reviews, List<Video> videos) {
                       mMoviesDetailView.showReviews(reviews);
                       return true;
                   }

               }).subscribe(mMoviesDetailView::onSuccess, throwable->mMoviesDetailView.showError());





       /*mMoviesDetailUseCase.getReviews()
               .compose(mLifecycleHandler.load(R.id.reviews_request_id))
               .subscribe(mMoviesDetailView::showReviews, throwable->mMoviesDetailView.showError());*/


   }

    public void resolveDownload(List<Review> reviews, List<Video> videos) {



    }



}
