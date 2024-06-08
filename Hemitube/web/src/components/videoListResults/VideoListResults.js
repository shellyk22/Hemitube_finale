import React from 'react';
import { Link } from 'react-router-dom';
import VideoItem from '../videoItem/VideoItem';
import videoTable from '../videoItem/Videos.json';

function VideoListResults() {
  const initialVideoList = Object.values(videoTable);
  const videoLinks = [];
  for (let i = 0; i < initialVideoList.length; i++) {
    const video = initialVideoList[i];
    videoLinks.push(
      <Link to={`/video/${video.id}`} key={i} style={{ textDecoration: 'none', color: 'inherit' }}>
        <VideoItem
          title={video.title}
          author={video.author}
          views={video.views}
          time={video.time}
          img={video.thumbnail}
        />
      </Link>
    );
  }

  return (
    <div className="video-list">
      {videoLinks}
    </div>
  );
}

export default VideoListResults;
