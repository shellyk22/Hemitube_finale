const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const VideoSchema = new Schema({
    publisher: [ //users
        {
            type: Schema.Types.ObjectId,
            ref: 'User',
            required: true
        }
    ],
    comments: [ //messeges
        {
            type: Schema.Types.ObjectId,
            ref: 'Comment',
            required: true
        }
    ],
    title: [ 
        {
            type: String ,
            required: true
        }
    ],
});

module.exports = mongoose.model('Video', VideoSchema);