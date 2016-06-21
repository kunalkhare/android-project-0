package com.example.android.myappportfolio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.domain.PopularMovie;
import com.example.android.menu.MovieMenuSettingsActivity;
import com.example.android.service.MovieService;
import com.example.android.domain.ImageAdapter;
import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<PopularMovie> mGridData;
    private GridView mGridView;
    public  static ProgressBar mProgressBar;
    public  static ImageAdapter imageAdapter;
    public static Context movieActivityContext;
    private final String LOG_TAG = MovieActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mGridView = (GridView) findViewById(R.id.gridViewActivity);
        mProgressBar =(ProgressBar) findViewById(R.id.movie_progress_bar);

        movieActivityContext = this;
        mGridData = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, R.layout.fragment_movie_activity, mGridData);
        movieActivityContext = this;
        mGridView.setAdapter(imageAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(LOG_TAG, " position : " + position);
                Log.v(LOG_TAG, " id : " + id);
                PopularMovie movieItemSelected = imageAdapter.getItem(position);
                Log.v(LOG_TAG, " movieItemSelected : " + movieItemSelected.getTitle());
                Intent intent = new Intent(getApplicationContext(),MovieDetailActivity.class)
                        .putExtra("PopularMovie",  movieItemSelected);
                startActivity(intent);
                //Toast.makeText( getApplicationContext(), movieItemSelected.getPlotSynopsis(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sort_settings){
            updateMoviesOrder();
            mProgressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(this, MovieMenuSettingsActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateMoviesOrder(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selection = sharedPreferences.getString(getString(R.string.pref_sort_popular_label),
                getString(R.string.pref_sort_popular));

        MovieService service = new MovieService();
        service.execute(selection);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMoviesOrder();
    }
}
