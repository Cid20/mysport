package sds_java.mysport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sds_java.mysport.entity.Feedback;
import sds_java.mysport.entity.User;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.req.ReqFeedback;
import sds_java.mysport.repository.FeedbackRepository;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final NotificationService notificationService;
    private ActionService actionService;

    public ApiResponse saveFeedback(User user, ReqFeedback reqFeedback){
        Feedback feedback = Feedback.builder()
                .user(user)
                .text(reqFeedback.getText())
                .rate(reqFeedback.getRate())
                .build();

        feedbackRepository.save(feedback);
        notificationService.saveNotification(
                user,
                "Feedback saved",
                user.getUsername() + " izoh uchun raxmat!",
                reqFeedback.getPackageId(),
                false
        );
//        actionService.saveAction(user,); todo
        return new ApiResponse("Succes");
    }

    public ApiResponse getAll(Long packageId,int size,int page){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Feedback> feedbacks;
    }
}
