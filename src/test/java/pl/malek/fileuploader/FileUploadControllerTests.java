package pl.malek.fileuploader;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import pl.malek.fileuploader.service.impl.FileUploadServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileUploadControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileUploadServiceImpl fileUploadService;

    @Test
    void shouldReturnOkStatusWhileUploadingFiles() throws Exception {
        mockMvc.perform(multipart("/v1/files/upload")
                        .file(new MockMultipartFile("file", "test.txt",
                                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes())))
                .andExpect(status().isOk());
    }

}
