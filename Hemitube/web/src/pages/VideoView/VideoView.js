import React, { useEffect, useState } from 'react';
import { useNavigate, useParams, Link } from 'react-router-dom';
import Search from '../../components/search/Search';
import NotFound from '../NotFound/NotFound';
import VideoListResults from '../../components//VideoViewVidResults/VideoViewVidResults';
import CommentSection from '../../components/CommentSection/CommentSection';
import './VideoView.css';

function VideoView({
  doSearch, videoList, currentUser, filteredVideoList, updateComments, deleteVideo, updateVideoDetails, likedVideos, toggleLike
}) {
  const { id } = useParams();
  const video = videoList.find(v => v.id === id);
  const navigate = useNavigate();
  const [isEditing, setIsEditing] = useState(false);
  const [editedTitle, setEditedTitle] = useState(video ? video.title : '');
  const [editedDescription, setEditedDescription] = useState(video ? video.description : '');
  const isLiked = likedVideos.includes(id);

  useEffect(() => {
    if (video) {
      setEditedTitle(video.title);
      setEditedDescription(video.description);
    }
  }, [video]);

  if (!video) {
    return <NotFound />;
  }

  const handleDeleteVideo = () => {
    deleteVideo(id);
    navigate('/');
  };

  const handleEditVideo = () => {
    setIsEditing(true);
  };

  const handleSaveChanges = () => {
    updateVideoDetails(id, editedTitle, editedDescription);
    setIsEditing(false);
  };

  const handleToggleLike = () => {
    toggleLike(id);
  };

  return (
    <div className="video-view-container">
      <div className="video-list-container">
        <VideoListResults filteredVideoList={filteredVideoList} />
      </div>
      <div className="main-content">
        <Search doSearch={doSearch} />
        <div>
          <h2>{video.title}</h2>
          <video controls width="100%" src={video.data} />
        </div>
        <div className="video-details">
          <p>Description: {video.description}</p>
          <p>Author: {video.author}</p>
          <p>Views: {video.views}</p>
          <p>Uploaded: {video.time} ago</p>
        </div>
        {currentUser && (
          <div className="video-actions">
            <div className="action-buttons">
              <button className="btn btn-outline-secondary rounded-pill">Share</button>
              <button className="btn btn-outline-secondary rounded-pill">Subscribe</button>
              <button className="btn btn-outline-secondary rounded-pill" onClick={handleDeleteVideo}>Delete Video</button>
              <button className="btn btn-outline-secondary rounded-pill" onClick={handleEditVideo}>Edit Video</button>
              <button className="btn btn-outline-secondary rounded-pill" onClick={handleToggleLike}>
                {isLiked ? 'Unlike' : 'Like'}
              </button>
            </div>
          </div>
        )}
        {isEditing && (
          <div className="edit-section">
            <input
              type="text"
              value={editedTitle}
              onChange={(e) => setEditedTitle(e.target.value)}
              placeholder="Edit title"
            />
            <textarea
              value={editedDescription}
              onChange={(e) => setEditedDescription(e.target.value)}
              placeholder="Edit description"
            />
            <button className="rounded-pill" onClick={handleSaveChanges}>Save Changes</button>
          </div>
        )}
        <div className="comment-section">
          <CommentSection
            videoList={videoList}
            currentUser={currentUser}
            updateComments={updateComments}
          />
        </div>
        <Link to="/">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            Back to Home Page
          </button>
        </Link>
      </div>
    </div>
  );
}

export default VideoView;
