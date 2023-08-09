# PapyrusNebulae 2023 Document Cloud Hackathon: Round 2 - Resume Builder API

This repository contains my solution for Round 2 of the PapyrusNebulae 2023 Document Cloud Hackathon.
The goal of this round is to develop a Resume Builder API using the Adobe Document Generation API for dynamically creating PDF resumes.

## Solution Approach

My solution approach involved developing a Resume Builder API using Java Spring Boot and integrating the Adobe Document Generation API. The API allowed users to quickly create professional resumes by merging their data with pre-built templates, reducing manual effort. Data validation, template management, and thorough testing were implemented for reliability.
## Table of Contents

1. [Project Structure](#project-structure)
2. [Prerequisites](#prerequisites)
3. [Setup Instructions](#setup-instructions)
4. [Running the API](#running-the-api)
5. [API Endpoints](#api-endpoints)
6. [Error Handling](#error-handling)
7. [Unit Tests](#unit-tests)
8. [Conclusion](#conclusion)
8. [Submission](#Submission)
8. [About me](#Aboutme)



## Project Structure <a name="project-structure"></a>
The project consists of the following files and directories:

- `ResumeBuilderApplication.java`: The main class of the Spring Boot application.
- `ResumeController.java`: The controller class that handles the API endpoints for resume generation.
- `RestExceptionHandler.java`: The exception handler class for handling different types of exceptions.
- `AdobeGeneratePdfService.java`: The service class responsible for generating the resume in PDF format using the Adobe Document Generation API.
- `ResumeService.java`: The service class that performs validation and formatting of the input JSON data.
- `ResumeControllerTest.java`: Unit tests for the `ResumeController` class.
- `ResumeServiceTest.java`: Unit tests for the `ResumeService` class.
- `AdobeGeneratePdfServiceTest.java`: Unit tests for the `AdobeGeneratePdfService` class.
- `pom.xml`: The Maven project configuration file.
- `README.md`: The README file with setup and usage instructions.

## Prerequisites <a name="#prerequisites"></a>
Before setting up and running the API, make sure you have the following prerequisites:

1. Java Development Kit (JDK) 17 or higher.
2. Apache Maven 3.8.x or higher.
3. <a href="https://developer.adobe.com/document-services/apis/doc-generation/">Adobe Document Generation API</a> credentials (client ID and client secret) to generate PDF resumes. 

## Setup Instructions <a name="setup-instructions"></a>
To set up the Resume Builder API, follow these steps:

1. Clone the project repository or download the source code to your local machine.
2. Update the `pdfservices-api-credentials.json` file in the project directory with your Adobe credentials.
   (For sake of submission I am attaching my credentials as demo).

## Running the API <a name="running-the-api"></a>
To run the Resume Builder API, follow these steps:

1. Open a terminal or command prompt.
2. Navigate to the project directory.
3. Build the project using Maven by running the following command:
```
mvn clean install
```
4. Once the build is successful, start the API server by running the following command:
```
mvn spring-boot:run
```
5. The API will start running on `http://localhost:8080`.

## API Endpoints <a name="api-endpoints"></a>
The Resume Builder API provides the following endpoint:

### Generate Resume [POST /resume]
Generates a resume in PDF format based on the provided JSON data.

- Request Body: JSON data representing the resume information. 
- Note that all fields of json are required.
- Template id should be a string equal to either "1" or "2" or "3".
    - Example:
  ```json
  {
      "template_id" : "1",
      "personal_information" : {
        "name": "Lorem",
        "last_name": "ipsum",
        "email_address": "ipsum@adobe.com",
        "phone_number": "+91 99xx14xx99",
        "linkedin_url": "https://www.linkedin.com"
      },
      "job_title": "Software Development Engine er",
      "career_objective" : "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper.",
      "skills": [
        "Strong interpersonal",
        "communication skills", 
        "Leadership",
        "Poised under pressure"
      ],
      "education": [
        {
          "school_name": "School",
          "passing_year": "201X-201Y",
          "description" : "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don'\''t look even slightly believable."
        },
        {
          "school_name": "College",
          "passing_year": "203X-203Y",
          "description" : "All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc"
        }
      ],
      "experience": [
        {
          "company_name": "Adobe",
          "passing_year": "201X-201Y",
          "responsibilities" : "It is a long established fact that a reader will be distracted by the readable content. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod"
        }
      ],
      "achievements": [
        {
          "field" : "Academics",
          "awards" : "Lorem ipsum dolor sit amet"
        },
        {
          "field" : "Sports",
          "awards" : "consectetuer adipiscing elit"
        }
      ]
    }
  ```

- Response: The generated PDF file.
- Content-Type: application/pdf
    

The API returns the generated resume in PDF format.


---

## Error Handling <a name="error-handling"></a>
The API handles various types of exceptions and returns appropriate error responses. The possible error responses include:
- `400 Bad Request`: The request was invalid or missing required parameters.
- `401 Unauthorized`: The request requires authentication or authorization.
- `404 Template not found`: The specified template ID was not found.
- `500 Internal Server Error`: An internal server error occurred.
  The API will respond with the generated PDF resume file.



## Testing <a name="unit-tests"></a>

The project includes unit tests to ensure the functionality of the API. You can run the tests using the following command:

```
mvn test
```



Here are some unit tests for the components related to the resume generation functionality.


## ResumeServiceTest

### testIsJsonValid_ValidJson_ReturnsTrue
This test verifies that the `isJsonValid` method of the `ResumeService` class returns `true` when a valid JSON string is provided.

### testIsJsonValid_InvalidJson_ReturnsFalse
This test verifies that the `isJsonValid` method of the `ResumeService` class returns `false` when an invalid JSON string is provided.

### testIsTemplateAvailable_ValidTemplateId_ReturnsTrue
This test ensures that the `isTemplateAvailable` method of the `ResumeService` class returns `true` when a valid template ID is provided.

### testIsTemplateAvailable_InvalidTemplateId_ReturnsFalse
This test ensures that the `isTemplateAvailable` method of the `ResumeService` class returns `false` when an invalid template ID is provided.

### testReformatJson_ValidJson_ReturnsFormattedJson
This test verifies that the `reformatJson` method of the `ResumeService` class correctly reformats the provided JSON string and returns the expected formatted JSON.

## ResumeControllerTest

### testGenerateResume_ValidJson_ReturnsPdfResponse
This test validates that the `generateResume` method of the `ResumeController` class returns an HTTP 200 OK response with the content type set to PDF when a valid JSON string is provided.

### testGenerateResume_TemplateNotAvailable_ThrowsTemplateNotFoundException
This test ensures that the `generateResume` method of the `ResumeController` class throws a `TemplateNotFoundException` when an invalid template ID is provided in the JSON string.

---

These unit tests provide comprehensive coverage for the resume generation service, validating its behavior in different scenarios. The tests ensure that the service properly handles valid and invalid JSON inputs, checks the availability of templates, and generates the expected PDF responses.

Note: These tests utilize Mockito to mock dependencies such as the `ResumeService` and `AdobeGeneratePdfService` for isolated unit testing.

## Conclusion <a name="conclusion"></a>
The Resume Builder API provides a convenient way to generate professional resumes by utilizing pre-built templates and user-provided data. By following the setup instructions and utilizing the provided API endpoint, users can easily create resumes in PDF format. The API is well-organized, modularized, and tested, ensuring its reliability and accuracy.

## Submission <a name="Submission"></a>

To submit my solution for the hackathon, I am providing a link containing the code: https://drive.google.com/drive/folders/1bJYearaPDYrNvfvn53GxP3LpCAk5vRAt?usp=sharing

I have ensured that the code is properly documented and organized for better understanding. If you have any questions or need further clarification, please feel free to reach out.

Thank you for considering my submission.

## About Me <a name="Aboutme"></a>

I'm Bhavya Gupta (20ucs052@lnmiit.ac.in), a passionate and versatile developer with expertise in full-stack web development. I love creating seamless user experiences and solving complex problems with technology. Excited to learn and deliver innovative solutions in this hackathon. Let's build something incredible together!
