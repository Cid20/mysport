package sds_java.mysport.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private Object data;
    private ResponseError error;

    public ApiResponse(Object data) {
        this.data = data;
    }

    public ApiResponse(ResponseError error) {this.error = error;}
}