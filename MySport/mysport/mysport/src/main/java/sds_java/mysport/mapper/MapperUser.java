package sds_java.mysport.mapper;


import lombok.RequiredArgsConstructor;
import sds_java.mysport.entity.User;
import sds_java.mysport.payload.res.ResUser;


@RequiredArgsConstructor
public class MapperUser {

    public static ResUser toResUser(User user) {

        return ResUser.builder()
                .id(user.getId())
                .userName(user.getFullName())
                .phone(user.getPhone())
                .userRole(user.getUserRole())
                .build();
    }

}