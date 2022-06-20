package com.litsynp.mongodbdemo.ex1mongotemplate;

import com.litsynp.mongodbdemo.document.Post;
import com.litsynp.mongodbdemo.dto.PostServiceUpdateRequestDto;
import com.litsynp.mongodbdemo.exception.PostNotFoundException;
import com.litsynp.mongodbdemo.service.PostService;
import java.util.List;
import java.util.Optional;
import java.util.function.LongSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Qualifier("postServiceMongoTemplate")
@RequiredArgsConstructor
public class PostServiceMongoTemplateImpl implements PostService {

    private final MongoTemplate mongoTemplate;

    @Override
    public Post findById(String id) {
        Post post = mongoTemplate.findById(id, Post.class);
        return Optional.ofNullable(post)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public Page<Post> search(Pageable pageable, String title) {
        Query query = new Query();

        // Add conditions
        query.addCriteria(titleEq(title));

        // Paginate
        query.with(pageable);

        List<Post> postList = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(postList, pageable, getTotalCount(query));
    }

    private LongSupplier getTotalCount(Query query) {
        return () -> mongoTemplate
                .count(Query.of(query)
                                .limit(-1)
                                .skip(-1),
                        Post.class);
    }

    @Override
    public Post create(Post post) {
        return mongoTemplate.insert(post);
    }

    @Override
    public Post update(String id, PostServiceUpdateRequestDto dto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        // Set properties to be updated
        Update update = new Update()
                .set("title", dto.getTitle())
                .set("content", dto.getContent());

        mongoTemplate.updateFirst(query, update, Post.class);
        return findById(id);
    }

    @Override
    public void deleteById(String id) {
        Post found = findById(id);
        mongoTemplate.remove(found);
    }

    private Criteria titleEq(String title) {
        if (StringUtils.hasText(title)) {
            return Criteria.where("title").is(title);
        }
        return new Criteria();
    }
}
