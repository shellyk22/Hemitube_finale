// src/pages/ProfilePage/ProfilePage.js
import './ProfilePage.css';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import usersTable from '../../components/Users.json';
import { deleteUser, setUserDetails } from '../../DataAccess/users';


function ProfilePage({ currentUser, setCurrentUser }) {
  const [profilePic, setProfilePic] = useState(localStorage.getItem("profilePic") || "");
  const [nickname, setNickname] = useState(localStorage.getItem("nickname") || "");
  const [isEditing, setIsEditing] = useState(false);
  const navigate = useNavigate();

  if (!localStorage.getItem("username")) {
    return <div className="profile-page">Please log in to see your profile.</div>;
  }

  const handleProfilePicChange = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      setProfilePic(reader.result);
    };
    reader.readAsDataURL(file);
  };

  const handleNicknameChange = (event) => {
    setNickname(event.target.value);
  };

  const handleSaveChanges = async () => {

    const userId = localStorage.getItem("userId");

    try {
      const result = await setUserDetails(userId, profilePic, nickname);
      localStorage.setItem("profilePic", profilePic);
      localStorage.setItem("nickName", nickname);
      setIsEditing(false);
      alert("Profile updated successfully!");
      return result
    }
    catch {
      return
    }
  };

  const handleDeleteUser = async () => {

    const userId = localStorage.getItem("userId");

    try {
      const userLoginData = await deleteUser(userId);
      localStorage.setItem('userId', "undefined");
      localStorage.setItem('JWT', "null");
      localStorage.setItem('username', "undefined");
      localStorage.setItem('nickName', "undefined");
      localStorage.setItem('profilePic', "undefined");
      window.location.reload();
      setCurrentUser(null);
      alert("User deleted successfully!");
      return userLoginData
    }
    catch {
      return false
    }

  };

  return (
    <div className="profile-page">
      <h1>Profile Page</h1>
      <div className="profile-picture">
        {profilePic ? (
          <img src={profilePic} alt={`${localStorage.getItem("username")}'s profile`} className="img-fluid rounded-circle" />
        ) : (
          <div className="placeholder-image">No Image</div>
        )}
      </div>
      <div className="profile-info">
        <p><strong>Username:</strong> {localStorage.getItem("username")}</p>
        <p><strong>Nickname:</strong> {localStorage.getItem("nickName")}</p>
      </div>
      {isEditing ? (
        <div className="edit-section">
          <div className="profile-picture-edit">
            <p>choose a new profile pic photo:</p>
            <input type="file" accept="image/*" onChange={handleProfilePicChange} />
          </div>
          <div className="nickname-edit">
            <p>new Nickname:</p>
            <input type="text" value={nickname} onChange={handleNicknameChange} />
          </div>
          <button onClick={handleSaveChanges}>Save Changes</button>
          <button onClick={() => setIsEditing(false)}>Cancel</button>
        </div>
      ) : (
        <div className="action-buttons">
          <button onClick={() => setIsEditing(true)}>Edit Profile</button>
          <Link to="/">
            <button onClick={handleDeleteUser}>Delete User</button>
          </Link>
          <div>
            <Link to="/">
              <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
                Back to Home Page
              </button>
            </Link>
          </div>
        </div>

      )}
    </div>
  );
}

export default ProfilePage;