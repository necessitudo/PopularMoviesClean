package ru.gdgkazan.popularmoviesclean.screen.details;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.popularmoviesclean.domain.usecase.MoviesUseCase;


public class MovieDetailsPresenter {


    private final MovieDetailsView mMoviesDetailView;
    private final MoviesUseCase mMoviesUseCase;
    private final LifecycleHandler mLifecycleHandler;

    public MovieDetailsPresenter(@NonNull MovieDetailsView moviesDetailView, @NonNull MoviesUseCase moviesUseCase,
                           @NonNull LifecycleHandler lifecycleHandler) {
        mMoviesDetailView = moviesDetailView;
        mMoviesUseCase = moviesUseCase;
        mLifecycleHandler = lifecycleHandler;
    }

   public void init() {
        /*mMoviesUseCase.popularMovies()
                .doOnSubscribe(mMoviesView::showLoadingIndicator)
                .doAfterTerminate(mMoviesView::hideLoadingIndicator)
                .compose(mLifecycleHandler.load(R.id.movies_request_id))
                .subscribe(mMoviesView::showMovies, throwable -> mMoviesView.showError());*/
    }



}
