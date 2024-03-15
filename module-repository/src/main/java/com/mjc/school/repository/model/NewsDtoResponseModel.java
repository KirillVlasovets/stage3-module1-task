package com.mjc.school.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDtoResponseModel {

    @JsonProperty("id")
    @Builder.Default
    private Long id = 0L;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("createDate")
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
    @JsonProperty("lastUpdatedDate")
    @Builder.Default
    private LocalDateTime lastUpdateDate = LocalDateTime.now();
    @JsonProperty("authorId")
    @Builder.Default
    private Long authorId = 0L;

    @Override
    public String toString() {
        return "NewsDtoResponse[" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", authorId=" + authorId +
                ']';
    }
}
