package com.mjc.school.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mjc.school.repository.dao.NewsRepository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.NewsDtoResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class NewsRepositoryImplementation implements NewsRepository {

    private List<NewsDtoResponse> allNews;
    private List<Author> authors;

    {
        try {
            String jsonNews = Files.readString(Paths.get("module-repository/src/main/resources/content.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            CollectionType typeReferenceNews =
                    TypeFactory.defaultInstance().constructCollectionType(List.class, NewsDtoResponse.class);

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

    @Override
    public List<NewsDtoResponse> readAll() {
        return allNews;
    }

    @Override
    public NewsDtoResponse readById(long newsId) {
        try {
            return allNews.stream().filter(x -> x.getId() == newsId).toList().get(0);
        } catch (IndexOutOfBoundsException exception) {
            System.out.printf("ERROR_CODE: 000001 ERROR_MESSAGE: News with id %d does not exist.%n", newsId);
        }
        return null;
    }

    @Override
    public NewsDtoResponse create(String title, String content, long authorId) {
        if (!authors.stream().map(Author::getId).toList().contains(authorId)) {
            System.out.printf("ERROR_CODE: 000002 ERROR_MESSAGE: Author Id does not exist. Author Id is: %d%n", authorId);
            return null;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        NewsDtoResponse newNews = NewsDtoResponse.builder()
                .id(allNews.size() + 1)
                .title(title)
                .content(content)
                .createDate(dateTime)
                .lastUpdateDate(dateTime)
                .authorId(authorId).build();
        allNews.add(newNews);
        return newNews;
    }

    @Override
    public NewsDtoResponse update(NewsDtoResponse news, String title, String content, long authorId) {
        if (!authors.stream().map(Author::getId).toList().contains(authorId)) {
            System.out.printf("ERROR_CODE: 000002 ERROR_MESSAGE: Author Id does not exist. Author Id is: %d%n", authorId);
            return null;
        }

        LocalDateTime dateTime = LocalDateTime.now();
        NewsDtoResponse updatedNews = NewsDtoResponse.builder()
                .id(news.getId())
                .title(title)
                .content(content)
                .lastUpdateDate(dateTime)
                .authorId(authorId).build();
        allNews.set(allNews.indexOf(news), updatedNews);
        return updatedNews;
    }

    @Override
    public boolean delete(long newsId) {
        return allNews.remove(readById(newsId));
    }
}
