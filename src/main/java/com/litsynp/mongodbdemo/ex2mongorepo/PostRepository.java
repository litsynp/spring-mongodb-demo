package com.litsynp.mongodbdemo.ex2mongorepo;

import com.litsynp.mongodbdemo.document.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
