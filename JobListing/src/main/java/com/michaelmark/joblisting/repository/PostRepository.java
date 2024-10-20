package com.michaelmark.joblisting.repository;

import com.michaelmark.joblisting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
