import './LeftMenu.css'
import { Link } from 'react-router-dom';
import React from 'react';

function LeftMenu(){
    return(
        <div className="col-3 bg-light vh-100">
          <ul className="list-group">
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
          <div>
          <Link to="/signin">
                    <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
                        Sign In
                    </button>
                </Link>
        </div>
        <div>
        <Link to="/signup">
                    <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
                        Sign Up
                    </button>
                </Link>
        </div>
        </div>

        
    );
}
export default LeftMenu;