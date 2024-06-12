import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './SignIn.css';
import usersTable from '../../components/Users.json';
import logo from '../../components/hemitubeLogoForC.jpeg'; 

function SignIn({ setCurrentUser }) {
  const [showPassword, setShowPassword] = useState(false);
  const [signInUsername, setsignInUsername] = useState('');
  const [signInPassword, setsignInPassword] = useState('');

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleSignIn = () => {
    if (usersTable[signInUsername] && usersTable[signInUsername].password === signInPassword) {
      setCurrentUser(signInUsername);
      return true;
    }
    return false;
  }

  const navigate = useNavigate();

  return (
    <section className="text-center text-lg-start">
      <div1 className="container py-4 align-items-center">
        <div1 className="card cascading-right bg-body-tertiary shadow-5" style={{ backdropFilter: 'blur(30px)' }}>
          <div1 className="card-body p-5 shadow-5 text-center">
            <div1 className="logo-container mb-4">
              <img src={logo} alt="HemiTube Logo" className="logo" />
            </div1>
            <h2 className="fw-bold mb-5">Welcome back to HemiTube</h2>
            <div1 className="input-group mb-3">
              <input
                type="text"
                className="form-control"
                placeholder="Enter Username"
                aria-label="Username"
                value={signInUsername}
                onChange={(e) => setsignInUsername(e.target.value)}
              />
            </div1>
            <div1 className="input-group mb-3">
              <input
                type={showPassword ? 'text' : 'password'}
                className="form-control"
                placeholder="Enter Password"
                aria-label="Password"
                value={signInPassword}
                onChange={(e) => setsignInPassword(e.target.value)}
              />
              <button type="button" className="btn btn-outline-secondary" onClick={togglePasswordVisibility}>
                {showPassword ? <i className="bi bi-eye"></i> : <i className="bi bi-eye-slash"></i>}
              </button>
            </div1>
            <Link to="/" onClick={(event) => {
              event.preventDefault();
              if (handleSignIn()) {
                navigate('/');
              } else {
                alert('Incorrect username or password. Please try again.');
              }
            }}>
              <button type="button" className="btn btn-outline-danger">Sign In</button>
            </Link>
            <p>Don't have an account yet?</p>
            <Link to="/signup">
              <button type="button" className="btn btn-secondary btn-block mb-4">Sign Up</button>
            </Link>

            <Link to="/">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            Back to Home Page
          </button>
        </Link>
          </div1>
        </div1>
      </div1>
    </section>
  );
}

export default SignIn;
