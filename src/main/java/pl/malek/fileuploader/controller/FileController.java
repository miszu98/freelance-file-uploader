package pl.malek.fileuploader.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.fileuploader.exceptions.WrongFileTypeException;
import pl.malek.fileuploader.service.FileUploadService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/files")
public class FileController {

    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestBody MultipartFile[] files)
            throws IOException, WrongFileTypeException {
        log.info("Uploading files...");
        return ResponseEntity.status(HttpStatus.OK).body(fileUploadService.uploadFiles(files));
    }

}
