import React from 'react';
import LeftMenu from '../../components/leftMenu/LeftMenu';
import Search from '../../components/search/Search';
import VideoListResults from '../../components/videoListResults/VideoListResults';

import logo from '../../components/hemitubeLogoForC.jpeg';
import './Homepage.css'

function HomePage({ doSearch, videoList, setCurrentUser, currentUser, filteredVideoList }) {
  if (!filteredVideoList) {
    return <div>No videos</div>;
  }
  console.log("Current User: " + currentUser);

  return (
    <div className="container-fluid">
      <div className="row">
        {/* Left Menu */}
        <div className="col-12 col-md-2 sidebar2">
          <LeftMenu currentUser={currentUser} setCurrentUser={setCurrentUser} />
        </div>

        {/* Search and Video List Results */}
        <div className="col-12 col-md-10 main-content2">
          <div className="search-bar2 d-flex align-items-center mb-3">
            <Search doSearch={doSearch} />
            <img src={logo} alt="Logo" className="ms-3 top-right-image2" width="40px" />
          </div>
          <VideoListResults filteredVideoList={filteredVideoList} />
        </div>
      </div>
    </div>
  );
}

export default HomePage;
