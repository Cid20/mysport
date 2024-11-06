package sds_java.mysport.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import sds_java.mysport.service.FileService;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.res.ResFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        ApiResponse response = fileService.saveFile(file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ResFile> downloadFile(@PathVariable Long id) {
        ResFile resFile = fileService.loadFileAsResource(id);
        if (resFile != null && resFile.getResource() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, resFile.getHeaders().getContentType().toString())
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resFile.getHeaders().getContentLength()))
                    .body(resFile);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        ApiResponse response = fileService.updateFile(id, file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteFile(@PathVariable Long id) {
        ApiResponse response = fileService.deleteFile(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
