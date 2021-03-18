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

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieDao movieDao;
    public movies getMovie(String title) {
        return (movies) movieDao.getMovie(title);
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

    public void addMovie(movies movie)
    {
        movieDao.addMovie(movie);
    }

    public List<movies> getMoviesOfActor(String actor) {
        return movieDao.getMoviesOfActor(actor);
    }

    public List<Document> getTop10Directors() {
        return movieDao.getTop10Directors();
    }
}
