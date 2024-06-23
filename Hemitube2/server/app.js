const express = require('express');
var app = express();
const bodyParser = require('body-parser');
const cors = require('cors');


const customEnv = require('custom-env')
customEnv.env(process.env.NODE_ENV, './config');
console.log(process.env.CONNECTION_STRING)
console.log(process.env.PORT)



const mongoose = require('mongoose');
mongoose.connect(process.env.CONNECTION_STRING, 
    {
        useNewUrlParser : true,
        useUnifiedTopology: true

});


app.use(express.static('public'))
app.use(cors());
app.use(bodyParser.urlencoded({ extended: true}));
app.use(express.json());

const users = require('./routes/user');
app.use('/users', users);




app.listen(process.env.PORT);