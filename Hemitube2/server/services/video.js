const Video = require('../models/video');
const VidFile = require('../models/vidFile.js');
const Comment = require('../models/comment');
const User = require('../models/user');

const createVideo = async (title, description, publisher, file_name,file_data,thumbnail_name,thumbnail_data) => {
    try {
        const comments = [
            {
                userID: '667dc7a2e69ca93a73f1c1b0',
                text: 'This is an amazing video!'
            },
            {
                userID: '667e8256ca31bc259e62f65c',
                text: 'Great content, keep it up!'
            },
            {
                userID: '667e8256ca31bc259e62f65c',
                text: 'I found this video very informative.'
            },
            {
                userID: '667dc7a2e69ca93a73f1c1b0',
                text: 'Can you make more videos on this topic?'
            },
            {
                userID: '667e77c8ca31bc259e62f658',
                text: 'Loved the editing and presentation!'
            }
        ];
        
        console.log(comments);
        const video = new Video({ title, description, publisher, comments, file_name,file_data ,thumbnail_name,thumbnail_data});
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
           // .populate('publisher comments file'); // Populate comments and file
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



const addCommentToVideo = async (videoId, commentContent, userId) => {
    try {
        const user = await User.findById(userId);
        const comment = new Comment({ content: commentContent, author: user?.nickName });
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
        await Video.deleteMany({ publisher: userId});
        return {message : `Videos by user ${userId} deleted successfully`};
    } catch (error) {
        console.log("Error deleting videos by user Id: ", error);
        throw new Error('Could not delete videos by user ID');
    }
};

module.exports = { createVideo, getVideos, getVideoById, updateVideo, deleteVideo, 
    addCommentToVideo, getVideosByUserId, getCommentsByVideoId,deleteVideosByUserId };