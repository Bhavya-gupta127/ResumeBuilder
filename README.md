
## Resume Builder API Solution:

### Solution Approach:
- Developed a Resume Builder API using Java Spring Boot.
- Integrated the Adobe Document Generation API for creating PDF resumes.
- Allowed users to merge their data with pre-built templates for quick resume generation.
- Implemented data validation, template management, and thorough testing.

### Key Components:
- `ResumeBuilderApplication.java`, `ResumeController.java`, `AdobeGeneratePdfService.java`, `ResumeService.java`, etc.
- `pom.xml`: Maven project configuration file.
- `pdfservices-api-credentials.json`: Adobe Document Generation API credentials.
- Detailed unit tests for each service class.

### API Endpoints:
- `POST /resume`: Generates a resume in PDF format based on provided JSON data.
- Handles various error responses (400, 401, 404, 500).

### Prerequisites and Setup:
- Required JDK, Maven, and Adobe Document Generation API credentials.

### Testing:
- Included unit tests for service classes.
- Tests ensure proper functionality and error handling.

### Conclusion:
- API offers a user-friendly way to create professional resumes with pre-built templates.
- Well-organized, modular, and tested code for reliability.

## Resume Builder Website Solution (Optional):

### Solution Approach:
- Developed a React-based resume builder website.
- Captures user input for personal information, education, experience, skills, and achievements.
- Generates PDF resumes by making API calls to the backend server.

### Key Components:
- `App.js`, `components/Form.js`.
- Utilizes Axios for API requests and Material-UI components for UI design.
- Uses local storage to persist entered data.

### Installation and Usage:
- `npm install` to install dependencies.
- `npm start` to start the development server.
- Access the website at `http://localhost:3000`.

### Dependencies:
- React, Axios, Material-UI components/icons.

### Additional Notes:
- Data persistence through local storage.

### Conclusion:
- A React-based website offering a user-friendly interface for resume creation.
- Interacts with the backend API for PDF resume generation.
