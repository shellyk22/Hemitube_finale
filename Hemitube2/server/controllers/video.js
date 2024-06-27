// server/controllers/video.js

const videoService = require('../services/video');
const VidFile = require('../models/vidFile');
const jwt = require('jsonwebtoken'); 

const key = "secret key foo foo foo bar";
const createVideo = async (req, res) => {
    try {
        const { title, publisher, comments, file } = req.body;

        if (!title) {
            return res.status(400).json({ errors: ['Title is required'] });
        }

        const processedPublisher = Array.isArray(publisher) && publisher.length > 0 && publisher[0] !== 'null' ? publisher : [null];
        const processedComments = Array.isArray(comments) && comments.length > 0 && comments[0] !== 'null' ? comments : [];

        let fileDoc = null;
        if (file && file.name && file.data) {
            const { name, data } = file;
            fileDoc = new VidFile({ name, data, attachedVid: null });
            await fileDoc.save();
        }

        const newVideo = await videoService.createVideo(
            title,
            processedPublisher,
            processedComments,
            fileDoc ? fileDoc._id : null
        );

        if (fileDoc) {
            fileDoc.attachedVid = newVideo._id;
            await fileDoc.save();
        }

        res.status(201).json(newVideo);
    } catch (error) {
        console.log("Detailed Error: ", error); // Log the detailed error
        res.status(500).json({ errors: [error.message] });
    }
};

const getVideos = async (req, res) => {
    try {
        const userId = req.params.id;
        const videos = await videoService.getVideos({ publisher: userId });
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
        const { videoId, content } = req.body;
        if (!videoId || !content) {
            return res.status(400).json({ errors: ['Video ID and content are required'] });
        }
        const newComment = await videoService.addCommentToVideo(videoId, content);
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

const getVideosByUserId = async (req, res) => {
    try {
        const userId = req.params.id;
        const videos = await videoService.getVideosByUserId(userId);
        res.status(200).json(videos);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};
const getCommentsByVideoId = async (req, res) => {
    try {
        const comments = await videoService.getCommentsByVideoId(req.params.videoId);
        res.status(200).json(comments);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const deleteVideosByUserId = async (req, res) => {
    try {
        const deletedVideo = await videoService.deleteVideosByUserId(req.params.userId);
        if (!deletedVideo) {
            return res.status(404).json({ errors: ['Video not found'] });
        }
        res.status(200).json({ message: 'Video and associated file deleted successfully' });
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

module.exports = { createVideo, getVideos, getVideo, updateVideo, deleteVideo, 
    addCommentToVideo, isLoggedIn , getVideosByUserId, getCommentsByVideoId, deleteVideosByUserId};