package pl.malek.fileuploader.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.fileuploader.service.FileValidatorService;

@Slf4j
@Service
public class FileValidatorsServiceImpl implements FileValidatorService {

    @Override
    public boolean validateFileTypes(MultipartFile[] files) {
        log.info("Validation type file");
        for (MultipartFile file : files) {
            if (file.getContentType() != null) {
                if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
                    log.error("Wrong type of file: " + file.getContentType());
                    return false;
                }
            } else {
                log.error("Content type is null");
                return false;
            }
        }
        log.info("Validation complete");
        return true;
    }

}
