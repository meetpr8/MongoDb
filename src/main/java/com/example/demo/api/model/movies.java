package com.example.demo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document

@CompoundIndexes({
        @CompoundIndex(name = "tomato_numReviews_runtime", def = "{'tomatoes.viewer.numReviews': 1, 'runtime': 1}"),
        @CompoundIndex(name = "runtime", def = "{'runtime': 1}")
})

@Getter @Setter
public class movies{
    @Id
    private String id;
    private String title;
    private int year;
    private List<String> cast;
    private String plot;
    private String fullPlot;
    private Date lastUpdated;
    private String type;
    private String poster;
    private List<String> directors;
    private List<String> writers;
    private IMDB imdb;
//    @Indexed
    private int runtime;

    private List<String> countries;
    private List<String> genres;

    private RottenTomatoes tomatoes;



}
