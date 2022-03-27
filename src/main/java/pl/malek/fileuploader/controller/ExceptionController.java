package pl.malek.fileuploader.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.malek.fileuploader.dto.ErrorCode;
import pl.malek.fileuploader.dto.ExceptionResponse;
import pl.malek.fileuploader.exceptions.WrongFileTypeException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(WrongFileTypeException.class)
    public ResponseEntity<?> wrongFileTypeHandler(WrongFileTypeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionResponse.builder()
                        .messages(List.of(e.getMessage()))
                        .errorCode(ErrorCode.WRONG_FILE_TYPE)
                        .errorTime(LocalDateTime.now()).build());
    }

}
