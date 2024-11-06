package sds_java.mysport.payload.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResFile {
    private String fileName;
    private Resource resource;
    private HttpHeaders headers;
}