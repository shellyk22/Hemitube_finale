const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const CommentSchema = new Schema({ //MessageSchema
    publiced: { //created
        type: String,
        default: () => new Date().toISOString()
    },
    author: { //sender
        type: Schema.Types.ObjectId,
        ref: 'User',
        default: null
    },
    content: String
});

module.exports = mongoose.model('Comment', CommentSchema);