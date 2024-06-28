// src/pages/ProfilePage/ProfilePage.js
import './ProfilePage.css';
import React from 'react';
import usersTable from '../../components/Users.json';

function ProfilePage({ currentUser, setCurrentUser }) {
  if (!localStorage.getItem("username")) {
    return <div className="profile-page">Please log in to see your profile.</div>;
  }

  

  return (
    <div className="profile-page">
      <h1>Profile Page</h1>
      <div className="profile-picture">
        {localStorage.getItem("profilePic") ? (
          <img src={localStorage.getItem("profilePic")} alt={`${localStorage.getItem("username")}'s profile`} className="img-fluid rounded-circle" />
        ) : (
          <div className="placeholder-image">No Image</div>
        )}
      </div>
      <div className="profile-info">
        <p><strong>Username:</strong> {localStorage.getItem("username")}</p>
        <p><strong>Password:</strong> {localStorage.getItem("password")}</p>
        <p><strong>Nickname:</strong> {localStorage.getItem("nickname")}</p>
      </div>
    </div>
  );
}

export default ProfilePage;