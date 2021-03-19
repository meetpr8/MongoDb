package com.example.demo.api.dao;

import com.example.demo.api.model.movies;
import com.example.demo.api.repository.MovieRepo;
import com.example.demo.api.repository.projection;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class MovieDao {
    @Autowired
    private MovieRepo movieRepo;
    public movies getMovie(String title) {
        return (movies) movieRepo.findByTitleSingle(title);
    }

    public List<movies> getMovieSorted(String title)
    {
        Sort sort = Sort.by("year").ascending().and(Sort.by("runtime").descending());
        return movieRepo.findByTitle(title, sort);
    }

    public projection getMovieWithProjection(String title){
        return movieRepo.findByTitleWithProjection(title);
    }

    public List<movies> getMovieByGenresAndCast( String genres, String cast)
    {
        return movieRepo.foo(genres, cast);
    }

    public List<movies> getAll() {return movieRepo.findAll();}

    public void addMovie(@RequestBody movies movie)
    {
        movieRepo.save(movie);
    }

    public List<movies> getMoviesOfActor(String actor) {
        return movieRepo.getMoviesOfActor(actor);
    }

    public List<Document> getTop10Directors() {
        return movieRepo.getTop10ImdbDirectors();
    }

    public List<movies> getMoviesWithGreaterRuntime(int runtime) {
        return movieRepo.getMoviesWithGreaterRuntime(runtime);
    }

    public List<movies> getMoviesFromNumReviewsAndRuntime(int tomatoNumReviews, int runtime) {
        return movieRepo.getMoviesFromNumReviewsAndRuntime(tomatoNumReviews, runtime);
    }
}
