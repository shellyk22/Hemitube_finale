const user = require('../models/user');

const createUser = async (username) => {
    try {
        const user = new User({
            username: username
        });
        await user.save();
        return 'success';
    } catch (error) {
        console.log("Error creating user: " + error);
        return null;
    }
};

module.exports = { createUser }