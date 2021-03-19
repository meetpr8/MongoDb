package com.example.demo.api.repository;

import com.example.demo.api.model.movies;
import org.bson.Document;

import java.util.List;

public interface CustomRepo {
    public List<movies> getMoviesOfActor(String actor);
    public List<Document> getTop10ImdbDirectors();
}