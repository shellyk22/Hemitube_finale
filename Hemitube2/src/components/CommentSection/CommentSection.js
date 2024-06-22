import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './CommentSection.css';
import userTable from '../Users.json';

function CommentSection({ videoList, currentUser, updateComments }) {
  const { id } = useParams();
  const video = videoList.find(v => v.id === id);
  const [commentText, setCommentText] = useState('');
  const [comments, setComments] = useState(video ? video.commentsArr : []);
  

  useEffect(() => {
    if (video && video.commentsArr) {
      setComments(video.commentsArr);
    }
  }, [id, videoList, video]);

  const handleAddComment = () => {
    if (currentUser && commentText.trim()) {
      const newComment = {
        id: Date.now().toString(),
        text: commentText,
        author: userTable[currentUser].nickname
      };
      const updatedComments = [...comments, newComment];
      setComments(updatedComments);
      updateComments(id, updatedComments);
      setCommentText('');
    }
  };

  const handleDeleteComment = (commentId) => {
    const updatedComments = comments.filter(comment => comment.id !== commentId);
    setComments(updatedComments);
    updateComments(id, updatedComments);
  };

  const handleEditComment = (commentId, newText) => {
    const updatedComments = comments.map(comment =>
      comment.id === commentId ? { ...comment, text: newText } : comment
    );
    setComments(updatedComments);
    updateComments(id, updatedComments);
  };

  if (!video) {
    return <div>Video not found</div>;
  }

  return (
    <div className="unique-comment-section">
        <h1>comments:</h1>
      <div className="unique-comment-container">
        <div className="unique-comment-body">
        {currentUser && (
          <div className="unique-form-outline">
            <input
              type="text"
              className="form-control"
              placeholder="Type comment..."
              value={commentText}
              onChange={(e) => setCommentText(e.target.value)}
            />
            <button className="btn btn-outline-secondary rounded-pill" onClick={handleAddComment}>Add Comment</button>
          </div>
        )}
          {comments.map(comment => (
            <div key={comment.id} className="rounded-pill">
              <div className="unique-comment-header">
                <div className="unique-comment-author">
                  <p className="small mb-0 ms-2"><strong>{comment.author}</strong></p>
                </div>
                <p>{comment.text}</p>
                {currentUser && (
                  <div className="unique-comment-actions">
                    <button className="btn btn-outline-secondary rounded-pill" onClick={() => handleDeleteComment(comment.id)}>Delete</button>
                    <button className="btn btn-outline-secondary rounded-pill" onClick={() => {
                      const newText = prompt('Edit your comment:', comment.text);
                      if (newText !== null) handleEditComment(comment.id, newText);
                    }}>Edit</button>
                  </div>
                )}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default CommentSection;
