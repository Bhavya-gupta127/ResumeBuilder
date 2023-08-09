package com.example.resume.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResumeServiceTest {


    @Test
    public void testIsJsonValid_ValidJson_ReturnsTrue() {
        // Arrange
        String validJson = "{ \"template_id\": \"1\", \"personal_information\": { \"name\": \"John\", \"last_name\": \"Doe\", \"email_address\": \"john.doe@example.com\", \"phone_number\": \"+123456789\", \"linkedin_url\": \"https://www.linkedin.com/in/johndoe/\" }, \"job_title\": \"Software Developer\", \"career_objective\": \"Lorem ipsum dolor sit amet\", \"skills\": [\"Java\", \"Spring Boot\", \"REST API\"], \"education\": [{ \"school_name\": \"University\", \"passing_year\": \"2020\", \"description\": \"Bachelor's Degree in Computer Science\" }], \"experience\": [{ \"company_name\": \"ABC Corp\", \"passing_year\": \"2018-2021\", \"responsibilities\": \"Developed and maintained web applications\" }], \"achievements\": [{ \"field\": \"Academics\", \"awards\": \"Dean's List\" }] }";
        ResumeService resumeService = new ResumeService();

        // Act
        boolean isValid = resumeService.isJsonValid(validJson);

        // Assert
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testIsJsonValid_InvalidJson_ReturnsFalse() {
        // Arrange
        String invalidJson = "Invalid JSON";
        ResumeService resumeService = new ResumeService();

        // Act
        boolean isValid = resumeService.isJsonValid(invalidJson);

        // Assert
        Assertions.assertFalse(isValid);
    }
    @Test
    public void testIsTemplateAvailable_ValidTemplateId_ReturnsTrue() {
        // Arrange
        String jsonWithTemplate1 = "{ \"template_id\": \"1\", \"personal_information\": { \"name\": \"John\", \"last_name\": \"Doe\", \"email_address\": \"john.doe@example.com\", \"phone_number\": \"+123456789\", \"linkedin_url\": \"https://www.linkedin.com/in/johndoe/\" } }";
        ResumeService resumeService = new ResumeService();

        // Act
        boolean isTemplateAvailable = resumeService.isTemplateAvailable(jsonWithTemplate1);

        // Assert
        Assertions.assertTrue(isTemplateAvailable);
    }



    @Test
    public void testReformatJson_ValidJson_ReturnsFormattedJson() {
        // Arrange
        String inputJson = "{\"template_id\":\"3\",\"personal_information\":{\"name\":\"Bhavya\",\"last_name\":\"asdf\",\"email_address\":\"ipsum@adobe.com\",\"phone_number\":\"+91 99xx14xx99\",\"linkedin_url\":\"https://www.linkedin.com\"},\"job_title\":\"Software Development Engineer\",\"career_objective\":\"Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper.\",\"skills\":[\"Strong interpersonal\",\"communication skills\",\"Leadership\",\"Poised under pressure\"],\"education\":[{\"school_name\":\"School\",\"passing_year\":\"201X-201Y\",\"description\":\"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.\"},{\"school_name\":\"College\",\"passing_year\":\"203X-203Y\",\"description\":\"All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc\"}],\"experience\":[{\"company_name\":\"Adobe\",\"passing_year\":\"201X-201Y\",\"responsibilities\":\"It is a long established fact that a reader will be distracted by the readable content. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod\"}],\"achievements\":[{\"field\":\"Academics\",\"awards\":\"Lorem ipsum dolor sit amet\"},{\"field\":\"Sports\",\"awards\":\"consectetuer adipiscing elit\"}]}";
        String expectedFormattedJson = "{\"LinkedIn\":\"<a href=https:\\/\\/www.linkedin.com>linkedIn<\\/a>\",\"Experience\":[{\"CompanyName\":\"Adobe\",\"Description\":\"It is a long established fact that a reader will be distracted by the readable content. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod\",\"Year\":\"201X-201Y\"}],\"Skills\":[\"Strong interpersonal\",\"communication skills\",\"Leadership\",\"Poised under pressure\"],\"Education\":[{\"Description\":\"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.\",\"Year\":\"201X-201Y\",\"SchoolName\":\"School\"},{\"Description\":\"All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc\",\"Year\":\"203X-203Y\",\"SchoolName\":\"College\"}],\"Achievements\":[{\"Type\":\"Academics\",\"Description\":\"Lorem ipsum dolor sit amet\"},{\"Type\":\"Sports\",\"Description\":\"consectetuer adipiscing elit\"}],\"PhoneNumber\":\"+91 99xx14xx99\",\"Summary\":\"Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper.\",\"LastName\":\"asdf\",\"JobTitle\":\"Software Development Engineer\",\"EmailAddress\":\"ipsum@adobe.com\",\"Name\":\"Bhavya\"}";
        ResumeService resumeService = new ResumeService();
        // Act
        String formattedJson = resumeService.reformatJson(inputJson);

        // Assert
        Assertions.assertEquals(expectedFormattedJson, formattedJson);
    }

    @Test
    public void testIsTemplateAvailable_InvalidTemplateId_ReturnsFalse() {
        // Arrange
        String jsonWithInvalidTemplateId = "{ \"template_id\": \"4\", \"personal_information\": { \"name\": \"John\", \"last_name\": \"Doe\", \"email_address\": \"john.doe@example.com\", \"phone_number\": \"+123456789\", \"linkedin_url\": \"https://www.linkedin.com/in/johndoe/\" } }";
        ResumeService resumeService = new ResumeService();

        // Act
        boolean isTemplateAvailable = resumeService.isTemplateAvailable(jsonWithInvalidTemplateId);

        // Assert
        Assertions.assertFalse(isTemplateAvailable);
    }
}