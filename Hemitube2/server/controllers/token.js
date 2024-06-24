const authService = require('../services/token');

async function login(req, res) {

    // Check login 
    const {password, username} = req.body;

    console.log("username:", username, "passowrd:", password)

    const user = await authService.getUserByUsername(username);

    if (!user || user.password !== password) {
        return res.status(404).json({message: 'Invalid username and/or password'});
    }

    // Generate the JWT token
    const token = authService.generateToken(username);

    // Success! Send JWT back to client.
    return res.status(200).json({token: token});
}

module.exports = {login};