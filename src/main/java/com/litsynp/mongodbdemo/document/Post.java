package com.litsynp.mongodbdemo.document;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    private String id;

    private String title;

    private String content;

    private LocalDateTime createdOn;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdOn = LocalDateTime.now();
    }
}
