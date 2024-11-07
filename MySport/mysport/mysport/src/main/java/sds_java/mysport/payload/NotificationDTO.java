package sds_java.mysport.payload;

import java.time.LocalDateTime;

public record NotificationDTO(
        Long id,
        String title,
        String content,
        boolean read,
        LocalDateTime create,
        Long userId,
        Long fileId
) {
}
