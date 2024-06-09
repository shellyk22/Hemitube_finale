// CommentSection.js
import React, { useState , useEffect } from 'react';
import { useParams } from 'react-router-dom';

function CommentSection({ videoList, currentUser, updateComments }) {
  const { id } = useParams();
  const video = videoList.find(v => v.id === id);
  const [commentText, setCommentText] = useState('');
  const [comments, setComments] = useState(video.commentsArr);

  useEffect(() => {
    // Update comments when videoId changes
    setComments(video.commentsArr);
  }, [id, videoList]);

  const handleAddComment = () => {
    if (currentUser && commentText.trim()) {
      const newComment = {
        id: Date.now().toString(),
        text: commentText,
        author: currentUser
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

  return (
    <div>
      <h3>Comments</h3>
      {comments.map(comment => (
        <div key={comment.id}>
          <p><strong>{comment.author}:</strong> {comment.text}</p>
          {currentUser && (
            <>
              <button onClick={() => handleDeleteComment(comment.id)}>Delete</button>
              {currentUser && (
                <button onClick={() => {
                  const newText = prompt('Edit your comment:', comment.text);
                  if (newText !== null) handleEditComment(comment.id, newText);
                }}>Edit</button>
              )}
            </>
          )}
        </div>
      ))}
      {currentUser ? (
        <div>
          <textarea
            value={commentText}
            onChange={(e) => setCommentText(e.target.value)}
            placeholder="Add a comment"
          />
          <button onClick={handleAddComment}>Add Comment</button>
        </div>
      ) : (
        <p>Please log in to add a comment.</p>
      )}
    </div>
  );
}

export default CommentSection;
