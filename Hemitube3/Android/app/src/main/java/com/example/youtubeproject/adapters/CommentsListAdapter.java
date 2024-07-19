package com.example.youtubeproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeproject.R;
import com.example.youtubeproject.entities.Comment;
import com.example.youtubeproject.entities.SessionManager;
import com.example.youtubeproject.entities.User;
import com.example.youtubeproject.viewmodels.UserViewModel;

import java.util.List;

public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.CommentViewHolder> {

    private final LayoutInflater mInflater;
    private List<Comment> commentList;
    private List<User> users;
    private final LiveData<List<User>> usersLiveData;
    private final LifecycleOwner lifecycleOwner;

    public CommentsListAdapter(Context context, UserViewModel userViewModel, LifecycleOwner lifecycleOwner) {
        mInflater = LayoutInflater.from(context);
        this.usersLiveData = userViewModel.getAllUsers();
        this.lifecycleOwner = lifecycleOwner;

        // Observe the specific LiveData list
        usersLiveData.observe(lifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                users = userList;
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.comment_layout, parent, false);
        return new CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        if (commentList != null) {
            Comment currentComment = commentList.get(position);
            String userId = currentComment.getUserId();

            // Find the username by user ID
            String username = getUsernameById(userId);
            if (username != null) {
                holder.uploader.setText(username);
            } else {
                holder.uploader.setText("Unknown User");
            }

            holder.content.setText(currentComment.getText());

            holder.btnDelete.setOnClickListener(v -> {
                if (SessionManager.getInstance().getLoggedUser().getId().equals(currentComment.getUserId())) {
                    commentList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, commentList.size());
                }
            });

            if (SessionManager.getInstance().isLogedIn()) {
                if (SessionManager.getInstance().getLoggedUser().getId().equals(currentComment.getUserId())) {
                    holder.btnDelete.setVisibility(View.VISIBLE);
                } else {
                    holder.btnDelete.setVisibility(View.GONE);
                }
            } else {
                holder.btnDelete.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return commentList != null ? commentList.size() : 0;
    }

    public void setComments(List<Comment> comments) {
        this.commentList = comments;
        notifyDataSetChanged();
    }
    public void addComment(Comment comment) {
        if (commentList != null) {
            commentList.add(comment);
            notifyItemInserted(commentList.size() - 1);
        }
    }

    private String getUsernameById(String userId) {
        if (users != null) {
            for (User user : users) {
                if (user.getId().equals(userId)) {
                    return user.getUsername();
                }
            }
        }
        return null;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private final TextView uploader;
        private final TextView content;
        private final Button btnDelete;

        private CommentViewHolder(View itemView) {
            super(itemView);
            uploader = itemView.findViewById(R.id.commentUsername);
            content = itemView.findViewById(R.id.commentText);
            btnDelete = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
