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

module.exports = {createComment, getComment, updateComment, getComments, deleteComment}
