package com.paul_alarcon.flick.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterpath;
    String title;
    String overview;
    String vote;
    String backdroppath;

    public Movie(JSONObject jsonObject) throws JSONException {

        posterpath = jsonObject.getString("poster_path");
        title= jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        vote = jsonObject.getString("vote_average");
        backdroppath = jsonObject.getString("backdrop_path");

    }

    //Create a RecyclerView.Adapter and ViewHolder to render the item
    //Bind the adapter to the data source to populate the RecyclerView

    public static List<Movie> fromJSsonArray(JSONArray movieJsonArray) throws JSONException{
        List<Movie> movies = new ArrayList<>();
        for(int i=0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }

        return movies;
    }

    public String getPosterpath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", posterpath);
    }

    public String getTitle() {
        return title;
    }

    public String getVote() {
        return vote;
    }

    public String getBackdroppath() {
        return String.format("https://image.tmdb.org/t/p/w342%s",backdroppath);
    }

    public String getOverview() {
        return overview;
    }
}
