package com.plannyb.accomodation.host.processor;

import com.plannyb.accomodation.dto.request.CategoryReq;
import com.plannyb.accomodation.dto.response.CategoryRes;
import com.plannyb.accomodation.entity.Category;
import com.plannyb.accomodation.host.service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class HomeCategoryConverter {


    private final HomeCategoryService homeCategoryService;

    public static CategoryRes convertToDto(Category homeCategory) {
        CategoryRes homeCategoryDto = new CategoryRes();
//        homeCategoryDto.setId(homeCategory.getId());
        homeCategoryDto.setHomeCategoryTitle(homeCategory.getHomeCategoryTitle());
        return homeCategoryDto;
    }

    public Category convert(CategoryReq homeCategoryDto) {
        Category homeCategory = homeCategoryService.findHomeCategoryByHomeCategoryTitle(homeCategoryDto.getHomeCategoryTitle());
        return homeCategory;
    }
}