package pl.malek.fileuploader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.malek.fileuploader.dto.File;
import pl.malek.fileuploader.entity.FileEntity;
import pl.malek.fileuploader.exceptions.WrongFileTypeException;
import pl.malek.fileuploader.mapper.FileMapper;
import pl.malek.fileuploader.repository.FileRepository;
import pl.malek.fileuploader.service.impl.FileUploadServiceImpl;
import pl.malek.fileuploader.service.impl.FileValidatorsServiceImpl;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileUploadServiceTest {

    @Mock
    private FileRepository fileRepository;

    @Mock
    private FileMapper fileMapper;

    @Mock
    private FileValidatorsServiceImpl fileValidatorsService;

    @InjectMocks
    private FileUploadServiceImpl fileUploadService;

    @Captor
    private ArgumentCaptor<MultipartFile> multipartFileCaptor;

    @Captor
    private ArgumentCaptor<List<File>> fileListCaptor;

    @Captor
    private ArgumentCaptor<List<FileEntity>> entityFileListCaptor;

    @Test
    void shouldMapMultipartFilesToFileDto() throws IOException, WrongFileTypeException {
        MultipartFile[] files = new MultipartFile[]{new MockMultipartFile("test", new byte[]{})};

        when(fileValidatorsService.validateFileTypes(files)).thenReturn(true);

        fileUploadService.uploadFiles(files);

        verify(fileMapper).mapToFile(multipartFileCaptor.capture());

        assertEquals(multipartFileCaptor.getValue().getName(), "test");
    }

    @Test
    void shouldMapFilesToEntities() throws IOException, WrongFileTypeException {
        MultipartFile[] files = new MultipartFile[]{new MockMultipartFile("test", new byte[]{})};

        when(fileValidatorsService.validateFileTypes(files)).thenReturn(true);

        fileUploadService.uploadFiles(files);

        verify(fileMapper).mapToFileEntities(fileListCaptor.capture());

        assertEquals(fileListCaptor.getValue().size(), 1);
    }

    @Test
    void shouldSaveFiles() throws IOException, WrongFileTypeException {
        MultipartFile[] files = new MultipartFile[]{new MockMultipartFile("test", new byte[]{})};

        when(fileValidatorsService.validateFileTypes(files)).thenReturn(true);
        when(fileMapper.mapToFileEntities(anyList())).thenReturn(List.of(FileEntity.builder().build()));

        fileUploadService.uploadFiles(files);

        verify(fileRepository).saveAll(entityFileListCaptor.capture());

        assertEquals(entityFileListCaptor.getValue().size(), 1);
    }

    @Test
    void shouldThrowExceptionAboutWrongFileType() throws WrongFileTypeException, IOException {
        MultipartFile[] files = new MultipartFile[]{new MockMultipartFile("test", new byte[]{})};

        when(fileValidatorsService.validateFileTypes(files)).thenReturn(false);

        assertThrows(WrongFileTypeException.class, () -> fileUploadService.uploadFiles(files));
    }
}
