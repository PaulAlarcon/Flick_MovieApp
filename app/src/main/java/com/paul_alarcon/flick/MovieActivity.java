package com.paul_alarcon.flick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.paul_alarcon.flick.adapters.MoviesAdapter;
import com.paul_alarcon.flick.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MovieActivity extends AppCompatActivity {

    final public static String MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        RecyclerView Rvmovies = findViewById(R.id.Rvmovies);
        movies = new ArrayList<>();
        final MoviesAdapter adapter = new MoviesAdapter(this, movies);
        Rvmovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Rvmovies.setAdapter(adapter);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIE_URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray moviesJsonArray = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSsonArray(moviesJsonArray));
                    adapter.notifyDataSetChanged();
                    Log.d("hey8", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
                    }
                }

        );





    }
}
