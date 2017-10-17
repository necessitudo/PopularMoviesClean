package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.popularmoviesclean.domain.usecase.MoviesDetailUseCase;
import ru.gdgkazan.popularmoviesclean.R;


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
       mMoviesDetailUseCase.getReviews()
               .compose(mLifecycleHandler.load(R.id.reviews_request_id))
               .subscribe(mMoviesDetailView::showReviews, throwable->mMoviesDetailView.showError());

    }



}
