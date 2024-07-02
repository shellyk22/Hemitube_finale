import { render } from "@testing-library/react";
import { createVideo, addDefaultVideo } from "./videos";

export const serverAddress = 'http://localhost:5001'

export function setUserJWT(newJWT) {
    localStorage.setItem('JWT', newJWT);
}

export function logOut() {
    localStorage.setItem('userId', "undefined");
    localStorage.setItem('JWT', "null");
    localStorage.setItem('username', "undefined");
    localStorage.setItem('nickName', "undefined");
    localStorage.setItem('profilePic', "undefined");
    window.location.reload();
}

/**
 * Register a new user.
 */
export async function registerUser(username, password, nickName, profilePic) {
    try {
        const userData = {
            "username": username,
            "password": password,
            "nickName": nickName,
            "profilePic": profilePic
        };

        const res = await fetch(serverAddress + '/api/users', {
            'method': 'post',
            'headers': {
                'Content-Type': 'application/json',
            },
            'body': JSON.stringify(userData)
        });

        if (res.status === 200) {
            return 'success';
        } else if (res.status === 409) {
            return 'Username already exists.';
        }

        return "Ooopss! We've run into a problem :(\nPlease try again later";
    } catch (error) {
        console.log('Error fetching messages:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}


/**
 * Returns the user's data (username, displayName, profile-pic)
 */
async function getUserDetails(username) {
    try {
        const res = await fetch(serverAddress + '/api/users/' + username, {
            'method': 'get',
            'headers': {
                'Content-Type': 'application/json',
                "authorization": 'Bearer ' + localStorage.getItem('JWT'),
            }
        });
        if (res.status === 200) {
            return await res.json();
        } else return "Ooopss! We've run into a problem :(\nPlease try again later";
    } catch (error) {
        console.log('Error fetching messages:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}


export async function addDeafault() {
    const defaultUsers = [
        { username: 'Israel', password: 'Israel1948', nickName: 'Israel', profilePic: 'data:image/jpeg;base64,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' },
        //{ username: 'Hemi', password: 'Hemi2024', nickName: 'Hemi', profilePic: 'data:image/jpeg;base64,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' }
    ];

    defaultUsers.forEach(async (userData) => {
        try {
            const existingUser = await getUserDetails(userData.username);
            if (existingUser === null) {
                await registerUser(userData.username, userData.password, userData.nickName, userData.profilePic)
            }
            console.log('Deafault user added')
        }
        catch (error) {
            console.error('Error adding default user');
        }
    });

    const { id } = await getUserDetails("Israel");
    //const HemiData = await getUserDetails("Hemi");
    console.log(id);

    const defaultVideos = [
        {
            title: "Foo",
            description: "vid 1",
            publisher: id,
            uploadDate: new Date(),
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "public/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "public/images/ingdogs2.jpg",
            commentsArr: []
        }/*,
        {
            title: "Amazing",
            description: "vid 2",
            publisher: IsraelData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        },
        {
            title: "Niceeee",
            description: "vid 3",
            publisher: IsraelData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        },
        {
            title: "Foo",
            description: "vid 4",
            publisher: HemiData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        },
        {
            title: "Bar",
            description: "vid 5",
            publisher: IsraelData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        },
        {
            title: "Bar",
            description: "vid 6",
            publisher: IsraelData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        },
        {
            title: "Bar",
            description: "vid 7",
            publisher: HemiData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        },
        {
            title: "Bar",
            description: "vid 8",
            publisher: IsraelData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        },
        {
            title: "Bar",
            description: "vid 9",
            publisher: HemiData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        },
        {
            title: "BarBar",
            description: "vid 10",
            publisher: IsraelData._id,
            uploadDate: new Date,
            file_name: "vidTest.mp4",
            thumbnail_name: "img4.jpg",
            file_data: "/videos/5227413-uhd_3840_2160_24fps.mp4",
            thumbnail_data: "/images/ingdogs2.jpg",
            commentsArr: []
        }*/
    ]

    defaultVideos.forEach(async (videoData) => {
        const formData = new FormData();
        formData.append('publisher', videoData.publisher);
        formData.append('title', videoData.title);
        formData.append('description', videoData.description);
        formData.append('file_name', videoData.file_name);
        formData.append('file_data', videoData.file_data);
        formData.append('thumbnail_name', videoData.thumbnail_name);
        formData.append('thumbnail_data', videoData.thumbnail_data);
        await addDefaultVideo(formData);
    });
    
}









export async function setUserDetails(username, newPic, newnickName) {
    try {
        const res = await fetch(serverAddress + '/api/users/' + username, {
            'method': 'put',
            'headers': {
                'Content-Type': 'application/json',
                "authorization": 'Bearer ' + localStorage.getItem('JWT'),
            },
            'body': JSON.stringify({ "newPic": newPic, "newNickName": newnickName }),
        });

        return res.status === 200 ? 'success' : 'error';
    } catch (error) {
        console.log('Error fetching messages:', error);
        return 'error';
    }
}



export async function deleteUser(username) {
    console.log("lmao: " + username);
    try {
        const res = await fetch(serverAddress + '/api/users/' + username, {
            'method': 'delete',
            'headers': {
                'Content-Type': 'application/json',
                "authorization": 'Bearer ' + localStorage.getItem('JWT'),
            },
            'body': JSON.stringify({ "userId": username }),
        });

        return res.status === 200 ? 'success' : 'error';
    } catch (error) {
        console.log('Error deleting user:', error);
        return 'error';
    }
}


export async function fetchUsers() {
    try {
        const res = await fetch(`${serverAddress}/api/users`, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'Bearer ' + localStorage.getItem('JWT'),
            },
        });

        if (res.status === 200) {
            return await res.json();
        } else {
            throw new Error("Error fetching videos");
        }
    } catch (error) {
        console.log('Error fetching users:', error);
        return "Ooopss! We've run into a big big problem :(\nPlease try again later";
    }
}




/**
 * Logs in a username.
 */
export async function loginUser(username, password) {
    try {
        const userData = {
            "username": username,
            "password": password
        }

        const res = await fetch(serverAddress + '/api/tokens', {
            'method': 'post',
            'headers': {
                'Content-Type': 'application/json',
            },
            'body': JSON.stringify(userData)
        });

        if (res.status === 200) {
            const resData = await res.json();



            const userJWT = resData.token; //save the user's token.

            localStorage.setItem('userId', resData._id);
            localStorage.setItem('JWT', userJWT);
            localStorage.setItem('username', resData.username);
            localStorage.setItem('nickName', resData.nickName);
            localStorage.setItem('profilePic', resData.profilePic);


            return await getUserDetails(resData.username);
        } else {
            throw new Error(res.status === 404 ? 'Username or password does not match.' :
                "Ooopss! We've run into a problem :(\nPlease try again later");
        }

    } catch (error) {
        console.log('Error fetching messages:', error);
        throw new Error("Ooopss! We've run into a problem :(\nPlease try again later");
    }
}