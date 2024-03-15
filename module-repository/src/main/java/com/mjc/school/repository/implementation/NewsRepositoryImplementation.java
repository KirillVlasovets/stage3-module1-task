package com.mjc.school.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mjc.school.repository.dao.NewsRepository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.NewsDtoResponseModel;
import com.mjc.school.repository.source.DataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class NewsRepositoryImplementation implements NewsRepository {

    private List<NewsDtoResponseModel> allNews;
    private List<Author> authors;

    public NewsRepositoryImplementation() {
        DataSource dataSource = new DataSource();
        allNews = dataSource.getAllNews();
        authors = dataSource.getAuthors();
    }

    @Override
    public List<NewsDtoResponseModel> readAll() {
        return allNews;
    }

    @Override
    public NewsDtoResponseModel readById(Long newsId) {
        try {
            return allNews.stream().filter(x -> x.getId() == newsId).toList().get(0);
        } catch (IndexOutOfBoundsException exception) {
            System.out.printf("ERROR_CODE: 000001 ERROR_MESSAGE: News with id %d does not exist.%n", newsId);
        }
        return null;
    }

    @Override
    public NewsDtoResponseModel create(NewsDtoResponseModel news) {
        if (!authors.stream().map(Author::getId).toList().contains(news.getAuthorId())) {
            System.out.printf("ERROR_CODE: 000002 ERROR_MESSAGE: Author Id does not exist. Author Id is: %d%n", news.getAuthorId());
            return null;
        }
        news.setId(allNews.stream().mapToLong(NewsDtoResponseModel::getId).max().getAsLong() + 1);
        allNews.add(news);
        return news;
    }

    @Override
    public NewsDtoResponseModel update(NewsDtoResponseModel news) {
        if (!authors.stream().map(Author::getId).toList().contains(news.getAuthorId())) {
            System.out.printf("ERROR_CODE: 000002 ERROR_MESSAGE: Author Id does not exist. Author Id is: %d%n", news.getAuthorId());
            return null;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        news.setLastUpdateDate(dateTime);
        allNews.set(allNews.indexOf(readById(news.getId())), news);
        return news;
    }

    @Override
    public Boolean delete(Long newsId) {
        return allNews.remove(readById(newsId));
    }
}
