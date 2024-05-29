import React from 'react';

function SignIn() {
  return (
              
         
    <div>
      <div>
                  <h1>Welcome back to HemiTube</h1>
                  <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Enter Username" aria-label="Username"></input>
                    </div>
                    <div class="input-group mb-3">
                      <input type="text" class="form-control" placeholder="Enter Password" aria-label="Username"></input>
                    </div>
                    <button type="button" class="btn btn-outline-danger">Sign In</button>
                  </div>
    </div>
  );
}

export default SignIn;
