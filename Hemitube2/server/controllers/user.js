const userService = require('../services/user');


const createUser = async (username) => {

    const newUser = await userService.createUser(req.body.username);
    if (!newUser) {
        return res.status(500).send("Error creating user.");
    }
    return await user.save();
}


module.exports = { createUser }