package com.litsynp.mongodbdemo.service;

import com.litsynp.mongodbdemo.document.Post;
import com.litsynp.mongodbdemo.dto.PostServiceUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Post findById(String id);

    Page<Post> search(Pageable pageable, String title);

    Post create(Post post);

    Post update(String id, PostServiceUpdateRequestDto dto);

    void deleteById(String id);
}
