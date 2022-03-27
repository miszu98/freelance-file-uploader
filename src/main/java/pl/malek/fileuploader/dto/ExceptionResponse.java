package pl.malek.fileuploader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ExceptionResponse {

    private LocalDateTime errorTime;

    private ErrorCode errorCode;

    private List<String> messages;

}
