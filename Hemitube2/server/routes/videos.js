
const express = require('express');
const videoController = require('../controllers/video');
const userController = require('../controllers/user');

const router = express.Router();

// Routes to manage videos under a specific user
router.route('/:id/videos')
    .get(videoController.getVideos) // Get all videos for a user by user ID (:id)
    .post(userController.isLoggedIn, videoController.createVideo); // Create a new video for a user by user ID (:id)

router.route('/:id/videos/:pid')
    .get(videoController.getVideo) // Get details of a video by video ID (:pid)
    .put(userController.isLoggedIn, videoController.updateVideo) // Update a video by video ID (:pid)
    .delete(userController.isLoggedIn, videoController.deleteVideo); // Delete a video by video ID (:pid)

module.exports = router;
