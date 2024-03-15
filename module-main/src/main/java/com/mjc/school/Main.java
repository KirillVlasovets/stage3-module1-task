package com.mjc.school;

import com.mjc.school.constants.ActionCodes;
import com.mjc.school.repository.model.NewsDtoResponse;
import com.mjc.school.service.NewsService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String instruction = """
                Enter the number of operation:
                1 - Get all news.
                2 - Get news by id.
                3 - Create news.
                4 - Update news.
                5 - Remove news by id.
                0 - Exit.
                """;
        System.out.println(instruction);
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        while (!in.equals(ActionCodes.EXIT)) {
            switch (in) {
                case ActionCodes.GET_ALL_NEWS:
                    System.out.println("Operation: Get all news.");
                    List<NewsDtoResponse> allNews = NewsService.get().getAllNews();
                    for (NewsDtoResponse news : allNews) {
                        System.out.println(news);
                    }
                    break;
                case ActionCodes.GET_NEWS_BY_ID:
                    System.out.println("Operation: Get news by id.");
                    System.out.println("Enter news id:");
                    System.out.println(NewsService.get().getNewsById(scanner.nextLine()));
                    break;
                case ActionCodes.CREATE_NEWS:
                    System.out.println("Operation: Create news.");
                    System.out.println(NewsService.get().createNews(scanner));
                    break;
                case ActionCodes.UPDATE_NEWS:
                    System.out.println("Operation: Update news.");
                    System.out.println(NewsService.get().updateNews(scanner));
                    break;
                case ActionCodes.REMOVE_NEWS_BY_ID:
                    System.out.println("Operation: Remove news by id.");
                    System.out.println("Enter news id:");
                    System.out.println(NewsService.get().deleteNewsById(scanner.nextLine()));
                    break;
                default:
                    System.out.println("Command not found.");
            }
            System.out.println(instruction);
            in = scanner.nextLine();
        }
    }
}