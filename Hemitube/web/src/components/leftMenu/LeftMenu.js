import './LeftMenu.css';
import { Link } from 'react-router-dom';
import React from 'react';
import usersTable from '../Users.json';

import logo from '../../components/hemitubeLogoForC.jpeg';

function LeftMenu({ setCurrentUser, currentUser }) {
  return (
    <div className="left-menu">
      <ul className="list-group">
        <h1>HemiTube</h1>
        

        <li className="list-group-item d-flex align-items-center">
          <i className="bi bi-house-fill"></i>
          <span className="w-100 m-1 ms-3">Home</span>
          <span className="badge bg-primary rounded-pill">14</span>
        </li>
        <li className="list-group-item d-flex align-items-center">
          <i className="bi bi-search"></i>
          <span className="w-100 m-1 ms-3">Explore</span>
          <span className="badge bg-primary rounded-pill">2</span>
        </li>
        <li className="list-group-item d-flex align-items-center">
          <i className="bi bi-disc"></i>
          <span className="w-100 m-1 ms-3">Explore</span>
          <span className="badge bg-primary rounded-pill">1</span>
        </li>
        <li className="list-group-item d-flex align-items-center">
          <i className="bi bi-collection-play"></i>
          <span className="w-100 m-1 ms-3">Subscriptions</span>
          <span className="badge bg-primary rounded-pill">1</span>
        </li>
      </ul>
      <div>{!currentUser && (
        <Link to="/signin">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            Sign In
          </button>
        </Link>
      )}</div>
      <div>{!currentUser && (
        <Link to="/signup">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            Sign Up
          </button>
        </Link>
      )}</div>
      <div>{currentUser && (
        <Link to="/addVideo">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            Add Video
          </button>
        </Link>
      )}</div>
      <div>{currentUser && (
        <button 
          type="button" 
          className="btn btn-outline-danger list-group-item d-flex align-items-center"
          onClick={() => setCurrentUser(null)}>
            Sign Out
        </button>
      )}</div>
      <div className="user-list mt-4 list-group-item d-flex align-items-center">
        <h4>Hello, {usersTable[currentUser] ? usersTable[currentUser].nickname : "Guest"}</h4>
      </div>
      {currentUser && usersTable[currentUser] && usersTable[currentUser].pic_data && (
        <div className="user-pic">
          <img
            src={usersTable[currentUser].pic_data}
            alt="profile_pic"
            className="img-fluid rounded-circle"
          />
        </div>
      )}
    </div>
  );
}

export default LeftMenu;
