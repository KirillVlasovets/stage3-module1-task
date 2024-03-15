package com.mjc.school.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;

}
