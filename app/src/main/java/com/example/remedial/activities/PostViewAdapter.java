package com.example.remedial.activities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remedial.R;
import com.example.remedial.dto.Post;

import java.util.List;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {
    private List<Post> posts;

    public PostViewAdapter(List<Post> posts){
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_post_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        public ViewHolder(View view){
            super(view);
            this.view = view;
        }
        public void bind(Post post){
            TextView title = view.findViewById(R.id.titlePost);
            title.setText(post.getTitle());
            view.setOnClickListener((view) -> {
                Intent intent = new Intent(view.getContext(), PostDetailsActivity.class);
                PostListActivity.postIdTarget = post.getId();
                view.getContext().startActivity(intent);
            });
        }
    }
}
