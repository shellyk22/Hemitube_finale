import React from 'react';
import { Link } from 'react-router-dom';
import './VideoListResults.css';

function VideoListResults({ filteredVideoList }) {
  if (!filteredVideoList) {
    return <label>No Videos!!</label>;
  }

  const videoLinks = filteredVideoList.map((video, index) => (
    <Link to={`/video/${video.id}`} key={index} style={{ textDecoration: 'none', color: 'inherit' }}>
      <div className="video-item-res">
        <img className="video-thumbnail" src={video.thimbnail_data} alt={`${video.title}`} />
        <div className="video-title">{video.title}</div>
        <div className="video-details">
          <p>{video.author}</p>
          <p>{video.views} views • {video.time}</p>
        </div>
      </div>
    </Link>
  ));

  return (
    <div className="video-list-res">
      {videoLinks}
    </div>
  );
}

export default VideoListResults;
