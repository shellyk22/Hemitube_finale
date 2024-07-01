# HemiTube2
API Functionality:
The HemiTube website incorporates the following API endpoints and functionalities:

User:

GET /api/users/:id Retrieve user details by providing the username.
POST /api/users Create a new user.
PUT /api/users/:id Change nickName or profilePicture for a specific user.
Token:

POST /api/tokens: Generate a JSON Web Token (JWT) for registered users.
Videos:

GET /api/users/:id/videos Retrieve the list of videos of the user.
POST /api/users/:id/videos Create a new video.
GET /api/users/:id/videos/:pid Retrieve a specific video.
PUT /api/users/:id/videos/:pid Updates the description or title of a video
DELETE /api/users/:id/videos/:pid Delete a specific video.
Get /api/videos Retrieve the list of top 20 videos in the app.
Get /api/videosHemi Retrieve the list of all the videos in the app.

Comments:
DELETE /api/users/:id/videos/:pid
GET /api/users/:id/videos/:pid/comments Retrieve the list of comments in a specific video.
POST /api/users/:id/videos/:pid/comments Adds a comment to specific video
GET /api/users/:id/videos/:pid/comments/:cid Gets a specific comment from a video
PUT /api/users/:id/videos/:pid/comments/:cid Updates a specific comment
DELETE /api/users/:id/videos/:pid/comments/:cid Delets a specific comment from a video

Notice that we expanded the required API, but we ensured that the core functionalities remained intact.
