package sds_java.mysport.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sds_java.mysport.entity.File;
import sds_java.mysport.entity.enums.Events;
import sds_java.mysport.exception.NotFoundException;
import sds_java.mysport.payload.ApiResponse;
import sds_java.mysport.payload.ResponseError;
import sds_java.mysport.payload.res.ResFile;
import sds_java.mysport.repository.FileRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;
@Transactional
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository videoFileRepository;
    private final ActionService actionService;
    private final NotificationService notificationService;
    private static final Path root = Paths.get("src/main/resources");

    public ApiResponse saveFile(MultipartFile file) {
        String directory = determineFileType(file);
        if (directory == null) {
            return new ApiResponse(ResponseError.NOTFOUND("Fayl yuklash uchun papka"));
        }

        long timestamp = System.currentTimeMillis();
        Path filePath = root.resolve(directory + "/" + timestamp + "-" + file.getOriginalFilename());

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            File videoFile = new File(file.getOriginalFilename(), filePath.toString(), file.getContentType(), file.getSize());
            File savedFile = videoFileRepository.save(videoFile);
            actionService.saveAction(null,videoFile, Events.UPLOADED);
            return new ApiResponse(savedFile.getId());
        } catch (IOException e) {
            throw new NotFoundException(new ApiResponse(ResponseError.NOTFOUND(e.getMessage())));
        }
    }

    public ResFile loadFileAsResource(Long id) {
        try {
            Optional<File> fileOptional = videoFileRepository.findById(id);
            if (fileOptional.isPresent()) {
                File videoFile = fileOptional.get();
                Resource resource = new UrlResource(Paths.get(videoFile.getFilepath()).toUri());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(String.valueOf(videoFile.getContentType())));
                headers.setContentLength(videoFile.getSize());

                return new ResFile(videoFile.getFileName(), resource, headers);
            }
            return null;
        } catch (IOException e) {
            throw new NotFoundException(new ApiResponse(ResponseError.DEFAULT_ERROR(e.getMessage())));
        }
    }

    public ApiResponse updateFile(Long id, MultipartFile file) {
        try {
            Optional<File> existingFile = videoFileRepository.findById(id);
            if (existingFile.isPresent()) {
                File videoFile = existingFile.get();
                Files.deleteIfExists(Paths.get(videoFile.getFilepath()));

                String directory = determineFileType(file);
                if (directory == null) {
                    return new ApiResponse(ResponseError.NOTFOUND("Fayl yuklash uchun papka"));
                }

                long timestamp = System.currentTimeMillis();
                Path newFilePath = root.resolve(directory + "/" + timestamp + "-" + file.getOriginalFilename());
                Files.createDirectories(newFilePath.getParent());
                Files.copy(file.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);
                videoFile.update(file.getOriginalFilename(), newFilePath.toString(), file.getContentType());
                File updatedFile = videoFileRepository.save(videoFile);
                actionService.saveAction(null,updatedFile,Events.UPDATED);
                return new ApiResponse(updatedFile.getId());
            } else {
                throw new NotFoundException(new ApiResponse(ResponseError.NOTFOUND("File")));
            }
        } catch (IOException e) {
            throw new NotFoundException(new ApiResponse(ResponseError.DEFAULT_ERROR("Fileni o'qishda xatolik")));
        }
    }

    public ApiResponse deleteFile(Long id) {
        try {
            Optional<File> fileOptional = videoFileRepository.findById(id);
            if (fileOptional.isPresent()) {
                File videoFile = fileOptional.get();
                Files.deleteIfExists(Paths.get(videoFile.getFilepath()));
                videoFileRepository.delete(videoFile);
                actionService.saveAction(null,videoFile,Events.REMOVED);
                return new ApiResponse("Successfully deleted");
            } else {
                throw new IOException("File not found");
            }
        } catch (IOException e) {
            throw new NotFoundException(new ApiResponse(ResponseError.DEFAULT_ERROR("Fileni o'qishda xatolik")));
        }
    }

    private String determineFileType(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename != null && isImage(filename)) {
            return "img";
        } else if (filename != null && isDocument(filename)) {
            return "files";
        }
        return null;
    }

    private boolean isImage(String filename) {
        return filename.endsWith(".png") || filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".webp")
                || filename.endsWith(".PNG") || filename.endsWith(".JPG") || filename.endsWith(".JPEG") || filename.endsWith(".WEBP");
    }

    private boolean isDocument(String filename) {
        return filename.endsWith(".pdf") || filename.endsWith(".docx") || filename.endsWith(".pptx") || filename.endsWith(".zip")
                || filename.endsWith(".PDF") || filename.endsWith(".DOCX") || filename.endsWith(".PPTX") || filename.endsWith(".ZIP");
    }
}
