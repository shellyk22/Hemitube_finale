// server/controllers/video.js

const videoService = require('../services/video');
const VidFile = require('../models/vidFile');

const createVideo = async (req, res) => {
    try {
        const { title, publisher, comments, file } = req.body;

        if (!title) {
            return res.status(400).json({ errors: ['Title is required'] });
        }

        // Process publisher and comments to handle null or valid ObjectId values
        const processedPublisher = Array.isArray(publisher) && publisher.length > 0 && publisher[0] !== 'null' ? publisher : [null];
        const processedComments = Array.isArray(comments) && comments.length > 0 && comments[0] !== 'null' ? comments : [];

        // Create file attachment if provided
        let fileDoc = null;
        if (file && file.name && file.data) {
            const { name, data } = file;
            fileDoc = new VidFile({ name, data, attachedVid: null });
            await fileDoc.save();
        }

        // Create video and attach file
        const newVideo = await videoService.createVideo(
            title,
            processedPublisher,
            processedComments,
            fileDoc ? fileDoc._id : null
        );

        // Update file with the video reference
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


const getVideos = async (_, res) => {
    try {
        const videos = await videoService.getVideos();
        res.status(200).json(videos);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const getVideo = async (req, res) => {
    try {
        const video = await videoService.getVideoById(req.params.id);
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
        const updatedVideo = await videoService.updateVideo(req.params.id, req.body);
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
        const deletedVideo = await videoService.deleteVideo(req.params.id);
        if (!deletedVideo) {
            return res.status(404).json({ errors: ['Video not found'] });
        }
        res.status(200).json({ message: 'Video and associated file deleted successfully' });
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

// Add a comment to a video
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

module.exports = { createVideo, getVideos, getVideo, updateVideo, deleteVideo, addCommentToVideo };
