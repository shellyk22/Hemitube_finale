//const Message = require('../models/message');
//const FilesAttach = require('../models/files');
const Comment = require('../models/comment.js');
const Video = require('../models/video')

// const createComment = async (author, content) => {
//     try {
//         const comment = new Comment({
//             author: author || null, // Use `null` or an empty string if author is not provided
//             content: content
//         });
//         await comment.save();

//         const commentObject = comment.toObject();
//         commentObject.id = commentObject._id.toString();
//         delete commentObject._id;

//         return commentObject;
//     } catch (error) {
//         console.log('Error creating comment:', error);
//         throw new Error('Could not create comment');
//     }
// };

const getComments = async () => {
    return await Comment.find({});
};

const getCommentById = async (id) => {
    return await Comment.findById(id);
};

const updateComment = async (id, content) => {
    const comment = await getCommentById(id);
    if (!comment) return null;
    comment.content = content;
    await comment.save();
    return comment;
};

const deleteComment = async (id) => {
    const comment = await getCommentById(id);
    if (!comment) return null;
    await comment.deleteOne();
    return comment;
};

const deleteCommentsByUserId = async (userId) => {
    try {
        await Comment.deleteMany({ author: userId });
        return { message: `Comments by user ${userId} deleted successfully` };
    } catch (error) {
        console.log('Error deleting comments by user ID:', error);
        throw new Error('Could not delete comments by user ID');
    }
};
const deleteCommentByVideoId = async (videoId, commentId) => {
    try {
        const comment = await getCommentById(commentId);
        if (!comment) return null;

        // Remove comment from the specified video
        await Video.findByIdAndUpdate(videoId, {
            $pull: { comments: commentId }
        });

        await comment.deleteOne();
        return comment;
    } catch (error) {
        console.log('Error deleting comment by video ID:', error);
        throw new Error('Could not delete comment');
    }
};


module.exports = {getComments, getCommentById, updateComment, deleteComment, deleteCommentsByUserId, deleteCommentByVideoId}