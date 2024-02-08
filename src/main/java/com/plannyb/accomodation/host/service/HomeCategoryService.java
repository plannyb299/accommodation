package com.plannyb.accomodation.host.service;

import com.plannyb.accomodation.dto.response.CategoryRes;
import com.plannyb.accomodation.entity.Category;

import java.util.Date;
import java.util.List;


public interface HomeCategoryService {
    CategoryRes findHomeCategoryDtoById(Long id);
    CategoryRes findHomeCategoryDtoByHomeCategoryTitle(String title);
    Category findHomeCategoryByHomeCategoryTitle(String title);
    Category findHomeCategoryById(Long id);
    List<CategoryRes> findAll();
}
