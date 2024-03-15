package com.mjc.school.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.NewsDtoResponseModel;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Data
public class DataSource {

    private List<NewsDtoResponseModel> allNews;
    private List<Author> authors;

    public DataSource() {
        try {
            String jsonNews = Files.readString(Paths.get("module-repository/src/main/resources/content.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            CollectionType typeReferenceNews =
                    TypeFactory.defaultInstance().constructCollectionType(List.class, NewsDtoResponseModel.class);

            String jsonAuthors = Files.readString(Paths.get("module-repository/src/main/resources/author.json"));
            CollectionType typeReferenceAuthors =
                    TypeFactory.defaultInstance().constructCollectionType(List.class, Author.class);
            try {
                allNews = objectMapper.readValue(jsonNews, typeReferenceNews);
                authors = objectMapper.readValue(jsonAuthors, typeReferenceAuthors);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
