package sds_java.mysport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sds_java.mysport.entity.User;
import sds_java.mysport.entity.enums.UserRole;
import sds_java.mysport.mapper.MapperUser;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.CustomPageable;
import sds_java.mysport.payload.ResponseError;
import sds_java.mysport.payload.auth.AuthRegister;
import sds_java.mysport.payload.req.ReqUser;
import sds_java.mysport.payload.res.ResUser;
import sds_java.mysport.repository.UserMethod;
import sds_java.mysport.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends MapperUser implements UserMethod {
  private final UserRepository userRepository;
    @Override
    public ApiResponse saveUser(ReqUser reqUser, UserRole role) {
     boolean exist = userRepository.existsByPhone(reqUser.getPhone());
if (exist) {
    return new ApiResponse(ResponseError.ALREADY_EXIST("Phone number"));
}
        User user = User.builder()
                .userRole(role)
                .username(reqUser.getUsername())
                .phone(reqUser.getPhone())
                .password(reqUser.getPassword())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        userRepository.save(user);
        return new ApiResponse("Success");
    }

    @Override
    public ApiResponse updateUser(User user, AuthRegister authRegister) {
        boolean exist = userRepository.existsById(user.getId());
        if (!exist) {
            return new ApiResponse(ResponseError.NOTFOUND("User"));
        }
         User user1 = User.builder()
                .username(authRegister.getUsername())
                .phone(authRegister.getPhone())
                .password(authRegister.getPassword())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
        userRepository.save(user1);
        return new ApiResponse("Success");
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        boolean exist = userRepository.existsById(id);
        if (!exist) {
            return new ApiResponse(ResponseError.NOTFOUND("User"));
        }
        User user = userRepository.findById(id).get();
        user.setEnabled(false);
        return new ApiResponse("Deleted");
    }
    public ApiResponse getMeUser(User user) {
        boolean exist = userRepository.existsById(user.getId());
        if (!exist) {
            return new ApiResponse(ResponseError.NOTFOUND("User"));
        }
        Long id = user.getId();
        User user1 = userRepository.findById(id).get();
        return new ApiResponse(toResUser(user1));

    }

    @Override
    public ApiResponse getAllUser(UserRole role, int page, int size) {
        Page<User> users = userRepository.findAllByUserRole(role, PageRequest.of(page, size));
        List<ResUser> resUsers = toResUser(users.getContent());
        CustomPageable customPageable = new CustomPageable();
        customPageable.setData(resUsers);
        customPageable.setPage(users.getNumber());
        customPageable.setTotalPage(users.getTotalPages());
        customPageable.setTotalElements(users.getTotalElements());
        return new ApiResponse(customPageable);
    }

    @Override
    public ApiResponse searchUserByRole(String field, UserRole role) {
        List<User> users = userRepository.searchUserRole(field, role);
        List<ResUser> resUsers = toResUser(users);
        return new ApiResponse(resUsers);
    }

}
