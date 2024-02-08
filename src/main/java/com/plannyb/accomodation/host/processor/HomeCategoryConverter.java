package com.plannyb.accomodation.host.processor;

import com.plannyb.accomodation.dto.request.CategoryReq;
import com.plannyb.accomodation.entity.Category;
import com.project.homerent.service.HomeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HomeCategoryConverter {

    @Autowired
    private HomeCategoryService homeCategoryService;

    public static HomeCategoryDto convertToDto(Category homeCategory) {
        HomeCategoryDto homeCategoryDto = new HomeCategoryDto();
//        homeCategoryDto.setId(homeCategory.getId());
        homeCategoryDto.setHomeCategoryTitle(homeCategory.getHomeCategoryTitle());
        return homeCategoryDto;
    }

    public Category convert(CategoryReq homeCategoryDto) {
        Category homeCategory = homeCategoryService.findHomeCategoryByHomeCategoryTitle(homeCategoryDto.getHomeCategoryTitle());
        return homeCategory;
    }
}