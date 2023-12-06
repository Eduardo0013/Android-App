package com.example.remedial.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remedial.R;
import com.example.remedial.adapters.WebApiAdapter;
import com.example.remedial.dto.Post;
import com.example.remedial.services.WebApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailsActivity extends AppCompatActivity {
    private TextView title;
    private TextView emailTxt;
    private TextView webSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_post_detail_item);
        title = findViewById(R.id.titleCard);
        emailTxt = findViewById(R.id.emailTxt);
        webSite = findViewById(R.id.webSiteTxt);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.cardProgress);
        progressBar.setVisibility(View.VISIBLE);

        WebApiService webApiService = WebApiAdapter.getWebApiService();
        webApiService.getPost(PostListActivity.userIdTarget,PostListActivity.postIdTarget).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Post post = response.body();
                    title.setText(post.getTitle());
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                Toast.makeText(getApplicationContext(),"Code: "+response.code(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}