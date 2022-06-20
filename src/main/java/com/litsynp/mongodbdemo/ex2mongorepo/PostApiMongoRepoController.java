package com.litsynp.mongodbdemo.ex2mongorepo;

import com.litsynp.mongodbdemo.document.Post;
import com.litsynp.mongodbdemo.dto.PostCreateRequestDto;
import com.litsynp.mongodbdemo.dto.PostResponseDto;
import com.litsynp.mongodbdemo.dto.PostUpdateRequestDto;
import com.litsynp.mongodbdemo.service.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/posts")
public class PostApiMongoRepoController {

    private final PostService postService;

    public PostApiMongoRepoController(@Qualifier("postServiceMongoRepo") PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> create(@RequestBody PostCreateRequestDto dto) {
        Post created = postService.create(dto.toEntity());
        PostResponseDto response = PostResponseDto.from(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<PostResponseDto>> list(
            @PageableDefault(sort = "createdOn", direction = Direction.DESC) Pageable pageable,
            @RequestParam(value = "title", required = false) String title) {
        Page<Post> posts = postService.search(pageable, title);
        Page<PostResponseDto> response = posts.map(PostResponseDto::from);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> detail(@PathVariable String id) {
        Post found = postService.findById(id);
        PostResponseDto response = PostResponseDto.from(found);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> update(
            @PathVariable String id,
            @RequestBody PostUpdateRequestDto dto) {
        Post updated = postService.update(id, dto.toServiceDto());
        PostResponseDto response = PostResponseDto.from(updated);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
