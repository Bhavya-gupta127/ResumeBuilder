package com.example.resume.controller;
import com.example.resume.exception.TemplateNotFoundException;
import com.example.resume.service.AdobeGeneratePdfService;
import com.example.resume.service.ResumeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ResumeControllerTest {

    private ResumeController resumeController;

    @Mock
    private ResumeService resumeService;

    @Mock
    private AdobeGeneratePdfService adobeGeneratePdfService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resumeController = new ResumeController();
        resumeController.setResumeService(resumeService);
        resumeController.setAdobeGeneratePdfService(adobeGeneratePdfService);
    }

    @Test
    void testGenerateResume_ValidJson_ReturnsPdfResponse() {
        String json = "{\"template_id\":\"3\",\"personal_information\":{\"name\":\"Bhavya\",\"last_name\":\"asdf\",\"email_address\":\"ipsum@adobe.com\",\"phone_number\":\"+91 99xx14xx99\",\"linkedin_url\":\"https://www.linkedin.com\"},\"job_title\":\"Software Development Engineer\",\"career_objective\":\"Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper.\",\"skills\":[\"Strong interpersonal\",\"communication skills\",\"Leadership\",\"Poised under pressure\"],\"education\":[{\"school_name\":\"School\",\"passing_year\":\"201X-201Y\",\"description\":\"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.\"},{\"school_name\":\"College\",\"passing_year\":\"203X-203Y\",\"description\":\"All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc\"}],\"experience\":[{\"company_name\":\"Adobe\",\"passing_year\":\"201X-201Y\",\"responsibilities\":\"It is a long established fact that a reader will be distracted by the readable content. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod\"}],\"achievements\":[{\"field\":\"Academics\",\"awards\":\"Lorem ipsum dolor sit amet\"},{\"field\":\"Sports\",\"awards\":\"consectetuer adipiscing elit\"}]}";

        // Stub the behavior of the ResumeService and AdobeGeneratePdfService

        when(resumeService.isJsonValid(json)).thenReturn(true);
        when(resumeService.isTemplateAvailable(json)).thenReturn(true);
        doNothing().when(adobeGeneratePdfService).main(json);

        ResponseEntity<FileSystemResource> response = resumeController.generateResume(json);

        // Verify the response

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_PDF, response.getHeaders().getContentType());
    }

    @Test
    void testGenerateResume_TemplateNotAvailable_ThrowsTemplateNotFoundException() {
        // Arrange
        String inputJson = "{\"template_id\":\"10\",\"personal_information\":{\"name\":\"John\",\"last_name\":\"Doe\",\"email_address\":\"john.doe@example.com\",\"phone_number\":\"+123456789\",\"linkedin_url\":\"https://www.linkedin.com/in/johndoe\"},\"job_title\":\"Software Engineer\",\"career_objective\":\"Lorem ipsum dolor sit amet\",\"skills\":[\"Java\",\"Spring Boot\",\"RESTful APIs\"],\"education\":[{\"school_name\":\"University\",\"passing_year\":\"2010-2014\",\"description\":\"Bachelor's Degree in Computer Science\"}],\"experience\":[{\"company_name\":\"ABC Inc.\",\"passing_year\":\"2014-2018\",\"responsibilities\":\"Developed and maintained web applications\"}],\"achievements\":[{\"field\":\"Academics\",\"awards\":\"First Class Honors\"}]}";

        ResumeService resumeServiceMock = Mockito.mock(ResumeService.class);
        Mockito.when(resumeServiceMock.isJsonValid(inputJson)).thenReturn(true);
        Mockito.when(resumeServiceMock.isTemplateAvailable(inputJson)).thenReturn(false);

        AdobeGeneratePdfService adobeGeneratePdfServiceMock = Mockito.mock(AdobeGeneratePdfService.class);

        ResumeController resumeController = new ResumeController();
        resumeController.setResumeService(resumeServiceMock);
        resumeController.setAdobeGeneratePdfService(adobeGeneratePdfServiceMock);

        // Act & Assert
        Assertions.assertThrows(TemplateNotFoundException.class, () -> {
            resumeController.generateResume(inputJson);
        });
    }

}
