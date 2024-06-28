export const serverAddress = 'http://localhost:5001';

export async function fetchVideos() {
    try {
        const res = await fetch(`${serverAddress}/api/videos`, {
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
        console.log('Error fetching videos:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}

export async function fetchVideoById(videoId) {
    try {
        const res = await fetch(`${serverAddress}/api/videos/${videoId}`, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'Bearer ' + localStorage.getItem('JWT'),
            },
        });

        if (res.status === 200) {
            return await res.json();
        } else {
            throw new Error("Error fetching video");
        }
    } catch (error) {
        console.log('Error fetching video:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}

export async function createVideo(title, publisher = [null], comments = [], file = null) {
    try {
        const videoData = { title, publisher, comments, file };
        const res = await fetch(`${serverAddress}/api/videos`, {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'Bearer ' + localStorage.getItem('JWT'),
            },
            body: JSON.stringify(videoData),
        });

        if (res.status === 201) {
            return await res.json();
        } else {
            throw new Error("Error creating video");
        }
    } catch (error) {
        console.log('Error creating video:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}

export async function updateVideo(videoId, updateData) {
    try {
        const res = await fetch(`${serverAddress}/api/videos/${videoId}`, {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'Bearer ' + localStorage.getItem('JWT'),
            },
            body: JSON.stringify(updateData),
        });

        if (res.status === 200) {
            return await res.json();
        } else {
            throw new Error("Error updating video");
        }
    } catch (error) {
        console.log('Error updating video:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}

export async function deleteVideo(videoId) {
    try {
        const res = await fetch(`${serverAddress}/api/videos/${videoId}`, {
            method: 'delete',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'Bearer ' + localStorage.getItem('JWT'),
            },
        });

        if (res.status === 200) {
            return await res.json();
        } else {
            throw new Error("Error deleting video");
        }
    } catch (error) {
        console.log('Error deleting video:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}

export async function fetchVideosByUserId(userId) {
    try {
        const res = await fetch(`${serverAddress}/api/users/${userId}/videos`, {
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
        console.log('Error fetching videos:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}

export async function addCommentToVideo(videoId, content) {
    try {
        const commentData = { videoId, content };
        const res = await fetch(`${serverAddress}/api/videos/comments`, {
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'Bearer ' + localStorage.getItem('JWT'),
            },
            body: JSON.stringify(commentData),
        });

        if (res.status === 201) {
            return await res.json();
        } else {
            throw new Error("Error adding comment");
        }
    } catch (error) {
        console.log('Error adding comment:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}

export async function fetchCommentsByVideoId(videoId) {
    try {
        const res = await fetch(`${serverAddress}/api/videos/${videoId}/comments`, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',
                'authorization': 'Bearer ' + localStorage.getItem('JWT'),
            },
        });

        if (res.status === 200) {
            return await res.json();
        } else {
            throw new Error("Error fetching comments");
        }
    } catch (error) {
        console.log('Error fetching comments:', error);
        return "Ooopss! We've run into a problem :(\nPlease try again later";
    }
}