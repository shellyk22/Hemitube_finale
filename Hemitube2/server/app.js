// server/app.js

const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const path = require('path');
const commentController = require('./controllers/comment'); // Update the path as needed

const app = express();

// Middleware
app.use(bodyParser.json());

// Database Connection
mongoose.connect('mongodb://localhost:27017/yourDatabaseName', { // replace yourDatabaseName with your database name
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
  .then(() => console.log('MongoDB connected successfully'))
  .catch(err => console.log('MongoDB connection error:', err));

// Routes
app.get('/api/comments', commentController.getComments);
app.get('/api/comments/:id', commentController.getComment);
app.post('/api/comments', commentController.createComment);
app.put('/api/comments/:id', commentController.updateComment);
app.delete('/api/comments/:id', commentController.deleteComment);

// Serve the HTML file for testing
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'index.html'));
});

// Start the server
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
