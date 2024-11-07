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
import sds_java.mysport.repository.method.UserMethod;
import sds_java.mysport.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .FullName(reqUser.getUserName())
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
                .FullName(authRegister.getUsername())
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
        User user = userRepository.findById(id).orElseThrow(()->
                new RuntimeException(String.valueOf(ResponseError.NOTFOUND("User"))));
        user.setEnabled(false);
        return new ApiResponse("Deleted");
    }
    public ApiResponse getMeUser(User user) {
        boolean exist = userRepository.existsById(user.getId());
        if (!exist) {
            return new ApiResponse(ResponseError.NOTFOUND("User"));
        }
        Long id = user.getId();
        Optional<User> user1 = userRepository.findById(id);
        List<ResUser> resUsers = user1.stream()
                .map(MapperUser::toResUser)
                .collect(Collectors.toList());
        return new ApiResponse(resUsers);

    }
    @Override
    public ApiResponse getAllUser(UserRole role, int page, int size) {
        Page<User> users = userRepository.findAllByUserRole(role, PageRequest.of(page, size));
        List<ResUser> resUsers = users.getContent().stream()
                .map(MapperUser::toResUser)
                .collect(Collectors.toList());
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
        List<ResUser> resUsers = users.stream()
                .map(MapperUser::toResUser)
                .collect(Collectors.toList());
        return new ApiResponse(resUsers);
    }

}
