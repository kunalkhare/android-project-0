package com.example.android.myappportfolio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.service.MovieService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_activity);
/*        MovieService service = new MovieService();
        service.execute("popular");*/
    }

    public void clickGoUbt(View view) {
        Toast.makeText( getApplicationContext(), "This button will launch Go Ubiquitous", Toast.LENGTH_SHORT).show();
    }

    public void clickCapstone(View view) {
        Toast.makeText( getApplicationContext(), "This button will launch Capstone", Toast.LENGTH_SHORT).show();
    }

    public void clickMakeApp(View view) {
        Toast.makeText( getApplicationContext(), "This button will launch Make Your App", Toast.LENGTH_SHORT).show();
    }

    public void clickBuildIt(View view) {
        Toast.makeText( getApplicationContext(), "This button will launch Build It Bigger", Toast.LENGTH_SHORT).show();
    }

    public void clickStockHawk(View view) {
        Toast.makeText( getApplicationContext(), "This button will launch my Stock Hawk ", Toast.LENGTH_SHORT).show();
    }

    public void clickPmovies(View view) {
        //Toast.makeText( getApplicationContext(), "This button will launch my movies", Toast.LENGTH_SHORT).show();
        //launch movie activity
        Intent intent = new Intent(getApplicationContext(),MovieActivity.class);
        startActivity(intent);
    }

    }
