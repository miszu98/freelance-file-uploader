package pl.malek.fileuploader.service;

import org.springframework.web.multipart.MultipartFile;
import pl.malek.fileuploader.dto.FileUploadStatus;

import java.io.IOException;

public interface FileUploadService {

    FileUploadStatus uploadFiles(MultipartFile[] files) throws IOException;

}
