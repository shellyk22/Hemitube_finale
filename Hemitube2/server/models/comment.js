const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const CommentSchema = new Schema({ //MessageSchema
    published: { //created
        type: Date,
        //default: () => new Date().toISOString()
        default: Date.now
    },
    author: { //sender
        type: Schema.Types.ObjectId,
        ref: 'User',
        default: null
    },
    content: String
});

module.exports = mongoose.model('Comment', CommentSchema);