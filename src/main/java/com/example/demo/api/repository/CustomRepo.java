package com.example.demo.api.repository;

import com.example.demo.api.model.movies;
import org.bson.Document;

import java.util.HashMap;
import java.util.List;

public interface CustomRepo {
    public List<movies> getMoviesOfActor(String actor);
    public List<Document> getTop10ImdbDirectors();

    List<movies> getMoviesWithGreaterRuntime(int runtime);

    List<movies> getMoviesFromNumReviewsAndRuntime(int tomatoNumReviews, int runtime);

    void updateTitle(String title, HashMap<String, String> body);

    void removeMovie(String title);
}
