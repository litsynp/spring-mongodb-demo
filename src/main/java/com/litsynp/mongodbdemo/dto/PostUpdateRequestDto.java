package com.litsynp.mongodbdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequestDto {

    private String title;
    private String content;

    public PostServiceUpdateRequestDto toServiceDto() {
        return new PostServiceUpdateRequestDto(title, content);
    }
}
