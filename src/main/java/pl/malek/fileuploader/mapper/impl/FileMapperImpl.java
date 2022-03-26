package pl.malek.fileuploader.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.fileuploader.dto.File;
import pl.malek.fileuploader.entity.FileEntity;
import pl.malek.fileuploader.mapper.FileMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FileMapperImpl implements FileMapper {

    private final ObjectMapper objectMapper;

    @Override
    public File mapToFile(MultipartFile file) throws IOException {
        return File.builder()
                .filename(file.getOriginalFilename())
                .size(file.getSize())
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
    }

    @Override
    public List<FileEntity> mapToFileEntities(List<File> files) {
        return Arrays.stream(objectMapper.convertValue(files, FileEntity[].class)).collect(Collectors.toList());
    }
}
