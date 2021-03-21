package com.example.demo.api.service;

import com.example.demo.api.dao.MovieDao;
import com.example.demo.api.model.movies;
import com.example.demo.api.repository.MovieRepo;
import com.example.demo.api.repository.projection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    private MovieDao movieDao;
    public movies getMovie(String title) {
        return movieDao.getMovie(title);
    }

    public List<movies> getMovieSorted(String title)
    {
        return movieDao.getMovieSorted(title);
    }

    public projection getMovieWithProjection(String title){
        return movieDao.getMovieWithProjection(title);
    }

    public List<movies> getMovieByGenresAndCast(String genres, String cast)
    {
        return movieDao.getMovieByGenresAndCast(genres, cast);
    }

    public List<movies> getAll()
    {
        return movieDao.getAll();
    }

    public boolean addMovie(movies movie)
    {
        return movieDao.addMovie(movie);
    }

    public List<movies> getMoviesOfActor(String actor) {
        return movieDao.getMoviesOfActor(actor);
    }

    public List<Document> getTop10Directors() {
        return movieDao.getTop10Directors();
    }

    public List<movies> getMoviesWithGreaterRuntime(int runtime) {
        return movieDao.getMoviesWithGreaterRuntime(runtime);
    }

    public List<movies> getMoviesFromNumReviewsAndRuntime(int tomatoNumReviews, int runtime) {
        return movieDao.getMoviesFromNumReviewsAndRuntime(tomatoNumReviews, runtime);
    }

    public void updateTitle(String title, HashMap<String, String> body) {
        movieDao.updateTitle(title, body);
    }

    public void removeMovie(String title) {
        movieDao.removeMovie(title);
    }
}
