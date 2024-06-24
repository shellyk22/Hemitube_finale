// server/models/vidFile.js

const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const VidFileSchema = new Schema({
    name: {
        type: String,
        default: "Name Unknown"
    },
    attachedVid: {
        type: Schema.Types.ObjectId,
        ref: 'Video',
        default: null
    },
    data: {
        type: String,
        default: null
    }
});

module.exports = mongoose.model('VidFile', VidFileSchema);
