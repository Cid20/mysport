package sds_java.mysport.mapper;

import sds_java.mysport.entity.Feedback;
import sds_java.mysport.payload.res.ResFeedback;

public class MapperFeedback {
    public ResFeedback toResFeedback(Feedback feedback){
        return ResFeedback.builder()
                .userId(feedback.getUser().getId())
                .categoryId(feedback.getCategory().getId())
                .rate(feedback.getRate())
                .text(feedback.getText())
                .date(feedback.getDate())
                .build();
    }
}
