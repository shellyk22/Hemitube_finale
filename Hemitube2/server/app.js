// server/app.js
const express = require('express');
const path = require('path');
const http = require('http');
const bodyParser = require('body-parser');
const cors = require('cors');
const customEnv = require('custom-env');
const mongoose = require('mongoose');

const app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json({ limit: '4mb' }));
app.use(express.json());
app.use(cors());

const environment = process.env.NODE_ENV || 'default';
customEnv.env(environment, './config');

mongoose.connect(process.env.CONNECTION_STRING, {
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(() => console.log('MongoDB connected successfully'))
  .catch(err => console.log('MongoDB connection error:', err));

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, './index.html'));
});

const userRoutes = require('./routes/user');
app.use('/api/users', userRoutes);

const commentRoutes = require('./routes/comments');
app.use('/api/users/:id/videos', commentRoutes);

const videoRoutes = require('./routes/videos');
app.use('/api/users', videoRoutes);

const tokenRoutes = require('./routes/token');
app.use('/api/token', tokenRoutes);

const server = http.createServer(app);

const PORT = process.env.PORT || 5000;
server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT} in ${environment} mode`);
});
