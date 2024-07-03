# HemiTube2
our self-made logo:
![HemitubeLogoHorizontal](https://github.com/ArielGolanski/HemiTube2/assets/170665000/815d8c61-4e42-4ad7-9559-4309ca1bc596)


# Getting started:
To run the HemiTube website locally, follow these steps:

1. Clone the repository

2. Make sure that you have installed all required dependencies: npm i express cors body-parser mongoose custom-env path htpp multer

3. in the terminal, type "cd server" so you can run the server itself

4. Run the server using npm test, (or export NODE_ENV=test && node app.js for macOS/Linux, set NODE_ENV=test && node app.js for Windows). The server should be running.

5. Open your browser and visit http://localhost:5001 to access the HemiTube website.

# API Functionality:
The HemiTube website incorporates the following API endpoints and functionalities:

User:

- GET /api/users/:id Retrieve user details by providing the username.
- POST /api/users Create a new user.
- PUT /api/users/:id Change nickName or profilePicture for a specific user.
  
Token:
- POST /api/tokens: Generate a JSON Web Token (JWT) for registered users.
  
Videos:
- GET /api/users/:id/videos Retrieve the list of videos of the user.
- POST /api/users/:id/videos Create a new video.
- GET /api/users/:id/videos/:pid Retrieve a specific video.
- PUT /api/users/:id/videos/:pid Updates the description or title of a video
- DELETE /api/users/:id/videos/:pid Delete a specific video.
- Get /api/videos Retrieve the list of top 20 videos in the app.
- Get /api/videosHemi Retrieve the list of all the videos in the app.

Comments:

- GET /api/users/:id/videos/:pid/comments Retrieve the list of comments in a specific video.
- POST /api/users/:id/videos/:pid/comments Adds a comment to specific video
- GET /api/users/:id/videos/:pid/comments/:cid Gets a specific comment from a video
- PUT /api/users/:id/videos/:pid/comments/:cid Updates a specific comment
- DELETE /api/users/:id/videos/:pid/comments/:cid Delets a specific comment from a video.
  

Notice that we expanded the required API, but we ensured that the core functionalities remained intact.

# Some information about the app and how it works 

- in the signUp page, you will get an alert if you try to sign-up with an existing useranme ("invalid useranme" and 409 from the server),
   you will get a different alert if the fields are not filled properly.
- in the login page, you will get an alert if your password or username are incorrect.
- as a guest, you will have access to the videos and comments, without a possibility to edit or delete them.
- also, you can go to user videos page if you press on the name of a publisher in an existing video, in this page, you can see all of this user videos.
- as a registered user, you will have access to your profile page (where you can edit your profile photo and nickName)
- also, you will have the ability to add video (link to add video page from the home page as well), delete your videos and to comment on existing videos.
- pay attention, when you delete a user, all of its videos and comments are deleted as well

# some examples of how our app works:

![Screenshot 2024-07-01 223627](https://github.com/ArielGolanski/HemiTube2/assets/170665000/bd123514-1a47-4584-9058-592d0b3ba70b)
![alt text](<Screenshot 2024-07-01 195110.png>) ![alt text](<Screenshot 2024-07-01 173437.png>) ![alt text](<Screenshot 2024-07-01 193542.png>) ![alt text](<Screenshot 2024-07-01 193633.png>) ![alt text](<Screenshot 2024-07-01 193700.png>) ![alt text](<Screenshot 2024-07-01 193712.png>) ![alt text](<Screenshot 2024-07-01 193746.png>) ![alt text](<Screenshot 2024-07-01 193759.png>) ![alt text](<Screenshot 2024-07-01 193822.png>) ![alt text](<Screenshot 2024-07-01 194203.png>) ![alt text](<Screenshot 2024-07-01 194215.png>) ![alt text](<Screenshot 2024-07-01 194226.png>) ![alt text](<Screenshot 2024-07-01 194305.png>) ![alt text](<Screenshot 2024-07-01 194333.png>) ![alt text](<Screenshot 2024-07-01 194359.png>) ![alt text](<Screenshot 2024-07-01 194415.png>) ![alt text](<Screenshot 2024-07-01 194435.png>) ![alt text](<Screenshot 2024-07-01 194446.png>) ![alt text](<Screenshot 2024-07-01 194517.png>) ![alt text](<Screenshot 2024-07-01 194530.png>) ![alt text](<Screenshot 2024-07-01 194632.png>) ![alt text](<Screenshot 2024-07-01 194646.png>) ![alt text](<Screenshot 2024-07-01 194700.png>) ![alt text](<Screenshot 2024-07-01 194716.png>) ![alt text](<Screenshot 2024-07-01 194743.png>) ![alt text](<Screenshot 2024-07-01 194758.png>) ![alt text](<Screenshot 2024-07-01 194819.png>) ![alt text](<Screenshot 2024-07-01 194830.png>) ![alt text](<Screenshot 2024-07-01 194841.png>) ![alt text](<Screenshot 2024-07-01 194953.png>) ![alt text](<Screenshot 2024-07-01 195031.png>) ![alt text](<Screenshot 2024-07-01 195052.png>) ![Screenshot 2024-07-01 224139](https://github.com/ArielGolanski/HemiTube2/assets/170665000/1594021b-b3c7-4426-aabe-dd123482d5fd)


