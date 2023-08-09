package com.example.resume.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class AdobeGeneratePdfServiceTest {

    @Test
    public void testMain_ValidJson_GeneratesResumePdf() throws IOException {
        // Arrange
        String inputJson = "{\"template_id\":\"1\",\"personal_information\":{\"name\":\"Bhavya\",\"last_name\":\"Gupta\",\"email_address\":\"bhavyagupta127@gmail.com\",\"phone_number\":\"+91 99xx14xx99\",\"linkedin_url\":\"https://www.linkedin.com\"},\"job_title\":\"Software Development Engineer\",\"career_objective\":\"this is career_objective\",\"skills\":[\"Strong interpersonal\",\"communication skills\",\"Leadership\",\"Poised under pressure\"],\"education\":[{\"school_name\":\"LNM\",\"passing_year\":\"201X-201Y\",\"description\":\"this is desc of edu\"},{\"school_name\":\"College\",\"passing_year\":\"203X-203Y\",\"description\":\"All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc\"}],\"experience\":[{\"company_name\":\"Adobe\",\"passing_year\":\"201X-201Y\",\"responsibilities\":\"It is a long established fact that a reader will be distracted by the readable content. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod\"}],\"achievements\":[{\"field\":\"Academics\",\"awards\":\"Lorem ipsum dolor sit amet\"},{\"field\":\"Sports\",\"awards\":\"consectetuer adipiscing elit\"}]}";
        String expectedOutputFile = "src/main/resources/output/resume.pdf";

        // Delete the output file if it already exists
        Files.deleteIfExists(Paths.get(expectedOutputFile));

        // Act
        AdobeGeneratePdfService.main(inputJson);

        // Assert
        Assertions.assertTrue(Files.exists(Paths.get(expectedOutputFile)), "Output PDF file should be generated");
    }
}