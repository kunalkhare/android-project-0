package com.example.android.myappportfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.domain.ImageAdapter;
import com.example.android.service.MovieService;

import java.util.List;

public class MovieActivity extends AppCompatActivity {

/*    public static MovieActivity movieActivity = new MovieActivity();
    public static GridView gridView ;
    public static ImageAdapter imageAdapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_container, new MovieActivityFragment())
                    .commit();
        }

        MovieService service = new MovieService();
        service.execute("popular");
/*        gridView = (GridView) findViewById(R.id.gridView);
        String[] test = {};
        imageAdapter = new ImageAdapter(this,test);*/
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this,MovieService.getPosterUrls().toArray(new String[MovieService.getPosterUrls().size()])));

       // ImageView imageView = (ImageView) findViewById(R.id.gridView);
    }
}
