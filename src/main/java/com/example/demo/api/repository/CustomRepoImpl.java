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
import org.springframework.data.mongodb.core.query.Update;

import javax.print.Doc;
import java.util.HashMap;
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
    /** This function is using simple Criteria query to find a movies of the given actor by searching in the cast array.
     Also it is sorting the results first according to the imdb rating of that movie and then using year of release in
     the case of tie.**/
    @Override
    public List<movies> getMoviesOfActor(String actor)
    {
        Criteria criteria = Criteria.where("cast").in(actor);
        Query query = new Query(criteria).with(Sort.by(desc("imdb.rating"), asc("year")));
        return mongoTemplate.find(query, movies.class);
    }

    /**Here, I've implemented aggregation to get top 10 directors in terms of their highest rated imdb movie.
     The first match stage is just to remove all the movies with null or unspecified imdb rating.
     Then where using group property of Aggregation to group by "directors" and then finding highest rated movie of
     each director using mac metric.
     Then in the next stage, we are sorting the results in ascending order of highest rated movie. And then limiting the
     results by 10.**/
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

    /**I implemented the next two queries to check the effect of indexing as i'd made two indices, one on runtime and
     second on tomatoes.viewer.numReviews and runtime **/
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

    /**Below are the implementations of, first, update by searching on the movie title and second, removal by searching on
     movie title**/
    @Override
    public void updateTitle(String title, HashMap<String, String> body) {
        Criteria criteria = Criteria.where("title").is(title);
        Query query = new Query(criteria);
        Update update = new Update();
        for(HashMap.Entry<String,String> itr : body.entrySet())
        {
            update.set(itr.getKey(), itr.getValue());
        }
        mongoTemplate.updateFirst(query, update, movies.class);
    }

    @Override
    public void removeMovie(String title) {
        mongoTemplate.remove(new Query(Criteria.where("title").in(title)), movies.class);
    }

}
