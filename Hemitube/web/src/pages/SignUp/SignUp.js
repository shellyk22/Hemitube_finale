import React from 'react';

function SignUp() {
  return (     
    <div>
      <div>
                  <h1>Welcome to HemiTube</h1>
                  <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Choose Username" aria-label="Username"></input>
                    </div>
                    <div class="input-group mb-3">
                      <input type="text" class="form-control" placeholder="Choose Password" aria-label="Username"></input>
                    </div>
                    <div class="input-group mb-3">
                      <input type="text" class="form-control" placeholder="Verify Password" aria-label="Username"></input>
                    </div>
                    <div class="input-group mb-3">
                      <input type="text" class="form-control" placeholder="Choose Nickname" aria-label="Username"></input>
                    </div>
                    <div class="input-group mb-3">
                      <input type="text" class="form-control" placeholder="Choose Profile Photo-fix" aria-label="Username"></input>
                    </div>
                    <button type="button" class="btn btn-outline-danger">SignUp</button>
                  </div>
    </div>
  );
}

export default SignUp;

