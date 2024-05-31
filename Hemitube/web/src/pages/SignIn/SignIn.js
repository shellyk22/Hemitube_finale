import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './SignIn.css'; 


function SignIn() {
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div>
      <div>
        <div>
          <h1>Welcome back to HemiTube</h1>
          <div className="input-group mb-3">
            <input type="text" className="form-control" placeholder="Enter Username" aria-label="Username" required />
          </div>
          <div className="input-group mb-3">
            <input type={showPassword ? 'text' : 'password'} className="form-control" placeholder="Enter Password" aria-label="Password" required />
            <button type="button" className="btn btn eye-icon" onClick={togglePasswordVisibility}>
              {showPassword ? <i className="bi bi-eye"></i> : <i className="bi bi-eye-slash"></i>}
            </button>
          </div>
          <Link to="/">
            <button type="button" className="btn btn-outline-danger">Sign In</button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default SignIn;
