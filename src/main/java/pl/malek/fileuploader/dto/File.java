package pl.malek.fileuploader.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class File {

    private String filename;

    private long size;

    private boolean uploadStatus;

    private String type;

    private byte[] data;

}
