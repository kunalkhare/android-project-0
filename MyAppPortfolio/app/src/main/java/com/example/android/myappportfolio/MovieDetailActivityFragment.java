package com.example.android.myappportfolio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.domain.PopularMovie;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieDetailActivityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieDetailActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailActivityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String LOG_TAG = MovieDetailActivityFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public MovieDetailActivityFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailActivityFragment newInstance(String param1, String param2) {
        MovieDetailActivityFragment fragment = new MovieDetailActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_detail_activity, container, false);
        Intent intent = getActivity().getIntent();
        View vw = rootView.findViewById(R.id.image_detail);

        if(intent!=null && intent.hasExtra("PopularMovie")){
            PopularMovie movieItem = (PopularMovie)intent.getSerializableExtra("PopularMovie");
            Log.v(LOG_TAG, "TITLE : "+ movieItem.getTitle());
            Log.v(LOG_TAG, "IMAGEURL : "+ movieItem.getPosterUrl());

            Picasso.with(getContext())
                    .load(movieItem.getPosterUrl()).into((ImageView)vw);
            ((TextView)rootView.findViewById(R.id.movie_detail_title)).setText(movieItem.getTitle());
            ((TextView)rootView.findViewById(R.id.movie_detail_year)).setText(movieItem.getReleaseDate().substring(0,4));
            ((TextView)rootView.findViewById(R.id.movie_detail_vote)).setText(movieItem.getUserRating()+"/10");
            ((TextView)rootView.findViewById(R.id.movie_detail_plot)).setText(movieItem.getPlotSynopsis());
        }

        return rootView;
    }
}
