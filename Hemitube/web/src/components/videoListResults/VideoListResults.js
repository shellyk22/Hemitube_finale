import React from 'react';
import { Link } from 'react-router-dom';
import VideoItem from '../videoItem/VideoItem';
import './VideoListResults.css';

function VideoListResults({ filteredVideoList }) {
  if (!filteredVideoList) {
    return <label>No Videos!!</label>;
  }

  const videoLinks = filteredVideoList.map((video, index) => (
    <Link to={`/video/${video.id}`} key={index} style={{ textDecoration: 'none', color: 'inherit' }}>
      <div className="video-item-res">
        <img style={{height: 100, width: 180}} src={video.thimbnail_data} alt={`${video.title} `} />
        <div className="title">{video.title}</div>
        <div className="details">
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
