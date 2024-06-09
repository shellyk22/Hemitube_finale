import React from 'react';
import { Navigate, useNavigate, useParams, Link } from 'react-router-dom';
import Search from '../../components/search/Search';
import NotFound from '../NotFound/NotFound';
import VideoListResults from '../../components/videoListResults/VideoListResults';

function VideoView({doSearch, videoList, currentUser }) {
  const { id } = useParams();
  const video = videoList[id];
  
  if(!video) {
    return <NotFound/ >
  }

  return (
    
    <div className="video-view">
              <div className="row">
              <div className="col main-content">
        <Search doSearch={doSearch} />
        <VideoListResults videoList={videoList} />
      </div>
        
      <h2>{video.title}</h2>
      <video controls width="400" height="300" src={video.data} />
      <p>descrioption: {video.description}</p>
      <p>Author: {video.author}</p>
      <p>Views: {video.views}</p>
      <p>Uploaded: {video.time} ago</p>

      <div>
        <button> add comment</button>
        <button> share</button>
        <button> like</button>
        <button> subscribe</button>
      </div>
      <Link to="/">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            back to home page
          </button>
        </Link>


      
        
        
      
    </div>
    </div>

  );
}

export default VideoView;
