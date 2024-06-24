const userController = require('../controllers/user');

const express = require('express');
const router = express.Router();

router.route('/')
    .post(userController.createUser);

router.route('/:id')
    .get(userController.getUser)
    .put(userController.isLoggedIn, userController.updateUser);


module.exports = router;