import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './CommentSection.css';

export const serverAddress = 'http://localhost:5001';

function CommentSection({ videoList }) {
  const { id } = useParams();
  const video = videoList.find(v => v._id === id);
  const [commentText, setCommentText] = useState('');
  const [comments, setComments] = useState([]);

  useEffect(() => {
    if (video) {
      fetchComments();
    }
  }, [id, video]);

  const fetchComments = async () => {
    try {
      const response = await fetch(`${serverAddress}/api/users/${video.publisher}/videos/${id}/comments`);
      if (!response.ok) {
        throw new Error('Failed to fetch comments');
      }
      const data = await response.json();
      console.log(data);
      setComments(data || []);
    } catch (error) {
      console.error('Error fetching comments:', error);
    }
  };

  const handleAddComment = async () => {
    if (localStorage.getItem("username") && commentText.trim()) {
      const newComment = {
        content: commentText,
        author: localStorage.getItem("username")
      };

      try {
        const response = await fetch(`${serverAddress}/api/users/${video.publisher}/videos/${id}/comments`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(newComment)
        });
        if (!response.ok) {
          throw new Error('Failed to add comment');
        }
        const data = await response.json();
        setComments([...comments, data.comment]);
        setCommentText('');
      } catch (error) {
        console.error('Error adding comment:', error);
      }
    }
  };

  const handleDeleteComment = async (commentId) => {
    try {
      const response = await fetch(`${serverAddress}/api/users/${video.publisher}/videos/${id}/comments/${commentId}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete comment');
      }
      setComments(comments.filter(comment => comment._id !== commentId));
    } catch (error) {
      console.error('Error deleting comment:', error);
    }
  };

  const handleEditComment = async (commentId, newText) => {
    try {
      const response = await fetch(`${serverAddress}/api/users/${video.publisher}/videos/${id}/comments/${commentId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ text: newText })
      });
      if (!response.ok) {
        throw new Error('Failed to edit comment');
      }
      const data = await response.json();
      setComments(comments.map(comment => (comment._id === commentId ? data.comment : comment)));
    } catch (error) {
      console.error('Error editing comment:', error);
    }
  };

  if (!video) {
    return <div>Video not found</div>;
  }

  return (
    <div className="unique-comment-section">
      <h1>Comments:</h1>
      <div className="unique-comment-container">
        <div className="unique-comment-body">
          {localStorage.getItem("username") && (
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
            <div key={comment._id} className="rounded-pill">
              <div className="unique-comment-header">
                <div className="unique-comment-author">
                  <p className="small mb-0 ms-2"><strong>{comment.author}</strong></p>
                </div>
                <p>{comment.content}</p>
                {localStorage.getItem("username") && (
                  <div className="unique-comment-actions">
                    <button className="btn btn-outline-secondary rounded-pill" onClick={() => handleDeleteComment(comment._id)}>Delete</button>
                    <button className="btn btn-outline-secondary rounded-pill" onClick={() => {
                      const newText = prompt('Edit your comment:', comment.content);
                      if (newText !== null) handleEditComment(comment._id, newText);
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
