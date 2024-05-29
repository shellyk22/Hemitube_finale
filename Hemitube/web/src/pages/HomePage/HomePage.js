import React from 'react';
import LeftMenu from '../../components/leftMenu/LeftMenu';
import Search from '../../components/search/Search';
import VideoListResults from '../../components/videoListResults/VideoListResults';

function HomePage({ doSearch, videoList }) {
  return (
    <div className="row">
      <LeftMenu />
      <div className="col main-content">
        <Search doSearch={doSearch} />
        <VideoListResults videos={videoList} />
      </div>
    </div>
  );
}

export default HomePage;
