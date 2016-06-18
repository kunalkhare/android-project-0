package com.example.android.myappportfolio;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.android.service.MovieService;
import com.example.android.domain.ImageAdapter;
import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<String> mGridData;
    private GridView mGridView;
    public  static ProgressBar mProgressBar;
    public  static ImageAdapter imageAdapter;
    public static Context movieActivityContext;

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

        MovieService service = new MovieService();
        service.execute("popular");
        mProgressBar.setVisibility(View.VISIBLE);

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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
