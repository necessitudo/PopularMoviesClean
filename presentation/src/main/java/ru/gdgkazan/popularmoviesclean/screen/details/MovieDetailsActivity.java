package ru.gdgkazan.popularmoviesclean.screen.details;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.arturvasilov.rxloader.RxUtils;
import ru.gdgkazan.popularmoviesclean.R;
import ru.gdgkazan.popularmoviesclean.data.repository.RepositoryProvider;
import ru.gdgkazan.popularmoviesclean.domain.model.Movie;
import ru.gdgkazan.popularmoviesclean.domain.model.Review;
import ru.gdgkazan.popularmoviesclean.domain.model.Video;
import ru.gdgkazan.popularmoviesclean.domain.usecase.MoviesDetailUseCase;
import ru.gdgkazan.popularmoviesclean.utils.Images;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView {

    private static final String MAXIMUM_RATING = "10";

    public static final String IMAGE = "image";
    public static final String EXTRA_MOVIE = "extraMovie";

    private  MoviesDetailAdapter mAdapter;

    private Movie movie;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.image)
    ImageView mImage;

/*   @BindView(R.id.title)
    TextView mTitleTextView;

    @BindView(R.id.overview)
    TextView mOverviewTextView;

    @BindView(R.id.rating)
    TextView mRatingTextView;*/

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static void navigate(@NonNull AppCompatActivity activity, @NonNull View transitionImage,
                                @NonNull Movie movie) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindowForAnimation();
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar), IMAGE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        showMovie(movie);

        MoviesDetailUseCase moviesDetailUseCase = new MoviesDetailUseCase(RepositoryProvider.getMoviesRepository(), RxUtils.async(),RxUtils.async(), movie.getmId());
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        MovieDetailsPresenter presenter = new MovieDetailsPresenter(this, moviesDetailUseCase,lifecycleHandler);
        presenter.init();


        /**
         * TODO : task
         *
         * Load movie trailers and reviews and display them
         *
         * 1) See http://docs.themoviedb.apiary.io/#reference/movies/movieidtranslations/get?console=1
         * http://docs.themoviedb.apiary.io/#reference/movies/movieidtranslations/get?console=1
         * for API documentation
         *
         * 2) Add requests to repository
         *
         * 3) Add new UseCase for loading Movie details
         *
         * 4) Use MVP pattern for the Movie details screen
         *
         * 5) Execute requests in parallel and show loading progress until both of them are finished
         *
         * 6) Handle lifecycle changes any way you like
         *
         * 7) Save trailers and videos to Realm and use cached version when error occurred
         */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareWindowForAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void showMovie(@NonNull Movie movie) {
        String title = getString(R.string.movie_details);
        mCollapsingToolbar.setTitle(title);
        mCollapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));

        Images.loadMovie(mImage, movie, Images.WIDTH_780);

        /*String year = movie.getReleasedDate().substring(0, 4);
        mTitleTextView.setText(getString(R.string.movie_title, movie.getTitle(), year));
        mOverviewTextView.setText(movie.getOverview());

        String average = String.valueOf(movie.getVoteAverage());
        average = average.length() > 3 ? average.substring(0, 3) : average;
        average = average.length() == 3 && average.charAt(2) == '0' ? average.substring(0, 1) : average;
        mRatingTextView.setText(getString(R.string.rating, average, MAXIMUM_RATING));*/
    }


    @Override
    public void showTrailersReviews(@NonNull List<Review> reviews, @NonNull List<Video> videos) {

        mAdapter = new MoviesDetailAdapter(reviews,videos, movie);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showError() {

    }

    @Override
    public void showSuccess(Boolean success) {

    }



}
