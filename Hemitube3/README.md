# HemiTube3

our self-made logo: as you can see, this is hemi, holding the YouTube sign:
<br />
![hemitubeLogoForC](https://github.com/user-attachments/assets/20852023-6978-4b86-b501-0693707056ff)

# Getting started:
To run the HemiTube website locally, follow these steps:

- Clone the repository

- Make sure that you have installed all required dependencies: npm i express cors body-parser mongoose custom-env path htpp multer

- if you have a data base calles "test", make sure to clear it complately to avoid problems

- in the terminal, type "cd server" so you can run the server itself

- Run the server using npm test, (or export NODE_ENV=test && node app.js for macOS/Linux, set NODE_ENV=test && node app.js for Windows). The server should be running.

- Open your browser and visit http://localhost:5001 to access the HemiTube website. (and so that the default videos and user will be uploaded to the DB)

- we added a default user called "Israel" and default videos (that Israel uploaded)
  
- open you android studio and clone the repository there
  
- open a new pixel 7 device with R
  
- run the app

# API Functionality:
The HemiTube website incorporates the following API endpoints and functionalities:

User:

- GET /api/users/:id Retrieve user details by providing the username.
- GET /api/users Retrieves all the users in the DB.
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

- in the signUp page, you will get an alert if you try to sign-up with an existing useranme ("invalid useranme" and 409 from the server), you will get a different alert if the fields are not filled properly.
- in the login page, you will get an alert if your password or username are incorrect.
- if the login worked (you filled a valid username and password), we will generate you a JWT, that will be used to monitor your access to things (like adding videos, comments, editing things etc')
- as a guest, you will have access to the videos and comments, without a possibility to edit or delete them. This way, we can protect the information etch user uploads. If you login, you can comment on a video by pressing the comment icon.
- you can search videos (by their title) using the search bar in the top of the page.
- also, you can go to user videos page if you press the link in the name of a publisher of an existing video, in this page, you can see all of this user videos, and enter them.
- as a registered user, you will have access to your profile page (where you can edit your profile photo and nickName)
- also, you will have the ability to add video (link from your pfoilr page to the upload video page, by pressing the upload button), delete your videos and to comment on existing videos.
- pay attention, when you delete a user, all of its videos and comments are deleted as well.
- when you add a user, video or a comment, it will be added to the data base. so if you refresh a page or even run the app all over again, you will see all of the things you uploaded.

<br />
<br />
<br />
<br />

# App Demo Run:

lets start from skretch, lets make sure we connected to mongodb://localhost:27017 and the database called "test" is clear from users and videos:
<br />
![Screenshot 2024-07-20 165753](https://github.com/user-attachments/assets/ea07795c-f28e-4983-bcb4-25f9e997b340)
![Screenshot 2024-07-20 165805](https://github.com/user-attachments/assets/3bdd4073-f61f-45ce-912d-98bbda34daed)
![Screenshot 2024-07-20 165810](https://github.com/user-attachments/assets/9be2a9cf-a0bf-4fd8-a0a2-708cb3815756)

then we open the vsc and made clone to the repository then we open a terminal and run the server:
<br />
![Screenshot 2024-07-20 165921](https://github.com/user-attachments/assets/3ad32c75-08c8-441d-91a0-31ed85d0c71b)
![Screenshot 2024-07-20 165936](https://github.com/user-attachments/assets/a98a0faf-b776-4abd-a6cc-c41a6938f363)


we open the web app by entering http://localhost:5001/ to the search bar on the browser, and checked to see if the default user and default videos created:
<br />
![Screenshot 2024-07-20 170012](https://github.com/user-attachments/assets/fcffdaf3-f0ef-4398-80c6-4aa28a76c664)

then, open this package on android studio:
<br />
![Screenshot 2024-07-20 170112](https://github.com/user-attachments/assets/7eaad993-7999-42d5-8534-f82da4ab697c)

and run the app, recommended device is google pixel 7:
<br />
![Screenshot 2024-07-20 170228](https://github.com/user-attachments/assets/5c9c2f25-a44a-4d1f-a16e-9cbb4fd41b8a)

we can notice that the same videos from the web are now fetching in the android app, now lets register a user:
<br />
![Screenshot 2024-07-20 170506](https://github.com/user-attachments/assets/4ca4d9f3-6ff1-4b5e-b08f-c8b943ef8577)

we can see that the user has been added succesfuly to the mongodb database:
<br />
![Screenshot 2024-07-20 170530](https://github.com/user-attachments/assets/d7811ebb-0ed7-402b-bd60-7fda911741c8)

now lets login to the "FooBar" user we created:
<br />
![Screenshot 2024-07-20 170607](https://github.com/user-attachments/assets/e010aa5b-b029-4e2e-9be7-38f52b84626e)

and that "YouPage", here the user can see his uploads:
<br />
![Screenshot 2024-07-20 170623](https://github.com/user-attachments/assets/7d5ec3f7-dbbc-4258-bed5-02a0ac4b7ba5)

now lets upload a video:
<br />
![Screenshot 2024-07-20 170653](https://github.com/user-attachments/assets/56785941-b406-495a-a623-4c04c5510aa6)

we can see that the video uploaded succesfuly, and now displays on YouPage
<br />
![Screenshot 2024-07-20 170709](https://github.com/user-attachments/assets/514cda93-eeb2-436d-9055-89bf95377c06)

the video also being displayed on the web application, as the task required:
<br />
![Screenshot 2024-07-20 170727](https://github.com/user-attachments/assets/9b0c873c-1b19-4c8f-8e5a-4e221b7f60d4)

now lets look at the VideoViewPage:
<br />
![Screenshot 2024-07-20 170841](https://github.com/user-attachments/assets/768e61e5-a973-499b-8129-f416f777b3c0)

Lets add a comment to this video:
<br />
![Screenshot 2024-07-20 170901](https://github.com/user-attachments/assets/b51be848-5ce6-4691-9f50-cde91c19fde9)

the comment has been added succefuly and being displayed:
<br />
![Screenshot 2024-07-20 170919](https://github.com/user-attachments/assets/9f392172-32bd-4cb2-a635-f6c48e39e913)

the user that uploaded the comment has the permission to edit or delete the comment, lets edit:
<br />
![Screenshot 2024-07-20 170942](https://github.com/user-attachments/assets/b6567c76-8c5f-4d9f-b35b-5f855051c255)
![Screenshot 2024-07-20 170950](https://github.com/user-attachments/assets/85f8adb5-46b2-4b7d-a53d-ac9fd93cce2e)

now lets delete the comment:
<br />
![Screenshot 2024-07-20 171003](https://github.com/user-attachments/assets/caee1c67-9e33-4437-b0ac-89538edcf00d)

another thing we added is a link to a specific user uploads, when you are watching a user video for example, Israel's video and want to see all his uploads you can click on his name on VideoViewPage and move to this page:
<br />
![Screenshot 2024-07-21 204308](https://github.com/user-attachments/assets/b7894fb4-27cf-40e9-b80e-8e6dacfda170)

we added a details page as well, the data that been displaying is the profilePic, username and nickname (the user can update his profilePic and nickname only!)
<br />
![Screenshot 2024-07-21 204337](https://github.com/user-attachments/assets/d49465c9-d9ec-4141-b53b-c6435d9d5144)

lets update the nickname for example:
<br />
![Screenshot 2024-07-21 204427](https://github.com/user-attachments/assets/8ca89c2f-95bb-44d1-893f-eef11a3a5404)

we can see that the nickname on the mongoDB changed as well:
<br />
![Screenshot 2024-07-21 204443](https://github.com/user-attachments/assets/05ccdfea-c89b-4599-99cc-771e723e6d05)

now lets delete the user we created (the deletion of the user delete also all his videos and comments):
<br />
![Screenshot 2024-07-21 204503](https://github.com/user-attachments/assets/852ef38e-fdb5-48da-b3a5-f3161128a2d3)

we can see that now we are on guest mode and the user videos has been deleted
<br />
![Screenshot 2024-07-21 204525](https://github.com/user-attachments/assets/6126d358-841c-4762-8ae2-a0178382a39d)

we can see that the user has been deleted from the mongoDB:
<br />
![Screenshot 2024-07-21 204536](https://github.com/user-attachments/assets/c9415385-ec1f-415c-acfc-bfabeba0f6a9)

and we can see that the user videos has been deleted from the web app as well:
<br />
![Screenshot 2024-07-21 204545](https://github.com/user-attachments/assets/daadd609-858f-48ef-94ab-c408bf06a411)

now we will see that the app also supports the functionality from part 1 of the project:
the dark mode is working:
<br />
![Screenshot 2024-07-21 204626](https://github.com/user-attachments/assets/09e52c7f-2991-47d2-bff9-8acd9b5da84a)

and the search bar working as expected:
<br />
![Screenshot 2024-07-21 204659](https://github.com/user-attachments/assets/c10fa415-e406-416a-9592-5f3f42222ee9)


