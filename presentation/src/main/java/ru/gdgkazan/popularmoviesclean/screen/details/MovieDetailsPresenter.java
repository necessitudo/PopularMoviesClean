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
                       return true;
                   }

               }).subscribe(mMoviesDetailView::onSuccess, throwable->mMoviesDetailView.showError());

       /** TODO : task
        * нужно добавить индикатор прогресса загрузки, в onSuccess его скрывать если пришло true
        * нужно наконец запилить новую вьюху вместо content_movie_details, потому что вставить
        * в неее Recycler нельзя!
        */

       /*mMoviesDetailUseCase.getReviews()
               .compose(mLifecycleHandler.load(R.id.reviews_request_id))
               .subscribe(mMoviesDetailView::showReviews, throwable->mMoviesDetailView.showError());*/


   }




}
