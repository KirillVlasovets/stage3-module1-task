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
    private long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("createDate")
    private LocalDateTime createDate;
    @JsonProperty("lastUpdatedDate")
    private LocalDateTime lastUpdateDate;
    @JsonProperty("authorId")
    private long authorId;

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
