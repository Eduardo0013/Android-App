package com.example.remedial.activities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remedial.R;
import com.example.remedial.dto.User;

import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewAdapter.ViewHolder> {
    private List<User> users;

    public UserViewAdapter(List<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_user_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        public ViewHolder(View view){
            super(view);
            this.view = view;
        }
        public void bind(User user){
            TextView title = view.findViewById(R.id.title);
            title.setText(user.getUsername());
            title.setOnClickListener((view) -> {
               for(User us : UsersActivity.users){
                    if(us.getUsername().equals(title.getText())){
                        UsersActivity.userIdTarget = us.getId();
                        Intent intent = new Intent(view.getContext(),PostListActivity.class);
                        intent.putExtra("userId",user.getId());
                        view.getContext().startActivity(intent);
                    }
               }
            });
        }
    }
}
