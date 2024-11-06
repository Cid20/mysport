package sds_java.mysport.entity;

import jakarta.persistence.*;
import lombok.*;
import sds_java.mysport.entity.enums.ContentType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filepath;
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    private Long size;

    private String fileType;


    public File(String originalFilename, String string, String contentType, long size) {
    }

    public void update(String fileName, String filepath, String contentType) {
        this.fileName = fileName;
        this.filepath = filepath;
        this.contentType = ContentType.valueOf(contentType.toUpperCase()); // contentType ni Enumga o'zgartirish
    }
}
