package sds_java.mysport.mapper;

import lombok.RequiredArgsConstructor;
import sds_java.mysport.entity.Actions;
import sds_java.mysport.payload.res.ResAction;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MapperAction {

    public static ResAction toResAction(Actions action) {
        ResAction resAction = new ResAction();
        resAction.setId(action.getId());
        resAction.setUsername(action.getUser().getUsername());
        resAction.setFileId(action.getFile().getId());
        resAction.setTime(LocalDateTime.now());
        return resAction;
    }
}
