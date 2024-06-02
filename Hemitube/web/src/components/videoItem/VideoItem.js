function VideoItem({ title, author, views, time, img}){
    return (
        <div className="card col-xl-2 col-lg-3 col-md-4 col-sm-6 border-0 p-1">
              <img src={img} className="card-img-top" alt="..."></img>
              <div className="card-body">
                <p className="card-title">{title}</p>
                <p className="card-author">{author}</p>
                <p className="card-text">{views} views. {time} ago</p>
              </div>
            </div>
    );
}

export default VideoItem;