const commentService = require('../services/comment') 

const createComment = async (req, res) => {
    try {
        const { author, content } = req.body;
        if (!content) {
            return res.status(400).json({ errors: ['Content is required'] });
        }
        // Pass `null` as the author if it's not provided
        const comment = await commentService.createComment(author || null, content);
        if (!comment) {
            return res.status(500).json({ errors: ['Error creating comment'] });
        }
        res.status(201).json(comment);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};

const getComment = async (req, res) => {
    res.json(await commentService.getCommentById(req.params.id))
    if (!comment) {
        return res.status(404).json({errors: ['Comment not found']})
    }
    res.json(comment);
};

const updateComment = async (req, res) => {
    res.json(await commentService.updateComment(req.body.content))
    if (!comment) {
        return res.status(404).json({errors: ['Comment not found']})
    }
    res.json(comment);
};

const getComments = async (_, res) => {
    res.json(await commentService.getComments())
};

const deleteComment = async (_, res) => {
    res.json(await commentService.deleteComment(req.params.id))
    if (!comment) {
        return res.status(404).json({errors: ['Comment not found']})
    }
    res.json(comment);
};


const isLoggedIn = async (req, res, next) => {
    if (req.headers.authorization) {
        // Extract the token from that header
        const token = req.headers.authorization.split(" ")[1];
        try {
            // Verify the token is valid
            jwt.verify(token, key);

            //Token validation was successful. Continue to the actual function (index)
            return next();
        } catch (err) {
            return res.status(401).send("Invalid Token");
        }
    } else
        return res.status(403).send('Token required');
};


module.exports = {createComment, getComment, updateComment, getComments, deleteComment, isLoggedIn}
