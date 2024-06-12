package com.example.youtubeproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.Comment;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.Video;

import java.util.List;

public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.CommentViewHolder> {

    private Video video;

    private final SessionManager sessionManager = SessionManager.getInstance();

    class CommentViewHolder extends RecyclerView.ViewHolder {

        private final TextView uploader;

        private final TextView timePassed;
        private final TextView content;

        private final Button btnDelete;



        private CommentViewHolder(View itemView) {
            super(itemView);
            uploader = itemView.findViewById(R.id.commentUsername);
            timePassed = itemView.findViewById(R.id.commentUploadDate);
            content = itemView.findViewById(R.id.commentText);
            btnDelete = itemView.findViewById(R.id.deleteBtn);

        }


    }

    private final LayoutInflater mInflater;

    private List<Comment> commentlist;

    public CommentsListAdapter(Context context, Video video) {
        mInflater = LayoutInflater.from(context);
        this.video = video;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.comment_layout, parent, false);
        return new CommentViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        if (commentlist != null) {
            final Comment current = commentlist.get(position);
            holder.uploader.setText(current.getUploader() + " . ");
            holder.timePassed.setText(current.getTimePassedFromUpload() + " ago");
            holder.content.setText(current.getContent());

            holder.btnDelete.setOnClickListener(v -> {
                if (sessionManager.getLoggedUser().getUsername().equals(current.getUploader())) {
                    sessionManager.deleteComment(current, video);
                    this.notifyDataSetChanged();
                }
            });

            if(sessionManager.isLogedIn()){
                if(sessionManager.getLoggedUser().getUsername().equals(current.getUploader())){
                    holder.btnDelete.setVisibility(View.VISIBLE);
                }
                else
                    holder.btnDelete.setVisibility(View.GONE);
            }else {
                holder.btnDelete.setVisibility(View.GONE);
            }

        }
    }


    public void setComments(List<Comment> s) {
        commentlist = s;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (commentlist != null) {
            return commentlist.size();
        } else return 0;
    }

    public List<Comment> getCommentlist() {
        return commentlist;
    }
}
