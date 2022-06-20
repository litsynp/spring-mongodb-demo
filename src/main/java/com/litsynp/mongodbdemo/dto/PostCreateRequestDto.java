package com.litsynp.mongodbdemo.dto;

import com.litsynp.mongodbdemo.document.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDto {

    private String title;
    private String content;

    public Post toEntity() {
        return new Post(title, content);
    }
}
