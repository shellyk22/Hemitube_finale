import React from 'react';
import LeftMenu from '../../components/leftMenu/LeftMenu';
import Search from '../../components/search/Search';
import VideoListResults from '../../components/videoListResults/VideoListResults';

function HomePage({ doSearch, videoList, setCurrentUser, currentUser, filteredVideoList }) {
  if(!filteredVideoList) {
    return <div>no videos</div>
  }
  console.log("CCCCC"+currentUser)
  return (
    <div className="row">
      <LeftMenu currentUser={currentUser} setCurrentUser={setCurrentUser}/>
      <div className="col main-content">
        <Search doSearch={doSearch} />
        <VideoListResults filteredVideoList={filteredVideoList} />
      </div>
    </div>
  );
}

export default HomePage;
