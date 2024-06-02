import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './signup.css'; 

function SignUp() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [verifyPassword, setVerifyPassword] = useState('');
  const [nickname, setNickname] = useState('');
  const [profilePhoto, setProfilePhoto] = useState('');
  const [isPasswordValid, setIsPasswordValid] = useState(false);
  const [doPasswordsMatch, setDoPasswordsMatch] = useState(true);
  const [showPassword, setShowPassword] = useState(false);
  const [showVerificationPassword, setShowVerificationPassword] = useState(false);

  const validatePassword = (password) => {
    const minLength = /.{8,}/;
    const hasNumber = /[0-9]/;
    const hasLowercase = /[a-z]/;
    const hasUppercase = /[A-Z]/;

    return minLength.test(password) && hasNumber.test(password) && hasLowercase.test(password) && hasUppercase.test(password);
  };

  const handlePasswordChange = (event) => {
    const newPassword = event.target.value;
    setPassword(newPassword);
    const isValid = validatePassword(newPassword);
    setIsPasswordValid(isValid);
  };

  const handleVerifyPasswordChange = (event) => {
    const newVerifyPassword = event.target.value;
    setVerifyPassword(newVerifyPassword);
    setDoPasswordsMatch(newVerifyPassword === password);
  };

  const PasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const PasswordVerificationVisibility = () => {
    setShowVerificationPassword(!showVerificationPassword);
  };
  

  const isSignUpDisabled = (!isPasswordValid || !doPasswordsMatch
    || username.length < 1 || nickname.length < 1);

  return (
    <div>
      <h1>Welcome to HemiTube</h1>
      <div className="input-group mb-3">
        <input
          type="text"
          className={`form-control ${username ? 'valid' : 'invalid'}`}
          placeholder="Choose Username"
          aria-label="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>
      <div className={`input-group mb-3 ${isPasswordValid ? 'valid' : 'invalid'}`}>
        <input
          type={showPassword ? 'text' : 'password'}
          className="form-control"
          placeholder="Choose Password"
          aria-label="Password"
          value={password}
          onChange={handlePasswordChange}
        />
        <button
          type="button"
          className="btn btn eye-icon"
          onClick={PasswordVisibility}
        >
          {showPassword ? <i className="bi bi-eye-slash"></i> : <i className="bi bi-eye"></i>}
        </button>
      </div>
      <div className={`input-group mb-3 ${doPasswordsMatch ? '' : 'invalid'}`}>
      <input
        type={showVerificationPassword ? 'text' : 'password'}
        className="form-control"
        placeholder="Verify Password"
        aria-label="Verify Password"
        value={verifyPassword}
        onChange={handleVerifyPasswordChange}
/>
        <button
          type="button"
          className="btn btn eye-icon"
          onClick={PasswordVerificationVisibility}
        >
          {showVerificationPassword ? <i className="bi bi-eye-slash"></i> : <i className="bi bi-eye"></i>}
        </button>
        {password !== verifyPassword && verifyPassword.length >= 0 && (
          <div className="validation-message">Passwords do not match.</div>
        )}
      </div>
      <div>
        <input
          type="text"
          className="form-control"
          placeholder="Choose Nickname"
          aria-label="Nickname"
          value={nickname}
          onChange={(e) => setNickname(e.target.value)}
        />
      </div>
      <div>
        <input
          type="text"
          className="form-control"
          placeholder="Choose Profile Photo- fix"
          aria-label="Profile Photo"
          value={profilePhoto}
          onChange={(e) => setProfilePhoto(e.target.value)}
        />
      </div>
      {(!isPasswordValid) && (
        <div className="invalid-password-message">The password must contain at least 8 characters including a number, 
        a lowercase letter and an uppercase letter .</div>
      )}
      <Link to="/SignIn" onClick={isSignUpDisabled ? (e) => e.preventDefault() : null}>
  <button
    type="button"
    className="btn btn-outline-danger"
    disabled={isSignUpDisabled}>
    Sign Up
  </button>
</Link>
    </div>
  );
}

export default SignUp;
