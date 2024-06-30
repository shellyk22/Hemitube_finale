import { render } from "@testing-library/react";

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

export async function setUserDetails(username, newPic, newnickName) {
    console.log("lmao: " + username);
    try {
        const res = await fetch(serverAddress + '/api/users/' + username, {
            'method': 'put',
            'headers': {
                'Content-Type': 'application/json',
                "authorization": 'Bearer ' + localStorage.getItem('JWT'),
            },
            'body': JSON.stringify({"newPic": newPic, "newNickName": newnickName}),
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
            'body': JSON.stringify({"userId": username}),
        });

        return res.status === 200 ? 'success' : 'error';
    } catch (error) {
        console.log('Error deleting user:', error);
        return 'error';
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

            console.log(resData);

            
            const userJWT = resData.token; //save the user's token.

            localStorage.setItem('userId', resData._id);
            localStorage.setItem('JWT', userJWT);
            localStorage.setItem('username', resData.username);
            localStorage.setItem('nickName', resData.nickName);
            localStorage.setItem('profilePic', resData.profilePic);

            console.log(localStorage.getItem)
            
            console.log({ userJWT, id: resData._id });
            return await getUserDetails(resData.username);
        } else {
            throw new Error (res.status === 404 ? 'Username or password does not match.' :
                "Ooopss! We've run into a problem :(\nPlease try again later");
        }

    } catch (error) {
        console.log('Error fetching messages:', error);
        throw new Error("Ooopss! We've run into a problem :(\nPlease try again later");
    }
}