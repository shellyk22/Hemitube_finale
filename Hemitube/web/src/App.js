import React , {useState} from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import HomePage from './pages/HomePage/HomePage';
import SignUp from './pages/SignUp/SignUp';
import SignIn from './pages/SignIn/SignIn';
import NotFound from './pages/NotFound/NotFound';
import videos from './components/videoItem/videos';

function App() {
  const [videoList, setVideoList] = useState(videos);

  const doSearch = function(q) {
    setVideoList(videos.filter((video) => video.title.includes(q)));
  }

  const [currentUser, setCurrentUser] = useState("");

  return (
    <Router>
      <div className="container-fluid">
        <Routes>
          <Route path="/" element={<HomePage doSearch={doSearch} videoList={videoList} currentUser={currentUser} setCurrentUser={setCurrentUser}/>} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/signin" element={<SignIn setCurrentUser={setCurrentUser} />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
