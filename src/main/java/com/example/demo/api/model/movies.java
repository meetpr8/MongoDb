package com.example.demo.api.model;

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

    private List<Comment> comments;

    public movies() {
        super();
    }

    public movies(String id, String title, int year, List<String> cast, String plot, String fullPlot, Date lastUpdated, String type, String poster, List<String> directors, List<String> writers, IMDB imdb, int runtime, List<String> countries, List<String> genres, RottenTomatoes tomatoes, List<Comment> comments) {
        super();
        this.id = id;
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.plot = plot;
        this.fullPlot = fullPlot;
        this.lastUpdated = lastUpdated;
        this.type = type;
        this.poster = poster;
        this.directors = directors;
        this.writers = writers;
        this.imdb = imdb;
        this.runtime = runtime;
        this.countries = countries;
        this.genres = genres;
        this.tomatoes = tomatoes;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String Plot) {
        this.plot = Plot;
    }

    public String getFullPlot() {
        return fullPlot;
    }

    public void setFullPlot(String fullPlot) {
        this.fullPlot = fullPlot;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public IMDB getImdb() {
        return imdb;
    }

    public void setImdb(IMDB imdb) {
        this.imdb = imdb;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public RottenTomatoes getTomatoes() {
        return tomatoes;
    }

    public void setTomatoes(RottenTomatoes tomatoes) {
        this.tomatoes = tomatoes;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


}
