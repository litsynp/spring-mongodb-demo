package com.litsynp.mongodbdemo.ex2mongorepo;

import com.litsynp.mongodbdemo.document.Post;
import com.litsynp.mongodbdemo.dto.PostServiceUpdateRequestDto;
import com.litsynp.mongodbdemo.exception.PostNotFoundException;
import com.litsynp.mongodbdemo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Qualifier("postServiceMongoRepo")
@RequiredArgsConstructor
public class PostServiceMongoRepoImpl implements PostService {

    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    @Override
    public Post findById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public Page<Post> search(Pageable pageable, String title) {
        return postQueryRepository.search(pageable, title);
    }

    @Override
    public Post create(Post post) {
        return postRepository.insert(post);
    }

    @Override
    public Post update(String id, PostServiceUpdateRequestDto dto) {
        Post existing = findById(id);
        existing.update(dto.getTitle(), dto.getContent());
        return postRepository.save(existing);
    }

    @Override
    public void deleteById(String id) {
        Post existing = findById(id);
        postRepository.delete(existing);
    }
}
