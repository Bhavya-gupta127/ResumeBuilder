package com.example.resume.controller;

import com.example.resume.exception.UnauthorizedException;
import com.example.resume.service.AdobeGeneratePdfService;
import com.example.resume.service.ResumeService;
import com.example.resume.exception.BadRequestException;
import com.example.resume.exception.InternalServerErrorException;
import com.example.resume.exception.TemplateNotFoundException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ResumeController {

    private ResumeService resumeService;
    private AdobeGeneratePdfService adobeGeneratePdfService;

    public void setResumeService(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    public void setAdobeGeneratePdfService(AdobeGeneratePdfService adobeGeneratePdfService) {
        this.adobeGeneratePdfService = adobeGeneratePdfService;
    }

    // Endpoint to generate a resume in PDF format
    @PostMapping(value = "/resume", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<FileSystemResource> generateResume(@RequestBody String json) throws UnauthorizedException {

        // Create a ResumeService object to validate and process the JSON data
        ResumeService jsonFormater = new ResumeService();

        // Check if the JSON data is valid
        if (!jsonFormater.isJsonValid(json)) {
            throw new BadRequestException("Bad Request: Invalid request body");
        }
        // Check if the template is available
        if (!jsonFormater.isTemplateAvailable(json)) {
            throw new TemplateNotFoundException("Template not found");
        }
        // Generate the PDF using AdobeGeneratePdfService
        AdobeGeneratePdfService.main(json);

        final String PDF_FILE_PATH = "src/main/resources/output/resume.pdf";

        try {
            // Return the generated PDF file as a response
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new FileSystemResource(PDF_FILE_PATH));
        } catch (Exception e) {
            // For any other exceptions, return Internal Server Error
            throw new InternalServerErrorException("Internal Server Error: Failed to generate the PDF" + e);
        }
    }
}
