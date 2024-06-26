const commentService = require('../services/comment') 

const jwt = require('jsonwebtoken'); 

const key = "secret key foo foo foo bar";
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


const isLoggedIn = (req, res, next) => {
    if (req.headers.authorization) {
        const token = req.headers.authorization.split(" ")[1];
        try {
            const decoded = jwt.verify(token, key);
            req.user = decoded; // Attach decoded token data to req.user
            return next();
        } catch (err) {
            return res.status(401).send("Invalid Token");
        }
    } else {
        return res.status(403).send('Token required');
    }
};

const deleteCommentsByUserId = async (req, res) => {
    try {
        const result = await commentService.deleteCommentsByUserId(req.params.userId);
        res.status(200).json(result);
    } catch (error) {
        res.status(500).json({ errors: [error.message] });
    }
};


module.exports = {createComment, getComment, updateComment, getComments, deleteComment, isLoggedIn, deleteCommentsByUserId}
