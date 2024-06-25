import React from 'react';
import { useParams } from 'react-router-dom';
import VideoListResults from '../../components/videoListResults/VideoListResults';

function MyVideos({videoList}) {
  const { username } = useParams();
  console.log(username)
  const userVideos = Object.values(videoList).filter(video => video.author === username);

  console.log(userVideos.length)

  return (
    <div>
      <h1>My Videos</h1>
      <div>
      <VideoListResults filteredVideoList={userVideos} />
      </div>
    </div>
  );
}

export default MyVideos;