import React from 'react';
import { useParams } from 'react-router-dom';
import LeftMenu from '../../components/leftMenu/LeftMenu';
import Search from '../../components/search/Search';
import VideoListResults from '../../components/videoListResults/VideoListResults';
import videoTable from '../../components/videoItem/Videos.json'

function VideoView({doSearch, videoList, setCurrentUser, currentUser }) {
  const { id } = useParams();
  const initialVideoList = Object.values(videoTable);
  const video = initialVideoList[id];

  return (
    
    <div className="video-view">
              <div className="row">
        <Search doSearch={doSearch} />
        <div className="col main-content"></div>
        <VideoListResults videos={videoList} />
      <h2>{video.title}</h2>
      <video controls src={video.file_path} />
      <p>{video.description}</p>
      <p>Author: {video.author}</p>
      <p>Views: {video.views}</p>
      <p>Uploaded: {video.time} ago</p>
      <p>Duration: {video.duration}</p>

      <div>
        <button> add comment</button>
        <button> share</button>
        <button> like</button>
        <button> subscribe</button>
      </div>


      
        
        
      
    </div>
    </div>

  );
}

export default VideoView;
