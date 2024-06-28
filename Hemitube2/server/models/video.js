const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const VideoSchema = new Schema({
    publisher: [
        {
            type: Schema.Types.ObjectId,
            ref: 'User',
            required: true
            //default: null // Default to null
        }
    ],
    comments: [
        {
            type: Schema.Types.ObjectId,
            ref: 'Comment',
            required: true
        }
    ],
    title: {
        type: String,
        required: true
    },
    descreption: {
        type: String,
        required: true
    },
    views: {
        type: String,
        required: true
    },
    uploadDate: {
        type: Date,
        //required: true
    },
    thumbnail: {
        type: String,
        required: true
    },
    file: { // Single reference to a VidFile document
        type: Schema.Types.ObjectId,
        ref: 'VidFile',
        default: null
    }
});

module.exports = mongoose.model('Video', VideoSchema);
