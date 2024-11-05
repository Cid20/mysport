package sds_java.mysport.exception;

import lombok.Getter;
import lombok.Setter;
import sds_java.mysport.payload.ApiResponse;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

    private ApiResponse apiResponse;
    public NotFoundException(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

}