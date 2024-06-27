import './LeftMenu.css';
import { Link } from 'react-router-dom';
import React from 'react';
import usersTable from '../Users.json';
import { logOut } from '../../DataAccess/users';

import logo from '../../components/hemitubeLogoForC.jpeg';

function LeftMenu({ setCurrentUser, currentUser }) {
  console.log(localStorage.getItem("username"))
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
      <div>{localStorage.getItem("username") && (
        <Link to="/addVideo">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            Add Video
          </button>
        </Link>
      )}</div>
      <div>{localStorage.getItem("username") && (
        <button 
          type="button" 
          className="btn btn-outline-danger list-group-item d-flex align-items-center"
          onClick={() => logOut()}>
            Log Out
        </button>
      )}</div>
      <div>{currentUser && (
        <Link to="/profile">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            Profile
          </button>
        </Link>
      )}</div>
      <div>{currentUser && (
        <Link to={`/${currentUser}`} className="w-100 m-1 ms-3">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            My Videos
          </button>
        </Link>
      )}</div>
      <div className="user-list mt-4 list-group-item d-flex align-items-center">
        <h4>Hello, {localStorage.getItem("username") ? localStorage.getItem("username") : "Guest"}</h4>
      </div>
      {localStorage.getItem("profilePic") && (
        <div className="user-pic">
          <img
            src={localStorage.getItem("profilePic")}
            alt="profile_pic"
            className="img-fluid rounded-circle"
          />
        </div>
      )}
    </div>
  );
}

export default LeftMenu;
