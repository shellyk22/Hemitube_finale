// src/pages/ProfilePage/ProfilePage.js
import './ProfilePage.css';
import React from 'react';
import usersTable from '../../components/Users.json';

function ProfilePage({ currentUser, setCurrentUser }) {
  if (!currentUser) {
    return <div className="profile-page">Please log in to see your profile.</div>;
  }

  const user = usersTable[currentUser];

  return (
    <div className="profile-page">
      <h1>Profile Page</h1>
      <div className="profile-picture">
        {user.pic_data ? (
          <img src={user.pic_data} alt={`${user.username}'s profile`} className="img-fluid rounded-circle" />
        ) : (
          <div className="placeholder-image">No Image</div>
        )}
      </div>
      <div className="profile-info">
        <p><strong>Username:</strong> {user.username}</p>
        <p><strong>Password:</strong> {user.password}</p>
        <p><strong>Nickname:</strong> {user.nickname}</p>
      </div>
      <button 
        type="button" 
        className="btn btn-outline-danger"
        onClick={() => setCurrentUser(null)}>
          Sign Out
      </button>
    </div>
  );
}

export default ProfilePage;