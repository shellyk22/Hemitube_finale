const Video = require('../models/video');
const VidFile = require('../models/vidFile');
const Comment = require('../models/comment');
const User = require('../models/video');

const createVideo = async (title, publisher = [null], comments = [], file = null) => {
    try {
        const video = new Video({ publisher, title, comments, file });
        await video.save();
        return video.toObject();
    } catch (error) {
        console.log("Error creating video: ", error);
        throw new Error('Could not create video');
    }
};

const getVideos = async () => {
    try {
        return await Video.find({})
            .populate('publisher comments file'); // Populate comments and file
    } catch (error) {
        console.log("Error fetching videos: ", error);
        throw new Error('Could not fetch videos');
    }
};

const getVideoById = async (id) => {
    try {
        return await Video.findById(id)
            .populate('publisher comments file'); // Populate comments and file
    } catch (error) {
        console.log("Error fetching video by ID: ", error);
        throw new Error('Could not fetch video');
    }
};

const updateVideo = async (id, updateData) => {
    try {
        return await Video.findByIdAndUpdate(id, updateData, { new: true })
            .populate('publisher comments file'); // Populate comments and file
    } catch (error) {
        console.log("Error updating video: ", error);
        throw new Error('Could not update video');
    }
};

const deleteVideo = async (id) => {
    try {
        const video = await Video.findById(id);
        if (video && video.file) {
            await VidFile.findByIdAndDelete(video.file);
        }
        return await Video.findByIdAndDelete(id);
    } catch (error) {
        console.log("Error deleting video: ", error);
        throw new Error('Could not delete video');
    }
};



const addCommentToVideo = async (videoId, commentContent) => {
    try {
        const comment = new Comment({ content: commentContent, author: null });
        await comment.save();

        const video = await Video.findById(videoId);
        if (!video) {
            throw new Error('Video not found');
        }
        video.comments.push(comment._id);
        await video.save();

        return comment.toObject();
    } catch (error) {
        console.log("Error adding comment to video: ", error);
        throw new Error('Could not add comment to video');
    }
};

const getVideosByUserId = async (userId) => {
    try {
        return await Video.find({ publisher: userId })
            .populate('publisher comments file'); // Populate comments and file
    } catch (error) {
        console.log("Error fetching videos by user ID: ", error);
        throw new Error('Could not fetch videos by user ID');
    }
};

const getCommentsByVideoId = async (videoId) => {
    try {
        const video = await Video.findById(videoId).populate('comments');
        if (!video) {
            throw new Error('Video not found');
        }
        return video.comments;
    } catch (error) {
        console.log("Error fetching comments by video ID: ", error);
        throw new Error('Could not fetch comments by video ID');
    }
};

const deleteVideosByUserId = async (userId) => {
    try {
            await Video.deleteMany({ author: userId});
        return {message : `Videos by user ${userId} deleted successfully`};
    } catch (error) {
        console.log("Error deleting videos by user Id: ", error);
        throw new Error('Could not delete videos by user ID');
    }
};

module.exports = { createVideo, getVideos, getVideoById, updateVideo, deleteVideo, 
    addCommentToVideo, getVideosByUserId, getCommentsByVideoId, getCommentsByVideoId,deleteVideosByUserId };