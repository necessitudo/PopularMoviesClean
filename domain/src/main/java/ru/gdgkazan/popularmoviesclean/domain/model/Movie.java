package ru.gdgkazan.popularmoviesclean.domain.model;

import java.io.Serializable;

/**
 * @author Artur Vasilov
 */
public class Movie implements Serializable {

    private String mPosterPath;
    private String mOverview;
    private String mTitle;
    private String mReleasedDate;
    private double mVoteAverage;
    private int    mId;

    public Movie() {
    }

    public int getmId() {
        return mId;
    }

    public Movie(String posterPath, String overview,
                 String title, String releasedDate, double voteAverage, int id) {
        mPosterPath = posterPath;
        mOverview = overview;
        mTitle = title;
        mReleasedDate = releasedDate;

        mVoteAverage = voteAverage;
        mId          = id;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReleasedDate() {
        return mReleasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        mReleasedDate = releasedDate;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }
}
