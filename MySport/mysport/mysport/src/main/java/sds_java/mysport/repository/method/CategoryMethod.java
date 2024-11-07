package sds_java.mysport.repository.method;

import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.req.ReqCategory;

public interface CategoryMethod {
    ApiResponse saveCategory(ReqCategory reqCategory);
    ApiResponse updateCategory(Long id, ReqCategory reqCategory);
    ApiResponse deleteCategory(Long id);
    ApiResponse getAllCategory();
    ApiResponse searchCategoryByField(String field);
}
