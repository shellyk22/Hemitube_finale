import React , {useState} from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import HomePage from './pages/HomePage/HomePage';
import SignUp from './pages/SignUp/SignUp';
import SignIn from './pages/SignIn/SignIn';
import NotFound from './pages/NotFound/NotFound';
//import videos from './components/videoItem/videos';
import videoTable from './components/videoItem/Videos.json'
import VideoView from './pages/VideoView/VideoView';

function App() {


  const initialVideoList = Object.values(videoTable);
  const [videoList, setVideoList] = useState(initialVideoList);
  const [currentUser, setCurrentUser] = useState("");
  

  const doSearch = (q) => {
    const filteredVideos = initialVideoList.filter((video) => video.title.includes(q));
    setVideoList(filteredVideos);
  };
  
  return (
    <Router>
      <div className="container-fluid">
        <Routes>
          <Route path="/" element={<HomePage doSearch={doSearch} videoList={videoList} currentUser={currentUser} setCurrentUser={setCurrentUser}/>} />
          <Route path="/video/:id" element={<VideoView doSearch={doSearch} videoList={videoList} 
          currentUser={currentUser} setCurrentUser={setCurrentUser}/>} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/signin" element={<SignIn setCurrentUser={setCurrentUser} />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
