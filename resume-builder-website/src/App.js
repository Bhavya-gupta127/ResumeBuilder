import React, { useState } from 'react';
import Form from './components/Form';
import axios from 'axios';
import { Box, Button, Dialog, DialogTitle, DialogContent, DialogActions ,Typography} from '@material-ui/core';
import './App.css'; 

const App = () => {
  const [resumeData, setResumeData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [pdfUrl, setPdfUrl] = useState(null);
  const [showPreview, setShowPreview] = useState(false);

  const handleSubmitForm = async (data) => {
    setLoading(true);
    // Save data to local storage
    localStorage.setItem('resumeData', JSON.stringify(data));
    // Make API call
    try {
      const response = await axios.post('http://localhost:8080/resume', data, {
        responseType: 'arraybuffer',
      });
      const blob = new Blob([response.data], { type: 'application/pdf' });
      const url = URL.createObjectURL(blob);
      setPdfUrl(url);
      setResumeData(data);
      setShowPreview(true);
    } catch (error) {
      console.error('Error:', error);
      console.error('json:', JSON.stringify(data));
      alert('An error occurred while generating the resume.\n'+error);
    }
    setLoading(false);
  };

  const handleDownload = () => {
    if (!pdfUrl) {
      return;
    }
    const link = document.createElement('a');
    link.href = pdfUrl;
    link.download = 'resume.pdf';
    link.click();
  };

  const handleClosePreview = () => {
    setShowPreview(false);
  };

  return (
    <div className="app-container">
      <Form onSubmit={handleSubmitForm} />
      {loading && (
  <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100px' }}>
    <Typography variant="h6" color="textSecondary">Loading...</Typography>
  </div>
)}
      {resumeData && (
    <Box display="flex" justifyContent="center" mt={3}>
    {pdfUrl && (
      <Box>
        <Button variant="contained" color="primary" onClick={handleDownload} style={{ marginRight: '10px' }}>
          Download PDF
        </Button>

        <Button variant="contained" color="primary" onClick={() => setShowPreview(true)}>
          Preview PDF
        </Button>
      </Box>
    )}
  </Box>
      )}
      <Dialog open={showPreview} onClose={handleClosePreview}>
        <DialogTitle>Resume Preview</DialogTitle>
        <DialogContent>
          <iframe src={pdfUrl} width="100%" height="500px" title="Resume Preview" />
        </DialogContent>
        <DialogActions>
          <Button variant="contained" onClick={handleDownload} style={{ marginRight: '10px' }}>
            Download PDF
          </Button>
          <Button variant="contained" onClick={handleClosePreview}>
            Close
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default App;
