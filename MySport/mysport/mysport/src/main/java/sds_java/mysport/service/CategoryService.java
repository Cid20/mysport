package sds_java.mysport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sds_java.mysport.entity.Category;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.ResponseError;
import sds_java.mysport.payload.req.ReqCategory;
import sds_java.mysport.repository.CategoryRepository;
import sds_java.mysport.repository.method.CategoryMethod;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryMethod{
    private final CategoryRepository categoryRepository;


    @Override
    public ApiResponse saveCategory(ReqCategory reqCategory) {
        boolean exist = categoryRepository.existsByTitle(reqCategory.getTitle());
        if(exist){
            return new ApiResponse(ResponseError.ALREADY_EXIST("Title"));
        }
        Category category = Category.builder()
                .title(reqCategory.getTitle())
                .description(reqCategory.getDescription())
                .price(reqCategory.getPrice())
                .build();
        categoryRepository.save(category);
        return new ApiResponse("Saved");
    }

    @Override
    public ApiResponse updateCategory(Long id, ReqCategory reqCategory) {
        return null;
    }

    @Override
    public ApiResponse deleteCategory(Long id) {
        return null;
    }

    @Override
    public ApiResponse getAllCategory() {
        return null;
    }

    @Override
    public ApiResponse searchCategoryByField(String field) {
        return null;
    }
}
