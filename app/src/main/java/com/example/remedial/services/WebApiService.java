package com.example.remedial.services;

import com.example.remedial.dto.Post;
import com.example.remedial.dto.User;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebApiService {
    @GET("users")
    Call<List<User>> getUsers();
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") int userId);

    @GET("posts")
    Call<Post> getPost(@Query(("userId")) int userId,@Query("id") int postId);
}
