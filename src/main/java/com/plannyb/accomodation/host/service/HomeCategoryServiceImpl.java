package com.plannyb.accomodation.host.service;

import com.plannyb.accomodation.dto.response.CategoryRes;
import com.plannyb.accomodation.entity.Category;
import com.plannyb.accomodation.host.processor.HomeCategoryConverter;
import com.plannyb.accomodation.host.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {


     private final CategoryRepository homeCategoryRepository;

    @Override
    public CategoryRes findHomeCategoryDtoById(Long id) {
        Category homeCategory;
        homeCategory = homeCategoryRepository.findById(id).get();
        return HomeCategoryConverter.convertToDto(homeCategory);
    }
    @Override
    public CategoryRes findHomeCategoryDtoByHomeCategoryTitle(String title) {
        Category homeCategory;
        homeCategory = homeCategoryRepository.findByHomeCategoryTitle(title).get();
        return HomeCategoryConverter.convertToDto(homeCategory);
    }

    @Override
    public Category findHomeCategoryById(Long id) {
        Category homeCategory;
        homeCategory = homeCategoryRepository.findById(id).get();
        return homeCategory;
    }

    @Override
    public Category findHomeCategoryByHomeCategoryTitle(String title) {
        Category homeCategory;
        homeCategory = homeCategoryRepository.findByHomeCategoryTitle(title).get();
        return homeCategory;
    }

    @Override
    public List<CategoryRes> findAll() {
        return homeCategoryRepository.findAll()
                .stream()
                .map(HomeCategoryConverter::convertToDto)
                .collect(Collectors.toList());
    }

}
