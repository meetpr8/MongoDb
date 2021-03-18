package com.example.demo.api.controller;

import com.example.demo.api.model.movies;
import com.example.demo.api.repository.MovieRepo;
import com.example.demo.api.repository.projection;
import com.example.demo.api.service.MovieService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/{title}")
    public movies getMovie(@PathVariable(value = "title") String title) {
        return (movies) movieService.getMovie(title);
    }

    @GetMapping(value = "/{title}/sort")
    public List<movies> getMovieSorted(@PathVariable(value = "title") String title)
    {
        return movieService.getMovieSorted(title);
    }

    @GetMapping(value = "/{title}/projection")
    public projection getMovieWithProjection(@PathVariable(value = "title") String title){
        return movieService.getMovieWithProjection(title);
    }

    @GetMapping(value = "/{genres}/{cast}")
    public List<movies> getMovieByGenresAndCast(@PathVariable(value = "genres") String genres, @PathVariable(value = "cast") String cast)
    {
        return movieService.getMovieByGenresAndCast(genres, cast);
    }

    @GetMapping(value = "/all")
    public List<movies> getAll()
    {
        return movieService.getAll();
    }

    @PutMapping(value = "/add")
    public void addMovie(@RequestBody movies movie)
    {
        movieService.addMovie(movie);
    }

    @GetMapping(value = "/moviesOf/{actor}")
    public List<movies> getMoviesOfActor(@PathVariable(value = "actor") String actor)
    {
        return movieService.getMoviesOfActor(actor);
    }

    @GetMapping(value = "/top10Directors")
    public List<Document> getTop10Directors()
    {
        return movieService.getTop10Directors();
    }


}
