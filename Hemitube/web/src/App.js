import React , {useEffect, useState} from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import HomePage from './pages/HomePage/HomePage';
import SignUp from './pages/SignUp/SignUp';
import SignIn from './pages/SignIn/SignIn';
import NotFound from './pages/NotFound/NotFound';
//import videos from './components/videoItem/videos';
import videoTable from './components/videoItem/Videos.json'
import usersTable from './components/Users.json'
import VideoView from './pages/VideoView/VideoView';
import AddVideo from './pages/AddVideo/Addvideo';

function App() {


  const initialVideoList = Object.values(videoTable);
  const initialUsers = Object.values(usersTable);
  const [videoList, setVideoList] = useState(Object.values(videoTable));
  const [filteredVideoList, setFilteredVideoList] = useState(Object.values(videoTable));
  const [currentUser, setCurrentUser] = useState("");
  const [usersVideos, setUsersVideos] = useState([]);
  
  useEffect(()=> {
    setFilteredVideoList(videoList);
  }, [videoList])

  const doSearch = (q) => {
    if(!q){
      setFilteredVideoList(videoList)
    }
    const filteredVideos = videoList.filter((video) => video.title.includes(q));
    setFilteredVideoList(filteredVideos);
  };
  

  //dark mode modifications
  const [isDarkMode, setIsDarkMode] = useState(false);

  useEffect(() => {
    document.body.classList.toggle('dark-mode', isDarkMode);
  }, [isDarkMode]);

  const toggleDarkMode = () => {
    setIsDarkMode((prevMode) => !prevMode);
  };

  return (
    <div className="container-fluid">
    <Router>
      
        <Routes>
          <Route path="/" element={<HomePage doSearch={doSearch} videoList={filteredVideoList} currentUser={currentUser}
           setCurrentUser={setCurrentUser}/>} />
          <Route path="/video/:id" element={<VideoView doSearch={doSearch} videoList={filteredVideoList} 
          currentUser={currentUser} setCurrentUser={setCurrentUser}/>} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/signin" element={<SignIn setCurrentUser={setCurrentUser} />} />
          <Route path="/addVideo" element={<AddVideo setCurrentUser={setCurrentUser} usersVideos={usersVideos}
          setUsersVideos = {setUsersVideos} videoList={videoList} setVideoList={setVideoList} currentUser={currentUser}/>} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      
    </Router>
    </div>
  );
}

export default App;
