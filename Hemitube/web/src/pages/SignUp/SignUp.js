import React, { useState , useRef } from 'react';
import { Link , useNavigate } from 'react-router-dom';
import './signup.css'; 
import usersTable from '../../components/Users.json';
import logo from '../../components/hemitubeLogoForC.jpeg'; 

function SignUp() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [verifyPassword, setVerifyPassword] = useState('');
  const [nickname, setNickname] = useState('');
  const [profilePhoto, setProfilePhoto] = useState(usersTable.s.pic_data);
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


  ///////////profilr pic upload etc
  const ThumbnailInputRef = useRef(null); // Ref for file input
  //const [selectedThumbnail, setSelectedThumbnail] = useState(null);
  const handleThumbnailUpload = (event) => {
    const selectedThumbnailFile = event.target.files[0];
    if (selectedThumbnailFile) {
        const reader = new FileReader();
        reader.onload = (e) => {
          setProfilePhoto(e.target.result);
        };
        reader.readAsDataURL(selectedThumbnailFile);
    }
};

  const handleSignUp = () => {
    
    if(!isPasswordValid || !doPasswordsMatch
      || username.length < 1 || nickname.length < 1) {
      console.log("oh no");
      return;}

    if (usersTable[username]) {
      console.log("BAHHHHHH");
      alert('Choose a different username, the one you chose is unavailable')
      return null; //TODO: add notification about existing username
    }

    //setProfilePhoto(usersTable.s.pic_data);
    //let selectedPicFile = usersTable.s.pic_data; // Default pic

    // if (!ThumbnailInputRef.current.files && ThumbnailInputRef.current.files.length < 0) {
    //   setProfilePhoto(usersTable.s.pic_data);
    // }
    
    usersTable[username] = {
      "username" : username,
      "password" : password,
      "nickname" : nickname,
      "profilepic" : profilePhoto,
      "pic_data" : profilePhoto
    }
    return true;
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
    <div1>
      <h1>Welcome to HemiTube</h1>
      <div1 className="input-group mb-3">
        <input
          type="text"
          className={`form-control ${username ? 'valid' : 'invalid'}`}
          placeholder="Choose Username"
          aria-label="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div1>
      <div1 className={`input-group mb-3 ${isPasswordValid ? 'valid' : 'invalid'}`}>
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
        {(!isPasswordValid) && (
        <div1 className="invalid-password-message">The password must contain at least 8 characters including a number, 
        a lowercase letter and an uppercase letter .</div1>
      )}

      </div1>
      <div1 className={`input-group mb-3 ${doPasswordsMatch ? '' : 'invalid'}`}>
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
          <div1 className="validation-message">Passwords do not match.</div1>
        )}
      </div1>
      <div1>
        <input
          type="text"
          className="form-control"
          placeholder="Choose Nickname"
          aria-label="Nickname"
          value={nickname}
          onChange={(e) => setNickname(e.target.value)}
        />
      </div1>
      <div1>
            {"profile pic:"}
                <input
                    type="file"
                    onChange={handleThumbnailUpload}
                    accept="image/*"
                    ref={ThumbnailInputRef}
                />
            </div1>
      
      <Link to="/SignIn" onClick={(event) => {
        event.preventDefault(); // Prevent the default link behavior
        if (handleSignUp()) {
          navigate('/SignIn'); //sign-up is successful
        } else {
          console.log('Sign-up failed. Staying on current page.');
        }
      }}>
  <button
    type="button"
    className="btn btn-outline-danger">
    Sign Up
  </button>
</Link>
        <Link to="/">
          <button type="button" className="btn btn-outline-danger list-group-item d-flex align-items-center">
            Back to Home Page
          </button>
        </Link>
    </div1>
    </div1>
        </div1>
      </div1>
    </section>
  );
}

export default SignUp;
