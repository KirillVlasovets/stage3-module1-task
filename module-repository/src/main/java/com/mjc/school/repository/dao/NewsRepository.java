package com.mjc.school.repository.dao;

import com.mjc.school.repository.model.NewsDtoResponseModel;

import java.util.List;

public interface NewsRepository {

    List<NewsDtoResponseModel> readAll();

    NewsDtoResponseModel readById(Long newsId);

    NewsDtoResponseModel create(NewsDtoResponseModel news);

    NewsDtoResponseModel update(NewsDtoResponseModel news);

    Boolean delete(Long newsId);
}
