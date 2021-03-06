package pl.malek.fileuploader.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.fileuploader.dto.File;
import pl.malek.fileuploader.dto.FileUploadStatus;
import pl.malek.fileuploader.entity.FileEntity;
import pl.malek.fileuploader.exceptions.WrongFileTypeException;
import pl.malek.fileuploader.mapper.FileMapper;
import pl.malek.fileuploader.repository.FileRepository;
import pl.malek.fileuploader.service.FileUploadService;
import pl.malek.fileuploader.service.FileValidatorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final FileRepository fileRepository;

    private final FileMapper fileMapper;

    private final FileValidatorService fileValidatorService;

    @Override
    public FileUploadStatus uploadFiles(MultipartFile[] files) throws IOException, WrongFileTypeException {
        log.info("Trying to save files to database");

        if (fileValidatorService.validateFileTypes(files)) {
            List<File> mappedFiles = new ArrayList<>();

            for (MultipartFile file : files) {
                File mappedFile = fileMapper.mapToFile(file);
                mappedFiles.add(mappedFile);
            }

            List<FileEntity> fileEntities = fileMapper.mapToFileEntities(mappedFiles);
            fileRepository.saveAll(fileEntities);

            return FileUploadStatus.builder().files(mappedFiles).build();
        } else {
            throw new WrongFileTypeException("Wrong format of uploaded files");
        }

    }

}
