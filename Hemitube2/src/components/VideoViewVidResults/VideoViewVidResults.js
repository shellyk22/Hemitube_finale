import React from 'react';
import { Link } from 'react-router-dom';
import VideoItem from '../videoItem/VideoItem';
import videoTable from '../videoItem/Videos.json';
import './VideoViewVidResults.css';

export const serverAddress = 'http://localhost:5001';

function VideoViewVidResuls({filteredVideoList}) {
  if(!filteredVideoList){
    return <label>No Videos!!</label>

  }

  const videoLinks = filteredVideoList.map((video, index) => (
    <>
      <Link to={`/video/${video._id}`} key={index} style={{ textDecoration: 'none', color: 'inherit' }}>
        <div className="video-item-res">
          <img className="video-thumbnail" src={`${serverAddress}/uploads/${video.thumbnail_name}`} alt={video.title} />
          <div className="video-title">{video.title}</div>
          <div className="video-details">
            <p>{video.publisher.username}</p>
            <p>{video.__v} views • {new Date(video.uploadDate).toLocaleDateString()}</p>
          </div>
        </div>
      </Link>
    </>
  ));

  return (
    <div className="video-list">
      {videoLinks}
    </div>
  );
}

export default VideoViewVidResuls;
