package sds_java.mysport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sds_java.mysport.entity.Actions;
import sds_java.mysport.entity.File;
import sds_java.mysport.entity.User;
import sds_java.mysport.entity.enums.Events;
import sds_java.mysport.mapper.MapperAction;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.ResponseError;
import sds_java.mysport.payload.res.ResAction;
import sds_java.mysport.repository.ActionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActionService {
    private final ActionRepository actionRepository;

    public ApiResponse saveAction(User user, File file, Events events){
        if (file==null){
            return new ApiResponse(ResponseError.NOTFOUND("File"));
        }
        Actions actions = Actions.builder()
                .user(user)
                .file(file)
                .event(events)
                .build();

        actionRepository.save(actions);
        return new ApiResponse("Succes");
    }

    public ApiResponse getAllByEvent(Events event) {
        List<Actions> actions = actionRepository.findAllByEvent(event);
        List<ResAction> resActions = actions.stream()
                .map(MapperAction::toResAction)
                .collect(Collectors.toList());

        return new ApiResponse(resActions);
    }
}