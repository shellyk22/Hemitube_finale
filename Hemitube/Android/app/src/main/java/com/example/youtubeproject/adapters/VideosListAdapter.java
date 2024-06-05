package com.example.youtubeproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.Video;
import com.example.youtubeproject.pages.VideoViewPage;

import java.util.List;

public class VideosListAdapter extends RecyclerView.Adapter<VideosListAdapter.VideoViewHolder> {

    class VideoViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView uploader;
        private final TextView views;

        private final TextView timePassed;
        private final ImageButton videoPic;


        private VideoViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.videoTitle);
            uploader = itemView.findViewById(R.id.videoUploader);
            views = itemView.findViewById(R.id.videoViews);
            timePassed = itemView.findViewById(R.id.videoPassedTime);
            videoPic = itemView.findViewById(R.id.videoPic);
        }


    }

    private final LayoutInflater mInflater;

    private List<Video> videos;

    public VideosListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.video_layout, parent, false);
        return new VideoViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position){
        if(videos != null){
            final Video current = videos.get(position);
            holder.title.setText(current.getTitle());
            holder.uploader.setText(current.getUploader() + " . ");
            holder.views.setText(current.getViews() + " views. ");
            holder.timePassed.setText(current.getTimePassedFromUpload() + " ago");
            holder.videoPic.setImageResource(current.getPic());
            holder.videoPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to start ActivityVideoViewPage
                    Context context = v.getContext();
                    Intent intent = new Intent(context, VideoViewPage.class);
                    // Pass additional data if needed
                    intent.putExtra("video_id", current.getId());
                    context.startActivity(intent);
                }
            });
        }
    }



    public void setVideos(List<Video> s){
        videos = s;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(videos != null){
            return videos.size();
        }
        else return 0;
    }

    public List<Video> getVideos() {
        return videos;
    }


}
