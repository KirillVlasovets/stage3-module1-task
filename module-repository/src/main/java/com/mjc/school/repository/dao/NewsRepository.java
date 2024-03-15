package com.mjc.school.repository.dao;

import com.mjc.school.repository.model.NewsDtoResponseModel;

import java.util.List;

public interface NewsRepository {

    List<NewsDtoResponseModel> readAll();

    NewsDtoResponseModel readById(long newsId);

    NewsDtoResponseModel create(String title, String content, long authorId);

    NewsDtoResponseModel update(NewsDtoResponseModel news, String title, String content, long authorId);

    Boolean delete(long newsId);
}
