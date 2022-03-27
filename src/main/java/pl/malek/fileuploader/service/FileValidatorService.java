package pl.malek.fileuploader.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileValidatorService {

    boolean validateFileTypes(MultipartFile[] files);

}
