package com.mjc.school.service;

import com.mjc.school.repository.implementation.NewsRepositoryImplementation;
import com.mjc.school.repository.model.NewsDtoResponse;

import java.util.List;
import java.util.Scanner;

public class NewsService {

    private static NewsService instance;
    private static final NewsRepositoryImplementation REPOSITORY = new NewsRepositoryImplementation();

    public static NewsService get() {
        if (instance == null) {
            instance = new NewsService();
        }
        return instance;
    }

    public List<NewsDtoResponse> getAllNews() {
        return REPOSITORY.readAll();
    }

    public NewsDtoResponse getNewsById(String stringNewsId) {
        long newsId;
        try {
            newsId = Long.parseLong(stringNewsId);
        } catch (NumberFormatException exception) {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: News Id should be number");
            return getNewsById(stringNewsId);
        }
        return REPOSITORY.readById(newsId);
    }

    public NewsDtoResponse createNews(Scanner scanner) {
        NewsDtoResponse newNews = setTitleAndContent(scanner);
        String title = newNews.getTitle();
        String content = newNews.getContent();
        System.out.println("Enter author id:");
        String stringAuthorId = scanner.nextLine();
        if (checkTitleAndContent(title, content)) {
            return null;
        }
        long authorId;
        try {
            authorId = Long.parseLong(stringAuthorId);
        } catch (NumberFormatException exception) {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: Author Id should be number");
            return createNews(scanner);
        }
        return REPOSITORY.create(title, content, authorId);
    }

    public NewsDtoResponse updateNews(Scanner scanner) {
        System.out.println("Enter news id:");
        String id = scanner.nextLine();
        long newsId;
        try {
            newsId = Long.parseLong(id);
        } catch (NumberFormatException exception) {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: News Id should be number");
            return updateNews(scanner);
        }
        NewsDtoResponse updatedNews = REPOSITORY.readById(newsId);
        if (updatedNews == null) {
            return updateNews(scanner);
        }
        NewsDtoResponse newNews = setTitleAndContent(scanner);
        String title = newNews.getTitle();
        String content = newNews.getContent();

        System.out.println("Enter author id:");
        String stringAuthorId = scanner.nextLine();
        if (checkTitleAndContent(title, content)) {
            return null;
        }
        long authorId;
        try {
            authorId = Long.parseLong(stringAuthorId);
        } catch (NumberFormatException exception) {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: Author Id should be number");
            return createNews(scanner);
        }
        return REPOSITORY.update(updatedNews, title, content, authorId);
    }

    public boolean deleteNewsById(String stringNewsId) {
        long newsId;
        try {
            newsId = Long.parseLong(stringNewsId);
        } catch (NumberFormatException exception) {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: News Id should be number");
            return deleteNewsById(stringNewsId);
        }
        return REPOSITORY.delete(newsId);
    }

    private boolean checkTitleAndContent(String title, String content) {
        if (title.length() < 5 || title.length() > 30) {
            System.out.printf("ERROR_CODE: 000012 ERROR_MESSAGE: News title can not be less than 5 and more than 30 symbols. News title is %s%n",
                    title);
            return true;
        } else if (content.length() < 5 || content.length() > 255) {
            System.out.printf("ERROR_CODE: 000012 ERROR_MESSAGE: News content can not be less than 5 and more than 255 symbols. News content is %s%n",
                    content);
            return true;
        }
        return false;
    }

    private NewsDtoResponse setTitleAndContent(Scanner scanner) {
        System.out.println("Enter news title:");
        String title = scanner.nextLine();
        System.out.println("Enter news content:");
        String content = scanner.nextLine();
        return NewsDtoResponse.builder()
                .title(title)
                .content(content)
                .build();
    }
}