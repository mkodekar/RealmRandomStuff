package com.example.rkodekar.randompractices.controller;

import android.content.Context;
import com.example.rkodekar.randompractices.model.Movies;
import io.realm.Realm;
import io.realm.RealmResults;

import java.util.ArrayList;

/**
 * Created by rkodekar on 9/28/16.
 */
public class RealmController extends WrappedAsyncTaskLoader<ArrayList<Movies>> {

    private static RealmController instance;
    private final Realm realm;
    String query;

    private ArrayList<Movies> movies = new ArrayList<>();
    /**
     * Constructor of <code>WrappedAsyncTaskLoader</code>
     *
     * @param context The {@link Context} to use.
     */
    public RealmController(Context context) {
        super(context);
        realm = Realm.getDefaultInstance();

    }

    public void queryString(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public void refresh() {
        realm.refresh();
    }

    public void clearAll() {

        realm.beginTransaction();
        realm.clear(Movies.class);
        realm.commitTransaction();
    }

    @Override
    public ArrayList<Movies> loadInBackground() {
        RealmResults<Movies> realmMovies;
        if (getQuery() != null) {
            realmMovies = getMovieQuery();
            for (Movies movie : realmMovies) {
                movies.add(movie);
            }
        } else {
            realmMovies = getMoviews();
            for (Movies movie : realmMovies) {
                movies.add(movie);
            }
        }
        return movies;
    }

    public RealmResults<Movies> getMoviews() {
        return realm.where(Movies.class).findAll();
    }

    public RealmResults<Movies> getMovieQuery() {
        switch (getQuery()) {
            case "director":
                return realm.where(Movies.class)
                        .contains("director", getQuery())
                        .findAll();
            case "year":
                return realm.where(Movies.class)
                        .contains("year", getQuery())
                        .findAll();
            case "actor":
                return realm.where(Movies.class)
                        .contains("year", getQuery())
                        .findAll();
        }
        return null;
    }
}
