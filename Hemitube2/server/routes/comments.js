
const express = require('express');
const commentController = require('../controllers/comment');

const router = express.Router();

router.route('/:vid/comments')
    .get(commentController.getComments) // Get all comments for a video by video ID (:vid)
    .post(commentController.isLoggedIn, commentController.createComment); // Add a new comment to a video by video ID (:vid)

router.route('/:vid/comments/:cid')
    .get(commentController.getComment) // Get details of a comment by comment ID (:cid)
    .put(commentController.isLoggedIn,commentController.updateComment) // Update a comment by comment ID (:cid)
    .delete(commentController.isLoggedIn,commentController.deleteComment); // Delete a comment by comment ID (:cid)

module.exports = router;
