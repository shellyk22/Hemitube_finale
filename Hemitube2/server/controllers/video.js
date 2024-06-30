
const Video = require('../models/video.js');

const videoService = require('../services/video');
const VidFile = require('../models/vidFile.js');
const jwt = require('jsonwebtoken'); 

const key = "secret key foo foo foo bar";
const createVideo = async (req, res) => {
    try {

        console.log("Create Video function - request log :")
        console.log(req.body)
        console.log(req.files.file[0])
        console.log(req.files.thumbnail[0])

        var title = req.body.title; 
        var description = req.body.description;
        var publisher = req.body.publisher
        var file = req.files.file;
        var thumb = req.files.thumbnail;

        /*if (!title) {
            return res.status(400).json({ errors: ['Title is required'] });
        }*/

        let fileDoc = null;
       /* if (file) {
            fileDoc = new VidFile({ name: file.filename, data: file.path, attachedVid: null });
            await fileDoc.save();
        }*/

        const newVideo = await videoService.createVideo(
            title,
            description,
            publisher, 
            req.files.file[0].filename,
            req.files.file[0].path,
            req.files.thumbnail[0].filename,
            req.files.thumbnail[0].path
        );

       /* if (fileDoc) {
            fileDoc.attachedVid = newVideo._id;
            await fileDoc.save();
        }*/

        res.status(201).json(newVideo);
    } catch (error) {
        console.log("Detailed Error: ", error);
        res.status(500).json({ errors: [error.message] });
    }
};

const getVideos = async (req, res) => {
    try {

        console.log("Create Video function - request log :")
        const videos = await videoService.getVideos();
        res.status(200).json(videos);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const getVideo = async (req, res) => {
    try {
        const video = await videoService.getVideoById(req.params.pid);
        if (!video) {
            return res.status(404).json({ errors: ['Video not found'] });
        }
        res.status(200).json(video);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const updateVideo = async (req, res) => {
    try {
        const updatedVideo = await videoService.updateVideo(req.params.pid, req.body);
        if (!updatedVideo) {
            return res.status(404).json({ errors: ['Video not found'] });
        }
        res.status(200).json(updatedVideo);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const deleteVideo = async (req, res) => {
    try {
        const deletedVideo = await videoService.deleteVideo(req.params.pid);
        if (!deletedVideo) {
            return res.status(404).json({ errors: ['Video not found'] });
        }
        res.status(200).json({ message: 'Video and associated file deleted successfully' });
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const addCommentToVideo = async (req, res) => {
    try {
        const {content, userID, username} = req.body;
        const {vid } = req.params;
        if (!vid || !content) {
            return res.status(400).json({ errors: ['Video ID and content are required'] });
        }
        const newComment = await videoService.addCommentToVideo(vid, content, userID, username);
        res.status(201).json(newComment);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const isLoggedIn = (req, res, next) => {
    if (req.headers.authorization) {
        const token = req.headers.authorization.split(" ")[1];
        try {
            const decoded = jwt.verify(token, key);
            req.user = decoded; // Attach decoded token data to req.user
            return next();
        } catch (err) {
            return res.status(401).send("Invalid Token");
        }
    } else {
        return res.status(403).send('Token required');
    }
};

const getVideosByUsername = async (req, res) => {
    try {
        const username = req.params.id;
        console.log(username);
        const videos = await videoService.getVideosByUsername(username);
        res.status(200).json(videos);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const deleteVideosByUsername = async (req, res) => {
    try {
        const deletedVideo = await videoService.deleteVideosByUsername(req.params.id);
        if (!deletedVideo) {
            return res.status(404).json({ errors: ['Video not found'] });
        }
        res.status(200).json({ message: 'Video and associated file deleted successfully' });
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const getAllCommentsByVideoId = async (req, res) => {
    try {
        const comments = await videoService.getAllCommentsByVideoId(req.params.vid);
        res.status(200).json(comments);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const getTopVids = async (req, res) => {
    try {
        const topVideos = await videoService.getTopVids();
        res.status(200).json(topVideos);
    } catch (error) {
        console.error('Error getting top videos:', error);
        res.status(500).json({ message: 'Failed to get top videos' });
    }
};

module.exports = { createVideo, getVideos, getVideo, updateVideo, deleteVideo,  getTopVids,
    addCommentToVideo, isLoggedIn , getVideosByUsername, getAllCommentsByVideoId, deleteVideosByUsername};