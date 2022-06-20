package com.litsynp.mongodbdemo.ex2mongorepo;

import com.litsynp.mongodbdemo.document.Post;
import java.util.List;
import java.util.function.LongSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

    private final MongoTemplate mongoTemplate;

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

    private Criteria titleEq(String title) {
        if (StringUtils.hasText(title)) {
            return Criteria.where("title").is(title);
        }
        return new Criteria();
    }
}
