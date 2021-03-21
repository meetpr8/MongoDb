package com.example.demo.api.controller;

import com.example.demo.api.model.movies;
import com.example.demo.api.repository.MovieRepo;
import com.example.demo.api.repository.projection;
import com.example.demo.api.service.MovieService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/{title}")
    public ResponseEntity<Map> getMovie(@PathVariable(value = "title") String title) {
        HashMap<String, Object> result = new HashMap<>();
        movies movie = movieService.getMovie(title);
        if(movie==null)
        {
            result.put("Error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("Movie", movie);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{title}/sort")
    public ResponseEntity<Map> getMovieSorted(@PathVariable(value = "title") String title) {
        HashMap<String, Object> result = new HashMap<>();
        List<movies> Movies = movieService.getMovieSorted(title);
        if(Movies.size()==0)
        {
            result.put("Error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("Movies", Movies);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{title}/projection")
    public ResponseEntity<Map> getMovieWithProjection(@PathVariable(value = "title") String title){
        HashMap<String, Object> result = new HashMap<>();
        projection projectedMovie = movieService.getMovieWithProjection(title);
        if(projectedMovie==null)
        {
            result.put("Error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("Movies", projectedMovie);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{genres}/{cast}")
    public ResponseEntity<Map> getMovieByGenresAndCast(@PathVariable(value = "genres") String genres, @PathVariable(value = "cast") String cast) {
        HashMap<String, Object> result = new HashMap<>();
        List<movies> Movies = movieService.getMovieByGenresAndCast(genres, cast);
        if(Movies.size()==0)
        {
            result.put("Error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("Movies", Movies);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Map> getAll() {
        HashMap<String, Object> result = new HashMap<>();
        List<movies> Movies = movieService.getAll();
        if(Movies.size()==0)
        {
            result.put("Error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("Movies", Movies);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/add")
    public ResponseEntity addMovie(@RequestBody movies movie) {
        HashMap<String, Object> result = new HashMap<>();
        if(!movieService.addMovie(movie))
        {
            result.put("Error", "Duplicate ID");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/moviesOf/{actor}")
    public ResponseEntity<Map> getMoviesOfActor(@PathVariable(value = "actor") String actor) {
        HashMap<String, Object> result = new HashMap<>();
        List<movies> Movies = movieService.getMoviesOfActor(actor);
        if(Movies.size()==0)
        {
            result.put("Error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("Movies", Movies);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/top10Directors")
    public List<Document> getTop10Directors() {
        HashMap<String, Object> result = new HashMap<>();
        return movieService.getTop10Directors();
    }

    @GetMapping(value = "/runtime>/{runtime}")
    public ResponseEntity<Map> getMoviesWithGreaterRuntime(@PathVariable(value = "runtime") int runtime) {
        HashMap<String, Object> result = new HashMap<>();
        List<movies> Movies = movieService.getMoviesWithGreaterRuntime(runtime);
        if(Movies.size()==0)
        {
            result.put("Error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("Movies", Movies);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/ReviewsAndRuntime/{tomatoNumReviews}/{runtime}")
    public ResponseEntity<Map> getMoviesFromNumReviewsAndRuntime(@PathVariable(value = "tomatoNumReviews") int tomatoNumReviews, @PathVariable(value = "runtime") int runtime) {
        HashMap<String, Object> result = new HashMap<>();
        List<movies> Movies = movieService.getMoviesFromNumReviewsAndRuntime(tomatoNumReviews, runtime);
        if(Movies.size()==0)
        {
            result.put("Error", "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("Movies", Movies);
        return ResponseEntity.ok(result);    }

    @PutMapping(value = "update/{title}")
    public ResponseEntity updateTitle(@PathVariable String title, @RequestBody HashMap<String, String> body) {
        if(getMovie(title)==null)
        {
            HashMap<String, Object> result = new HashMap<>();
            result.put("Error", "Movie Not Found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        movieService.updateTitle(title, body);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "remove/{title}")
    public ResponseEntity removeMovie(@PathVariable String title) {
        if(getMovie(title)==null)
        {
            HashMap<String, Object> result = new HashMap<>();
            result.put("Error", "Movie Not Found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        movieService.removeMovie(title);
        return ResponseEntity.ok().build();
    }


}
