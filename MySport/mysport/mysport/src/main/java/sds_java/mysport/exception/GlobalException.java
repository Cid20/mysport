package sds_java.mysport.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.ResponseError;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> accessDeniedExceptionHandler(AccessDeniedException e) {
        return ResponseEntity.ok(new ApiResponse(ResponseError.ACCESS_DENIED()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.ok(e.getApiResponse());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.ok(new ApiResponse(ResponseError.VALIDATION_FAILED(message)));
    }
}