package sds_java.mysport.mapper;


import sds_java.mysport.entity.User;
import sds_java.mysport.entity.enums.UserRole;
import sds_java.mysport.payload.req.ReqUser;
import sds_java.mysport.payload.res.ResUser;

import java.util.List;

public class MapperUser {

    public ResUser toResUser(User user) {

        return ResUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .userRole(user.getUserRole().name())
                .build();
    }
    public List<ResUser> toResUser(List<User> users) { return users.stream().map(this::toResUser).toList(); }
    public User toUser(ReqUser reqUser) {
        return User.builder()
                .username(reqUser.getUsername())
                .phone(reqUser.getPhone())
                .password(reqUser.getPassword())
                .userRole(UserRole.USER)
                .build();

    }
    public List<User> toUser(List<ReqUser> reqUsers) { return reqUsers.stream().map(this::toUser).toList(); }
}
