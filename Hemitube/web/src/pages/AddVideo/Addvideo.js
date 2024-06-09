import React, { useRef, useState } from 'react';
import LeftMenu from '../../components/leftMenu/LeftMenu';
import Search from '../../components/search/Search';
import VideoListResults from '../../components/videoListResults/VideoListResults';
import usersTable from '../../components/Users.json';
import { Link, useNavigate } from 'react-router-dom';

function AddVideo({ currentUser, setUsersVideos, usersVideos, videoList, setVideoList }) {

    console.log("ADD VIDEO GOT VIDEO LIST AS:",videoList);
    const titleTextBox = useRef(null);
    const descriptionBox = useRef(null);
    const navigate = useNavigate();

    const VideoInputRef = useRef(null); // Ref for file input
    const ThumbnailInputRef = useRef(null); // Ref for file input
    const [selectedVideo, setSelectedVideo] = useState(null)
    const [selectedThumbnail, setSelectedThumbnail] = useState(null);

    const handleVideoUpload = function (event) {
        const selectedFile = event.target.files[0];
        if (selectedFile) {
            const reader = new FileReader();
            reader.onload = function (e) {
                setSelectedVideo(e.target.result);
            }
            reader.readAsDataURL(selectedFile);
        }

    }


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
         if (titleTextBox.current.value.length < 1 || descriptionBox.current.value.length < 1
            || !VideoInputRef.current.files || VideoInputRef.current.files.length === 0) {
             alert('you must fill all fields')
             { return false; }
         }
        const selectedVidFile = VideoInputRef.current.files[0];
        const selectedPicFile =ThumbnailInputRef.current.files[0];
        console.log("CCCCC" + videoList)
        const newVideo = {
            "id": videoList.length,
            "title": titleTextBox.current.value,
            "description": descriptionBox.current.value,
            "author": usersTable[currentUser].nickname,
            "views" : "0", 
            "time" : "0s",
            "file_path" : selectedVidFile,
            "thumbnail" : selectedPicFile,
            "data" : selectedVideo,
            "thimbnail_data" : selectedThumbnail,
            "comments" : []
        }
        console.log("new vid!!!!!:", newVideo)
        

        // Update state immutably
        console.log("VIDEPLOS:", videoList)
        setVideoList([...videoList, newVideo]);
        setUsersVideos([...usersVideos, newVideo]);

        // Reset form fields
        // titleTextBox.current.value = '';
        // descriptionBox.current.value = '';
        //setThumbnail("0");
        console.log("CCCCC" + newVideo.id)
        console.log("CCCCC" + newVideo.author)
        console.log("CCCCC" + newVideo.description)
        console.log("adding video to user" + currentUser);
        return true;
    }

    return (
        <div>
            <h4>Hello {usersTable[currentUser] ? usersTable[currentUser].nickname : "Guest"}, lets add a video</h4>
            <div>
                <input
                    type="text"
                    className={`form-control`}
                    placeholder="Choose a title to your new video"
                    aria-label="title"
                    ref={titleTextBox}
                />
            </div>
            <br />
            <div>
                <input
                    type="text"
                    className={`form-control`}
                    placeholder="Choose a title to your new video"
                    aria-label="title"
                    ref={descriptionBox}
                />
            </div>
            <div>
            {"video:"}
                <input
                    type="file"
                    onChange={handleVideoUpload}
                    accept="video/*"
                    ref={VideoInputRef}
                />
            </div>
            <div>
            {"Thumbnail:"}
                <input
                    type="file"
                    onChange={handleThumbnailUpload}
                    accept="image/*"
                    ref={ThumbnailInputRef}
                />
            </div>

            <Link to="/" onClick={(event) => {
                event.preventDefault(); // Prevent the default link behavior
                if (handeVideoAdding(event)) {
                    navigate('/'); //sign-up is successful
                } else {
                    console.log('adding the video failed. Staying on current page.');
                }
            }}>
                <button
                    type="button"
                    className="btn btn-outline-danger">
                    add the video
                </button>
            </Link>
            {selectedVideo && <video
                controls
                src={selectedVideo}>
            </video>}

            {selectedThumbnail && <img
                src={selectedThumbnail}
                alt="Thumbnail"
                width="200"
            />}
        </div>
    );
}

export default AddVideo;
