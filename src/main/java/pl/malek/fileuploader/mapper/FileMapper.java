package pl.malek.fileuploader.mapper;

import org.springframework.web.multipart.MultipartFile;
import pl.malek.fileuploader.dto.File;
import pl.malek.fileuploader.entity.FileEntity;

import java.io.IOException;
import java.util.List;

public interface FileMapper {

    File mapToFile(MultipartFile file) throws IOException;

    List<FileEntity> mapToFileEntities(List<File> files);

}
