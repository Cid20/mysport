package sds_java.mysport.repository;

import sds_java.mysport.entity.User;
import sds_java.mysport.entity.enums.UserRole;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.auth.AuthRegister;
import sds_java.mysport.payload.req.ReqUser;

public interface UserMethod  {
    ApiResponse saveUser(ReqUser reqUser, UserRole role);
    ApiResponse updateUser(User user, AuthRegister authRegister);
    ApiResponse deleteUser(Long id);
    ApiResponse getAllUser(UserRole role,int page, int size);
    ApiResponse searchUserByRole(String field, UserRole role);
}
