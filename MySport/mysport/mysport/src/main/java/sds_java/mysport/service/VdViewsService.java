package sds_java.mysport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sds_java.mysport.controller.FileController;
import sds_java.mysport.entity.File;
import sds_java.mysport.entity.User;
import sds_java.mysport.entity.VideoViews;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.req.ReqView;
import sds_java.mysport.repository.FileRepository;
import sds_java.mysport.repository.UserRepository;
import sds_java.mysport.repository.VdViewsRepository;

@Service
@RequiredArgsConstructor
public class VdViewsService {
    private final VdViewsRepository viewsRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    public ApiResponse addView(User user,ReqView reqView){
        File file = fileRepository.findById(reqView.getVideoId()).orElse(null);
        VideoViews videoViews = VideoViews.builder()
                .user(user)
                .file(file)
                .duration(reqView.getDuration())
                .build();
        viewsRepository.save(videoViews);
        return new ApiResponse("Succes");
    }

    public ApiResponse getViews(Long videoId){
        Integer count = viewsRepository.countByFileId(videoId);
        return new ApiResponse(count);
    }
}
