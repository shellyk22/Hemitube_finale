import React, { useRef, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import usersTable from '../../components/Users.json';
import logo from '../../components/hemitubeLogoForC.jpeg';

function AddVideo({ currentUser, videoList, setVideoList }) {
  const titleTextBox = useRef(null);
  const descriptionBox = useRef(null);
  const navigate = useNavigate();
  const VideoInputRef = useRef(null);
  const ThumbnailInputRef = useRef(null);
  const [selectedVideo, setSelectedVideo] = useState(null);
  const [selectedThumbnail, setSelectedThumbnail] = useState(null);

  const handleVideoUpload = (event) => {
    const selectedFile = event.target.files[0];
    if (selectedFile) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setSelectedVideo(e.target.result);
      };
      reader.readAsDataURL(selectedFile);
    }
  };

  const handleThumbnailUpload = (event) => {
    const selectedThumbnailFile = event.target.files[0];
    if (selectedThumbnailFile) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setSelectedThumbnail(e.target.result);
      };
      reader.readAsDataURL(selectedThumbnailFile);
    }
  };

  const handeVideoAdding = () => {
    if (
      titleTextBox.current.value.length < 1 ||
      descriptionBox.current.value.length < 1 ||
      !VideoInputRef.current.files ||
      VideoInputRef.current.files.length === 0
    ) {
      alert('You must fill all fields');
      return false;
    }
    const selectedVidFile = VideoInputRef.current.files[0];
    const selectedPicFile = ThumbnailInputRef.current.files[0];
    const newVideo = {
      id: Date.now().toString(), // Use a unique ID
      title: titleTextBox.current.value,
      description: descriptionBox.current.value,
      author: usersTable[currentUser].nickname,
      views: '0',
      time: '0s',
      file_path: selectedVidFile,
      thumbnail: selectedPicFile,
      data: selectedVideo,
      thimbnail_data: selectedThumbnail,
      commentsArr: [{ id: '0', text: 'hello1', author: 'hemi' }]
    };

    setVideoList([...videoList, newVideo]);

    return true;
  };

  return (
    <section className="text-center text-lg-start">
      <div className="container py-4 align-items-center">
        <div className="card cascading-right bg-body-tertiary shadow-5" style={{ backdropFilter: 'blur(30px)' }}>
          <div className="card-body p-5 shadow-5 text-center">
            <div className="logo-container mb-4">
              <img src={logo} alt="HemiTube Logo" className="logo" />
            </div>
            <div>
              <h4>Hello {usersTable[currentUser] ? usersTable[currentUser].nickname : 'Guest'}, let's add a video</h4>
              <div>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Choose a title for your new video"
                  aria-label="title"
                  ref={titleTextBox}
                />
              </div>
              <br />
              <div>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Choose a description for your new video"
                  aria-label="description"
                  ref={descriptionBox}
                />
              </div>
              <div>
                {'video:'}
                <input type="file" onChange={handleVideoUpload} accept="video/*" ref={VideoInputRef} />
              </div>
              <div>
                {'Thumbnail:'}
                <input type="file" onChange={handleThumbnailUpload} accept="image/*" ref={ThumbnailInputRef} />
              </div>

              <Link
                to="/"
                onClick={(event) => {
                  event.preventDefault();
                  if (handeVideoAdding(event)) {
                    navigate('/');
                  } else {
                    console.log('Adding the video failed. Staying on the current page.');
                  }
                }}
              >
                <button type="button" className="btn btn-outline-danger">
                  Add the video
                </button>
              </Link>
              <Link to="/">
                <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
                  Back to Home Page
                </button>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default AddVideo;
