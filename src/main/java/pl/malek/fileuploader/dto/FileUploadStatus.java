package pl.malek.fileuploader.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FileUploadStatus {

    private List<File> files;

}
