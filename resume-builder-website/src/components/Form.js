import React, { useState, useEffect } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import { Box, Divider } from '@material-ui/core';
import Typography from '@material-ui/core/Typography';
import template1 from "../Assets/template1.png"
import template2 from "../Assets/template2.png"
import template3 from "../Assets/template3.png"
import '../App.css';

const Form = ({ onSubmit }) => {
  const [name, setName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [linkedIn, setLinkedIn] = useState('');
  const [jobTitle, setJobTitle] = useState('');
  const [careerObjective, setCareerObjective] = useState('');
  const [skills, setSkills] = useState(['']);
  const [education, setEducation] = useState([{ school_name: '', passing_year: '', description: '' }]);
  const [experience, setExperience] = useState([{ company_name: '', passing_year: '', responsibilities: '' }]);
  const [achievements, setAchievements] = useState([{ field: '', awards: '' }]);
  const [templateId, setTemplateId] = useState("1"); //default template 1

  useEffect(() => {
    // Check if data exists in local storage
    const storedData = localStorage.getItem('resumeData');
    if (storedData) {
      const parsedData = JSON.parse(storedData);
      const {
        personal_information,
        job_title,
        career_objective,
        skills,
        education,
        experience,
        achievements,
      } = parsedData;
      const {
        name,
        last_name,
        email_address,
        phone_number,
        linkedin_url,
      } = personal_information;

      setName(name || '');
      setLastName(last_name || '');
      setEmail(email_address || '');
      setPhoneNumber(phone_number || '');
      setLinkedIn(linkedin_url || '');
      setJobTitle(job_title || '');
      setCareerObjective(career_objective || '');
      setSkills(skills || ['']);
      setEducation(education || [{ school_name: '', passing_year: '', description: '' }]);
      setExperience(experience || [{ company_name: '', passing_year: '', responsibilities: '' }]);
      setAchievements(achievements || [{ field: '', awards: '' }]);
    }
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    // Validate form fields
    if (
      !name ||
      !lastName ||
      !email ||
      !phoneNumber ||
      !linkedIn ||
      !jobTitle ||
      !careerObjective ||
      skills.length === 0 ||
      education.length === 0 ||
      experience.length === 0 ||
      achievements.length === 0
    ) {
      alert('Please fill in all required fields');
      return;
    }
    // Prepare the data
    const data = {
      template_id: templateId,
      personal_information: {
        name: name,
        last_name: lastName,
        email_address: email,
        phone_number: phoneNumber,
        linkedin_url: linkedIn,
      },
      job_title: jobTitle,
      career_objective: careerObjective,
      skills: skills.filter((skill) => skill.trim() !== ''),
      education: education.filter((edu) => edu.school_name.trim() !== '' && edu.passing_year.trim() !== ''),
      experience: experience.filter((exp) => exp.company_name.trim() !== '' && exp.passing_year.trim() !== ''),
      achievements: achievements.filter((ach) => ach.field.trim() !== '' && ach.awards.trim() !== ''),
    };
    // Call the submit function from the parent component
    onSubmit(data);
  };

  const handleAddSkill = () => {
    setSkills([...skills, '']);
  };

  const handleSkillChange = (index, value) => {
    const updatedSkills = [...skills];
    updatedSkills[index] = value;
    setSkills(updatedSkills);
  };

  const handleAddEducation = () => {
    setEducation([...education, { school_name: '', passing_year: '', description: '' }]);
  };

  const handleEducationChange = (index, field, value) => {
    const updatedEducation = [...education];
    updatedEducation[index][field] = value;
    setEducation(updatedEducation);
  };

  const handleAddExperience = () => {
    setExperience([...experience, { company_name: '', passing_year: '', responsibilities: '' }]);
  };

  const handleExperienceChange = (index, field, value) => {
    const updatedExperience = [...experience];
    updatedExperience[index][field] = value;
    setExperience(updatedExperience);
  };

  const handleAddAchievement = () => {
    setAchievements([...achievements, { field: '', awards: '' }]);
  };

  const handleAchievementChange = (index, field, value) => {
    const updatedAchievements = [...achievements];
    updatedAchievements[index][field] = value;
    setAchievements(updatedAchievements);
  };
  const handleButtonClick = (templateId) => {
    setTemplateId(templateId);
    console.log(templateId);
  };
  const handleRemoveEducation = (index) => {
    const updatedEducation = [...education];
    updatedEducation.splice(index, 1);
    setEducation(updatedEducation);
  };

  const handleRemoveSkill = (index) => {
    const updatedSkills = [...skills];
    updatedSkills.splice(index, 1);
    setSkills(updatedSkills);
  };

  const handleRemoveExperience = (index) => {
    const updatedExperience = [...experience];
    updatedExperience.splice(index, 1);
    setExperience(updatedExperience);
  };

  const handleRemoveAchievement = (index) => {
    const updatedAchievements = [...achievements];
    updatedAchievements.splice(index, 1);
    setAchievements(updatedAchievements);
  };
  return (
    <>

      <Box display="flex" flexDirection="column" alignItems="center" >
        <h1>
          Choose Template
        </h1>
        <Divider />
        <Box display="flex" justifyContent="space-between" width={360} mt={2}>
          <Box
            style={{
              width: '120px',
              cursor: 'pointer',
              marginRight: '10px',
              border: templateId === '1' ? '4px solid black' : '1px solid black',
            }}
            onClick={() => handleButtonClick('1')}
          >
            <img src={template1} alt="Template 1" style={{ width: '100%' }} />
          </Box>
          <Box
            style={{
              width: '120px',
              cursor: 'pointer',
              marginRight: '10px',
              border: templateId === '2' ? '4px solid black' : '1px solid black',
            }}
            onClick={() => handleButtonClick('2')}
          >
            <img src={template2} alt="Template 2" style={{ width: '100%' }} />
          </Box>
          <Box
            style={{
              width: '120px',
              cursor: 'pointer',
              border: templateId === '3' ? '4px solid black' : '1px solid black',
            }}
            onClick={() => handleButtonClick('3')}
          >
            <img src={template3} alt="Template 3" style={{ width: '100%' }} />
          </Box>
        </Box>
        <Divider />
        <h1>
          Fill The Following Details
        </h1>
      </Box>
      <form className='form-container' onSubmit={handleSubmit} style={{ margin: '1px 15%', padding: '5%', border: '2px solid black' }}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              variant="outlined"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Last Name"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              variant="outlined"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              variant="outlined"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Phone Number"
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
              variant="outlined"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="LinkedIn"
              value={linkedIn}
              onChange={(e) => setLinkedIn(e.target.value)}
              variant="outlined"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Job Title"
              value={jobTitle}
              onChange={(e) => setJobTitle(e.target.value)}
              variant="outlined"
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              label="Career Objective"
              value={careerObjective}
              onChange={(e) => setCareerObjective(e.target.value)}
              variant="outlined"
              multiline
              rows={4}
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12}>
            <Typography variant="h6" gutterBottom>
              Skills:
            </Typography>
            {skills.map((skill, index) => (
              <Box key={index} display="flex" alignItems="center">
                <TextField
                  style={{ margin: '10px 0px' }}
                  label="Skill"
                  value={skill}
                  onChange={(e) => handleSkillChange(index, e.target.value)}
                  variant="outlined"
                  fullWidth
                  required
                />
                <Button
                  variant="contained"
                  type="button"
                  onClick={() => handleRemoveSkill(index)}
                  style={{ marginLeft: '10px' }}
                >
                  Remove
                </Button>
              </Box>
            ))}
            <Button variant="contained" type="button" onClick={handleAddSkill}>
              Add Skill
            </Button>
          </Grid>

          <Grid item xs={12}>
            <Typography variant="h6" gutterBottom>
              Education:
            </Typography>
            {education.map((edu, index) => (
              <Grid container spacing={2} key={index}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="School Name"
                    value={edu.school_name}
                    onChange={(e) => handleEducationChange(index, 'school_name', e.target.value)}
                    variant="outlined"
                    fullWidth
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="Year"
                    value={edu.passing_year}
                    onChange={(e) => handleEducationChange(index, 'passing_year', e.target.value)}
                    variant="outlined"
                    fullWidth
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    label="Description"
                    value={edu.description}
                    onChange={(e) => handleEducationChange(index, 'description', e.target.value)}
                    variant="outlined"
                    multiline
                    rows={4}
                    fullWidth
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <Button
                    variant="contained"
                    type="button"
                    onClick={() => handleRemoveEducation(index)}
                    style={{ margin: '10px 0px' }}
                  >
                    Remove
                  </Button>
                </Grid>
              </Grid>
            ))}
            <Button variant="contained" type="button" onClick={handleAddEducation} style={{ margin: '10px 0px' }}>
              Add Education
            </Button>
          </Grid>

          <Grid item xs={12}>
            <Typography variant="h6" gutterBottom>
              Experience:
            </Typography>
            {experience.map((exp, index) => (
              <Grid container spacing={2} key={index}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="Company Name"
                    value={exp.company_name}
                    onChange={(e) => handleExperienceChange(index, 'company_name', e.target.value)}
                    variant="outlined"
                    fullWidth
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="Year"
                    value={exp.passing_year}
                    onChange={(e) => handleExperienceChange(index, 'passing_year', e.target.value)}
                    variant="outlined"
                    fullWidth
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    label="Responsibilities"
                    value={exp.responsibilities}
                    onChange={(e) => handleExperienceChange(index, 'responsibilities', e.target.value)}
                    variant="outlined"
                    multiline
                    rows={4}
                    fullWidth
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <Button
                    variant="contained"
                    type="button"
                    onClick={() => handleRemoveExperience(index)}
                    style={{ margin: '10px 0px' }}
                  >
                    Remove
                  </Button>
                </Grid>
              </Grid>
            ))}
            <Button
              variant="contained"
              type="button"
              onClick={handleAddExperience}
              style={{ margin: '10px 0px' }}
            >
              Add Experience
            </Button>
          </Grid>

          <Grid item xs={12}>
            <Typography variant="h6" gutterBottom>
              Achievements:
            </Typography>
            {achievements.map((ach, index) => (
              <Grid container spacing={2} key={index}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="Field"
                    value={ach.field}
                    onChange={(e) => handleAchievementChange(index, 'field', e.target.value)}
                    variant="outlined"
                    fullWidth
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="Awards"
                    value={ach.awards}
                    onChange={(e) => handleAchievementChange(index, 'awards', e.target.value)}
                    variant="outlined"
                    fullWidth
                    required
                  />
                </Grid>
                <Grid item xs={12}>
                  <Button
                    variant="contained"
                    type="button"
                    onClick={() => handleRemoveAchievement(index)}
                    style={{ margin: '10px 0px' }}
                  >
                    Remove
                  </Button>
                </Grid>
              </Grid>
            ))}
            <Button
              variant="contained"
              type="button"
              onClick={handleAddAchievement}
              style={{ margin: '10px 0px' }}
            >
              Add Achievement
            </Button>
          </Grid>
          <Grid item xs={12} >
            <Button variant="contained" color="primary" type="submit">
              Submit
            </Button>
          </Grid>
        </Grid>
      </form>
    </>

  );
};

export default Form;
