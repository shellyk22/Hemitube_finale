import './App.css';
import VideoListResults from './videoListResults/VideoListResults';
import LeftMenu from './leftMenu/LeftMenu';
import videos from './videoItem/videos';
import Search from './search/Search';
import { useState } from 'react';



function App() {

  const [videoList, setVideoList] = useState(videos);

  const doSearch = function(q){
    setVideoList(videos.filter((video)=> video.title.includes(q)));
  }



  return (
    <div className="container-fluid">
      <div className="row">
        <LeftMenu />
        <div className="col main-content">
          <Search doSearch={doSearch} />
          <div className="row"></div>
          <div className="row"></div>
          <VideoListResults videos={videoList}/>
        </div>
      </div>
    </div>
  );
}

export default App;
