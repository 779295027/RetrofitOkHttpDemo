package com.example.sunshaoshuai.retrofitokhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String DOMAIN_NAME = "你的服务器";
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Retrofit_Post).setOnClickListener(this);
        findViewById(R.id.Retrofit_Post_two).setOnClickListener(this);
        findViewById(R.id.OKHttp_Post).setOnClickListener(this);
        findViewById(R.id.OKHttp_Get).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Retrofit_Post:
                onRetrofitJsonPost();
                break;
            case R.id.Retrofit_Post_two:
                onRetrofitJsonPostTwo();
                break;
            case R.id.OKHttp_Post:
                onOkHttpPost();
                break;
            case R.id.OKHttp_Get:
                onOkHttpGet();
                break;
        }
    }

    private void onOkHttpGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GetExample example = new GetExample();
                    String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
                    Log.e(TAG, "" + response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void onOkHttpPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostExample example = new PostExample();
                    Map<String, Object> map = new HashMap<>();
                    map.put("MobilePhone", "18618490056");
                    map.put("Password", "654321");
                    String json = new Gson().toJson(map);
                    String response = example.post(DOMAIN_NAME + "你的接口相对路径", json);
                    Log.e(TAG, "" + response.toString());//不知道为何就是不打印，当数据是有的
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void onRetrofitJsonPostTwo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())//解析方法
                        .baseUrl(DOMAIN_NAME)//主机地址
                        .build();
                //2.创建访问API的请求
                GitHubService service = retrofit.create(GitHubService.class);
                UserApi userApi = new UserApi();
                userApi.setMobilePhone("18618490056");
                userApi.setPassword("654321");
                Call<User> call = service.login(userApi);
                //3.发送请求
                try {
                    retrofit2.Response<User> response = call.execute();
                    Log.e(TAG, "" + response.message() + "---" + response.body() + "" + call.clone().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private void onRetrofitJsonPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DOMAIN_NAME)
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        UserApi userApi = new UserApi();
        userApi.setMobilePhone("18618490056");
        userApi.setPassword("654321");
        Call<User> call = service.login(userApi);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                response.isSuccessful();
                if (response.isSuccessful()) {
                    Log.i(TAG, "success!!!" +
                            call.getClass().toString());
                    Gson gson = new Gson();
                    Log.i(TAG, "---" + gson.toJson(response.body()));
                } else {
                    Log.e(TAG, "+++" + response.message());
                }
                Log.e("", "" + response.message() + "---" + response.body() + "" + call.clone().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(call.toString() + "onFailure");
                Log.e("", "" + call.toString());
            }
        });
    }


}
