import React from 'react';
import { Link } from 'react-router-dom';
import VideoItem from '../videoItem/VideoItem';
import videoTable from '../videoItem/Videos.json';

function VideoListResults({videoList}) {
  if(!videoList){
    return <label>No Videos!!</label>

  }

  const videoLinks = [];
  for (let i = 0; i < videoList.length; i++) {
    const video = videoList[i];
    videoLinks.push(
      <Link to={`/video/${video.id}`} key={i} style={{ textDecoration: 'none', color: 'inherit' }}>
        <VideoItem
          title={video.title}
          author={video.author}
          views={video.views}
          time={video.time}
          img={video.thimbnail_data}
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
