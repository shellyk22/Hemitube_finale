import React from 'react';
import LeftMenu from '../../components/leftMenu/LeftMenu';
import Search from '../../components/search/Search';
import VideoListResults from '../../components/videoListResults/VideoListResults';

function HomePage({ doSearch, videoList, setCurrentUser, currentUser }) {
  if(!videoList) {
    return <div>no videos</div>
  }
  console.log("CCCCC"+currentUser)
  return (
    <div className="row">
      <LeftMenu currentUser={currentUser} setCurrentUser={setCurrentUser}/>
      <div className="col main-content">
        <Search doSearch={doSearch} />
        <VideoListResults videoList={videoList} />
      </div>
    </div>
  );
}

export default HomePage;
