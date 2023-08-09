package com.example.resume.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResumeService {

    // Reformat the JSON string to a desired format
    static String reformatJson(String jsonString) {
        try {
            // Parse the original JSON string
            JSONObject obj = new JSONObject(jsonString);
            JSONObject personalInfo = obj.getJSONObject("personal_information");

            // Extract personal information fields
            String name = personalInfo.getString("name");
            String lastName = personalInfo.getString("last_name");
            String email = personalInfo.getString("email_address");
            String phoneNumber = personalInfo.getString("phone_number");
            String linkedinUrl = personalInfo.getString("linkedin_url");

            // Create a new JSONObject with reformatted data
            JSONObject obj2 = new JSONObject();
            obj2.put("Name", name);
            obj2.put("LastName", lastName);
            obj2.put("EmailAddress", email);
            obj2.put("PhoneNumber", phoneNumber);
            obj2.put("LinkedIn", "<a href="+linkedinUrl+">linkedIn</a>");
            obj2.put("JobTitle", obj.getString("job_title"));
            obj2.put("Summary", obj.getString("career_objective"));

            // Extract and reformat skills array
            JSONArray skills = obj.getJSONArray("skills");
            obj2.put("Skills", skills);

            // Extract and reformat education array
            JSONArray education = obj.getJSONArray("education");
            JSONArray educationArr = new JSONArray();
            for (int i = 0; i < education.length(); i++) {
                JSONObject educationObj = education.getJSONObject(i);
                JSONObject educationItem = new JSONObject();
                educationItem.put("SchoolName", educationObj.getString("school_name"));
                educationItem.put("Year", educationObj.getString("passing_year"));
                educationItem.put("Description", educationObj.getString("description"));
                educationArr.put(educationItem);
            }
            obj2.put("Education", educationArr);

            // Extract and reformat experience array
            JSONArray experience = obj.getJSONArray("experience");
            JSONArray experienceArr = new JSONArray();
            for (int i = 0; i < experience.length(); i++) {
                JSONObject experienceObj = experience.getJSONObject(i);
                JSONObject experienceItem = new JSONObject();
                experienceItem.put("CompanyName", experienceObj.getString("company_name"));
                experienceItem.put("Year", experienceObj.getString("passing_year"));
                experienceItem.put("Description", experienceObj.getString("responsibilities"));
                experienceArr.put(experienceItem);
            }
            obj2.put("Experience", experienceArr);

            // Extract and reformat achievements array
            JSONArray achievements = obj.getJSONArray("achievements");
            JSONArray achievementsArr = new JSONArray();
            for (int i = 0; i < achievements.length(); i++) {
                JSONObject achievementObj = achievements.getJSONObject(i);
                JSONObject achievementItem = new JSONObject();
                achievementItem.put("Type", achievementObj.getString("field"));
                achievementItem.put("Description", achievementObj.getString("awards"));
                achievementsArr.put(achievementItem);
            }
            obj2.put("Achievements", achievementsArr);

            return obj2.toString();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // Check if the JSON data is valid
    public boolean isJsonValid(String json) {
        try {
            // Parse the JSON string
            JSONObject obj = new JSONObject(json);

            // Validate template ID
            String templateId = obj.getString("template_id");
            if (templateId == null || templateId.isEmpty()) {
                System.out.println("Invalid template ID");
                return false;
            }

            // Validate personal information
            JSONObject personalInfo = obj.getJSONObject("personal_information");
            if (personalInfo == null || personalInfo.length() == 0) {
                System.out.println("Invalid personal information");
                return false;
            }

            // Validate required fields in personal information
            String name = personalInfo.getString("name");
            String lastName = personalInfo.getString("last_name");
            String emailAddress = personalInfo.getString("email_address");
            String phoneNumber = personalInfo.getString("phone_number");
            String linkedinUrl = personalInfo.getString("linkedin_url");
            if (name == null || name.isEmpty()
                    || lastName == null || lastName.isEmpty()
                    || emailAddress == null || emailAddress.isEmpty()
                    || phoneNumber == null || phoneNumber.isEmpty()
                    || linkedinUrl == null || linkedinUrl.isEmpty()) {
                System.out.println("Invalid personal information fields");
                return false;
            }

            // Validate job title and career objective
            String jobTitle = obj.getString("job_title");
            String careerObjective = obj.getString("career_objective");
            if (jobTitle == null || jobTitle.isEmpty()
                    || careerObjective == null || careerObjective.isEmpty()) {
                System.out.println("Invalid job title or career objective");
                return false;
            }

            // Validate skills array
            JSONArray skills = obj.getJSONArray("skills");
            if (skills == null || skills.length() == 0) {
                System.out.println("Invalid skills");
                return false;
            }

            // Validate education array
            JSONArray education = obj.getJSONArray("education");
            if (education == null || education.length() == 0) {
                System.out.println("Invalid education");
                return false;
            }

            // Validate experience array
            JSONArray experience = obj.getJSONArray("experience");
            if (experience == null || experience.length() == 0) {
                System.out.println("Invalid experience");
                return false;
            }

            // Validate achievements array
            JSONArray achievements = obj.getJSONArray("achievements");
            if (achievements == null || achievements.length() == 0) {
                System.out.println("Invalid achievements");
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Invalid JSON format");
            return false;
        }
    }

    // Check if the template is available
    public boolean isTemplateAvailable(String json) {
        JSONObject obj = new JSONObject(json);
        String template_id = obj.getString("template_id");
        return template_id.equals("1") || template_id.equals("2") || template_id.equals("3");
    }
}
