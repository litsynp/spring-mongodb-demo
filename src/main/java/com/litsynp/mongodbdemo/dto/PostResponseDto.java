package com.litsynp.mongodbdemo.dto;

import com.litsynp.mongodbdemo.document.Post;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private String id;
    private String title;
    private String content;
    private LocalDateTime createdOn;

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(post.getId(), post.getTitle(), post.getContent(),
                post.getCreatedOn());
    }
}
