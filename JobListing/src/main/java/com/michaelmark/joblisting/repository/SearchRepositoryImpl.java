package com.michaelmark.joblisting.repository;

import com.michaelmark.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


@Component
public class SearchRepositoryImpl  implements SearchRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {
        final List<Post> posts = new ArrayList<>();
        MongoDatabase database = client.getDatabase("michaelmark");
        MongoCollection<org.bson.Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result;
        result = collection.aggregate(Arrays.asList(new Document("$search", new Document("index", "default").append("text", new Document("query", text).append("path", Arrays.asList("techs", "desc", "profile")))),
                new Document("$sort", new Document("exp", 1L)), new Document("$limit", 5L)));
        result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
        return posts;
    }
}
