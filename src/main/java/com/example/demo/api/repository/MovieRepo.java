package com.example.demo.api.repository;

import com.example.demo.api.model.movies;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MovieRepo extends MongoRepository<movies, String>,CustomRepo {
    @Query(value = "{'title': ?0}", fields = "{'title': 1, 'directors': 1, '_id': 0, 'plot': 1}")
    public movies findByTitleSingle(String title);
    @Query(value = "{'genres': ?0, 'cast': ?1}")
    public List<movies> foo(String genres, String cast);
    @Query(value = "{'title': ?0}")
    public projection findByTitleWithProjection(String title);
    public List<movies> findByTitle(String title, Sort sort);
}
