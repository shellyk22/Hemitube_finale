
// importing express
const express = require('express');
const path = require('path'); // Make sure to import the path module
const app = express();

// importing http.
const http = require("http");
// use of socket.io
// use body-parser to phrase the body of requests as json/URL-encoded.
const bodyParser = require('body-parser');

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json({limit: '4mb'}));

app.use(express.json());

// The cors middleware allows cross-origin requests,
// enabling the API to be accessed from different domains or ports.
const cors = require('cors');
app.use(cors());

//load environment variables from a configuration file.
const customEnv = require('custom-env');
customEnv.env(process.env.NODE_ENV, './config');

//importing mongoose library to connect to a MongoDB database.
const mongoose = require('mongoose');
mongoose.connect(process.env.CONNECTION_STRING, {
    useNewUrlParser: true,
    useUnifiedTopology: true
});
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, './index.html'));
  });

// Route setup.
const users = require('./routes/user');
app.use('/api/users', users);

const messages = require('./routes/comments');
app.use('/api/comments', messages);

const chats = require('./routes/videos');
app.use('/api/videos', chats);

const token = require('./routes/token');
const {env} = require("custom-env");
app.use('/api/token', token);

// initialize http server.
const server = http.createServer(app)

// // server start.
server.listen(process.env.PORT);
console.log("YASSSSSSSSSSS on:", process.env.PORT)