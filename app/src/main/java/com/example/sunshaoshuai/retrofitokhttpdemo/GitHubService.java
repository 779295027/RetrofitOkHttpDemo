package com.example.sunshaoshuai.retrofitokhttpdemo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by sunshaoshuai on 16/8/18.
 */
public interface GitHubService {

    @POST("你的接口相对路径")
    Call<User> login(@Body UserApi userApi);

    @Multipart
    @POST("你的接口相对路径")
    Call<User> login(@Part String userApi);

}
