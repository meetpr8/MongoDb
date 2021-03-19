package com.example.demo.api.repository;

import com.example.demo.api.model.movies;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.print.Doc;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

public class CustomRepoImpl implements CustomRepo{
    @Autowired
    MongoClient mongoClient;
    @Autowired
    MongoTemplate mongoTemplate;
    public Document explainQuery(Query query)
    {
        Document explainDocument = new Document();
        explainDocument.put("find", "movies");
        explainDocument.put("filter",query.getQueryObject());
        Document doc = new Document("explain",explainDocument);
        return mongoTemplate.getDb().runCommand(doc);
    }
    @Override
    public List<movies> getMoviesOfActor(String actor)
    {
        Criteria criteria = Criteria.where("cast").in(actor);
        Query query = new Query(criteria).with(Sort.by(desc("imdb.rating"), asc("year")));
        return mongoTemplate.find(query, movies.class);
    }

    @Override
    public List<Document> getTop10ImdbDirectors() {
        GroupOperation groupOperation = Aggregation.group("directors").
                count().as("Total Movies").
                max("imdb.rating").as("Highest (IMDB)Rated Movie");
        MatchOperation matchOperation = Aggregation.match(Criteria.where("imdb.rating").ne(null).ne(""));
        MatchOperation matchOperation1 = Aggregation.match(Criteria.where("_id").ne(null));
        Aggregation aggregation = Aggregation.newAggregation(
                matchOperation,
                groupOperation,
                Aggregation.sort(Sort.Direction.DESC, "Highest (IMDB)Rated Movie"),
                Aggregation.limit(10),
                matchOperation1
        );
        return mongoTemplate.aggregate(aggregation, movies.class, Document.class).getMappedResults();

    }

    @Override
    public List<movies> getMoviesWithGreaterRuntime(int runtime) {
        Criteria criteria = Criteria.where("runtime").ne(null).gt(runtime);
        Query query = new Query(criteria);
        query.fields().include("title", "cast", "runtime");
        return mongoTemplate.find(query, movies.class);
    }

    @Override
    public List<movies> getMoviesFromNumReviewsAndRuntime(int tomatoNumReviews, int runtime) {
        Criteria criteria = Criteria.where("tomatoes.viewer.numReviews").ne(null).gt(tomatoNumReviews).
                andOperator(Criteria.where("runtime").ne(null).gt(runtime));
        Query query = new Query(criteria).with(Sort.by(desc("tomatoes.viewer.numReview")));
        query.fields().include("title", "runtime", "tomatoes.viewer.numReviews");
        return mongoTemplate.find(query, movies.class);
    }

}
