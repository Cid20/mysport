package sds_java.mysport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sds_java.mysport.entity.Category;
import sds_java.mysport.entity.Feedback;
import sds_java.mysport.entity.User;
import sds_java.mysport.entity.enums.Events;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.req.ReqFeedback;
import sds_java.mysport.repository.CategoryRepository;
import sds_java.mysport.repository.FeedbackRepository;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final NotificationService notificationService;
    private final CategoryRepository categoryRepository;
    private ActionService actionService;

    public ApiResponse saveFeedback(User user, ReqFeedback reqFeedback){
        Category category = categoryRepository.findById(reqFeedback.getCategoryId()).orElse(null);
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
                reqFeedback.getCategoryId(),
                false
        );
        actionService.saveAction(user,null, Events.COMMENTED,category);
        return new ApiResponse("Succes");
    }

    public ApiResponse getAll(Long packageId, int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Feedback> feedbacks = feedbackRepository.findAllByCategoryId(packageId, pageRequest);
        return new ApiResponse(feedbacks.getContent());
    }

}
