package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;

import java.util.List;

public interface CateService {
    Page<Category> findPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void saveCategory(Category category);

    void updateStatus(Integer status, Integer id);

    void deleteCategory(Integer id);

    void update(Category category);

    List<Category> findAll(Integer type);
}
