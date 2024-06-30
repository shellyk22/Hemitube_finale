const Video = require('../models/video');
const VidFile = require('../models/vidFile.js');
const Comment = require('../models/comment');
const User = require('../models/user');

const createVideo = async (title, description, publisher, file_name, file_data, thumbnail_name, thumbnail_data) => {
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
        const video = new Video({ title, description, publisher, comments, file_name, file_data, thumbnail_name, thumbnail_data });
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
            //.populate('publisher comments file'); // Populate comments and file
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
        if (video) {
            return await Video.findByIdAndDelete(id);
        }
        
    } catch (error) {
        console.log("Error deleting video: ", error);
        throw new Error('Could not delete video');
    }
};



const addCommentToVideo = async (videoId, commentContent, username) => {
    try {
        // Find user by username
        const user = await User.findOne({ username });
        if (!user) {
            throw new Error('User not found');
        }
        const userId = user._id;

        // Create a new comment
        const comment = new Comment({ content: commentContent, author: user?.nickName });
        await comment.save();

        // Find the video by its ID
        const video = await Video.findById(videoId);
        if (!video) {
            throw new Error('Video not found');
        }

        // Add the comment to the video's comments array
        video.comments.push(comment._id);
        await video.save();

        return comment.toObject();
    } catch (error) {
        console.error("Error adding comment to video: ", error);
        throw new Error('Could not add comment to video');
    }
};

const getVideosByUsername = async (username) => {
    try {
        // Find user by username
        const user = await User.findOne({ username });
        if (!user) {
            throw new Error('User not found');
        }
        const userId = user._id;

        // Find videos by userId
        const videos = await Video.find({ publisher: userId });

        return videos.map(video => video.toObject());
    } catch (error) {
        console.error("Error getting videos by username: ", error);
        throw new Error('Could not get videos by username');
    }
};

const getAllCommentsByVideoId = async (videoId) => {
    try {
        const video = await Video.findById(videoId).populate('comments');
        if (!video) {
            throw new Error('Video not found');
        }

        return video.comments.map(comment => ({
            _id: comment._id,
            content: comment.content,
            author: comment.author
        }));
    } catch (error) {
        console.error('Error getting comments by video ID:', error);
        throw new Error('Could not get comments');
    }
};

const deleteVideosByUsername = async (username) => {
    try {
        // Find user by username
        const user = await User.findOne({ username });
        if (!user) {
            throw new Error('User not found');
        }
        const userId = user._id;

        // Delete videos by userId
        await Video.deleteMany({ publisher: userId });

        return { message: `Videos by user ${username} deleted successfully` };
    } catch (error) {
        console.error("Error deleting videos by username: ", error);
        throw new Error('Could not delete videos by username');
    }
};

module.exports = {
    createVideo, getVideos, getVideoById, updateVideo, deleteVideo,
    addCommentToVideo, getVideosByUsername, getAllCommentsByVideoId, deleteVideosByUsername
};