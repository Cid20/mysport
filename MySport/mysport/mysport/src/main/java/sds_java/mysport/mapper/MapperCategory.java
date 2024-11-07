package sds_java.mysport.mapper;

import sds_java.mysport.entity.Category;
import sds_java.mysport.payload.res.ResCategory;

public class MapperCategory {
    public static ResCategory toResCategory(Category category) {
       return  ResCategory.builder()
                .name(category.getTitle())
                .price(category.getPrice())
                .description(category.getDescription())
                .rating(category.getRating())
                .build();


    }
}
