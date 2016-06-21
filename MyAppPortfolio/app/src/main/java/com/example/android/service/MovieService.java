package com.example.android.service;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.android.domain.PopularMovie;
import com.example.android.myappportfolio.MovieActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kunal on 6/17/2016.
 */

public class MovieService extends AsyncTask<String, Void, List<PopularMovie>> {

    private final String LOG_TAG = MovieService.class.getSimpleName();
    private final String MOVIEDB_KEY="f9674990e0f2f54b901c6a6b290731f5";
    private final String temp_url ="http://api.themoviedb.org/3/movie/popular?api_key=f9674990e0f2f54b901c6a6b290731f5";
    private final String MOVIE_URL ="http://api.themoviedb.org/3/movie";
    private final String KEY_PARAM ="api_key";
    private HttpURLConnection urlConn = null;
    private BufferedReader reader = null;

    public List<String> posterUrls = new ArrayList<String>();
    private final String POSTER_URL = "http://image.tmdb.org/t/p/w185";


    @Override
    protected List<PopularMovie> doInBackground(String... params) {
        String userSortChoice = params[0];
        List<PopularMovie> movieList = new ArrayList<PopularMovie>();

        try{
            Uri buildUrl = Uri.withAppendedPath(Uri.parse(MOVIE_URL),userSortChoice).buildUpon()
                    .appendQueryParameter(KEY_PARAM,MOVIEDB_KEY).build();
            URL url = new URL(buildUrl.toString());
            Log.v(LOG_TAG, "MOVIE URL: " +buildUrl.toString());

            //open connection
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.connect();

            InputStream inputStream = urlConn.getInputStream();
            if(inputStream==null)
            {
                Log.v(LOG_TAG, "No data returned");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder movieDbJsonString = new StringBuilder();

            while((line = reader.readLine())!=null){
                movieDbJsonString.append(line + "\n");
            }

            movieList = getMovieDetailFromJson(movieDbJsonString.toString());


        }catch (Exception e){

        }
        return movieList;
    }

    @Override
    protected void onPostExecute(List<PopularMovie> popularMovies) {
        String posterPath;
        List<String> posterUrls = new ArrayList<String>();

        if(popularMovies!=null)
        {
            for(PopularMovie movie:popularMovies){
                Log.v(LOG_TAG, "ADDING MOVIE######: " +movie.getTitle());
                posterPath = movie.getPosterUrl();
                Uri buildUrl = Uri.withAppendedPath(Uri.parse(POSTER_URL),posterPath);
                Log.v(LOG_TAG, "POSTER_URL ### "+buildUrl.toString() );
                movie.setPosterUrl(buildUrl.toString());
                posterUrls.add(buildUrl.toString());
            }

        }

        //Hide progressbar
        MovieActivity movieActivity = new MovieActivity();
        movieActivity.imageAdapter.setGridData(posterUrls,popularMovies);
        movieActivity.mProgressBar.setVisibility(View.GONE);
    }

    private List<PopularMovie> getMovieDetailFromJson(String movieDbJsonString){
        final String MOVIE_LIST = "results";
        final String MOVIE_TITLE = "original_title";
        final String MOVIE_POSTERURL = "poster_path";
        final String MOVIE_PLOTSYN ="overview";
        final String MOVIE_RATING ="vote_average";
        final String MOVIE_RELEASEDATE ="release_date";
        List<PopularMovie> movieList = new ArrayList<PopularMovie>();

        try {
            JSONObject movieJson = new JSONObject(movieDbJsonString);
            JSONArray movieArray = movieJson.getJSONArray(MOVIE_LIST);

            for (int i=0 ; i<movieArray.length();i++)
            {
                PopularMovie movie = new PopularMovie();
                JSONObject movieObject = movieArray.getJSONObject(i);
                movie.setTitle(movieObject.getString(MOVIE_TITLE));
                movie.setPosterUrl(movieObject.getString(MOVIE_POSTERURL));
                movie.setPlotSynopsis(movieObject.getString(MOVIE_PLOTSYN));
                movie.setUserRating(movieObject.getString(MOVIE_RATING));
                movie.setReleaseDate(movieObject.getString(MOVIE_RELEASEDATE));
                movieList.add(movie);
            }

        }catch (JSONException e)
        {
            Log.e(LOG_TAG, e.getMessage());
        }

        return movieList;
    }
}
