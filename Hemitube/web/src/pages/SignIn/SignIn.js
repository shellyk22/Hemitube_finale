import React, { useState } from 'react';
import { Link , useNavigate } from 'react-router-dom';
import './SignIn.css'; 
import usersTable from '../../components/Users.json';


function SignIn({setCurrentUser}) {
  const [showPassword, setShowPassword] = useState(false);
  const [signInUsername, setsignInUsername] = useState('');
  const [signInPassword, setsignInPassword] = useState('');

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const handleSignIn = () => {

    if (usersTable[signInUsername]
      && usersTable[signInUsername].password === signInPassword) {
        setCurrentUser(signInUsername)
        console.log("gAHHHHHH1");
        return true
    } 
    console.log("gAHHHHHH");
    return false; //TODO: notify user...
  }
  const navigate = useNavigate();

  return (
    <div>
      <div>
        <div>
          <h1>Welcome back to HemiTube</h1>
          <div className="input-group mb-3">
            <input type="text" 
            className="form-control" 
            placeholder="Enter Username" 
            aria-label="Username" 
            value={signInUsername}
            onChange={(e) => setsignInUsername(e.target.value)} />
          </div>
          <div className="input-group mb-3">
            <input 
            type={showPassword ? 'text' : 'password'} 
            className="form-control" 
            placeholder="Enter Password" 
            aria-label="Password" 
            value={signInPassword}
            onChange={(e) => setsignInPassword(e.target.value)} />
            <button type="button" className="btn btn eye-icon" onClick={togglePasswordVisibility}>
              {showPassword ? <i className="bi bi-eye"></i> : <i className="bi bi-eye-slash"></i>}
            </button>
          </div>
          <Link to="/" onClick={(event) => {
        event.preventDefault(); // Prevent the default link behavior
        if (handleSignIn()) {
          navigate('/'); //sign-in is successful
        } else {
          alert('Incorrect username or password. Please try again.');
          console.log('Sign-up failed. Staying on current page.');
        }
      }}>
            <button type="button" className="btn btn-outline-danger">Sign In</button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default SignIn;
