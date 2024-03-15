package com.mjc.school.repository.dao;

import com.mjc.school.repository.model.NewsDtoResponse;

import java.util.List;

public interface NewsRepository {

    List<NewsDtoResponse> readAll();

    NewsDtoResponse readById(long newsId);

    NewsDtoResponse create(String title, String content, long authorId);

    NewsDtoResponse update(NewsDtoResponse news, String title, String content, long authorId);

    boolean delete(long newsId);
}
