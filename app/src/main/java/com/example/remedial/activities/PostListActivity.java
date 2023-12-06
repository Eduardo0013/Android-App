package com.example.remedial.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.remedial.R;
import com.example.remedial.adapters.WebApiAdapter;
import com.example.remedial.dto.Post;
import com.example.remedial.services.WebApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private static List<Post> posts = new ArrayList<>();
    private WebApiService webApiService;
    private RecyclerView recyclerView;
    public static int userIdTarget;
    public static int postIdTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        PostViewAdapter adapter = new PostViewAdapter(posts);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerPost);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        webApiService = WebApiAdapter.getWebApiService();
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId",0);
        userIdTarget = userId;
        webApiService.getPosts(userId).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    posts.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                Toast.makeText(getApplicationContext(),"Code: "+response.code(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}