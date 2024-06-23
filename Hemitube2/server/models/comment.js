// server/models/comment.js

const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const CommentSchema = new Schema({
    published: {
        type: Date,
        default: Date.now
    },
    // Make author optional
    author: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        default: null  // Set a default value or leave it as null
    },
    content: {
        type: String,
        required: true
    }
});

module.exports = mongoose.model('Comment', CommentSchema);
