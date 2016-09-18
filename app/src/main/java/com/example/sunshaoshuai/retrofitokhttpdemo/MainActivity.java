package com.example.sunshaoshuai.retrofitokhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String DOMAIN_NAME = "http://v2api20160516-test.chinacloudsites.cn";
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.Retrofit_Post).setOnClickListener(this);
        findViewById(R.id.Retrofit_Post_two).setOnClickListener(this);
        findViewById(R.id.Retrofit_Post_three).setOnClickListener(this);
        findViewById(R.id.OKHttp_Post).setOnClickListener(this);
        findViewById(R.id.OKHttp_Get).setOnClickListener(this);
        findViewById(R.id.Retrofit_From_upload).setOnClickListener(this);
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
            case R.id.Retrofit_Post_three:
                onRetrofitJsonPostThree();
                break;
            case R.id.OKHttp_Post:
                onOkHttpPost();
                break;
            case R.id.OKHttp_Get:
                onOkHttpGet();
                break;
            case R.id.Retrofit_From_upload:
                onFromUpload();
                break;
        }
    }

    /**
     * OKHttp Get
     */
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

    /**
     * OKHttp Post
     */
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

    /**
     * 异步Retrofit From表单格式Post上传
     */
    private void onFromUpload() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl(DOMAIN_NAME)//主机地址
                .build();
        //2.创建访问API的请求
        GitHubService service = retrofit.create(GitHubService.class);

        //创建上传对象
        File file = new File("要上传的文件的路径");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        //创建额外的参数列表
        Map<String, RequestBody> params = new HashMap<>();
        requestBody = RequestBody.create(MediaType.parse("text/plain"), "额外的参数对象");
        params.put("额外的参数名", requestBody);

        Call<JsonObject> call = service.fromPost("相对路径", part, params);

        //3.发送请求
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                response.isSuccessful();
                if (response.isSuccessful()) {
                    Log.i(TAG, "success!!!");
                    Log.i(TAG, "---" + response.body().toString());
                } else {
                    Log.e(TAG, "+++" + response.message());
                }
                Log.e("", "" + response.message() + "---" + response.body() + "" + call.clone().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println(call.toString() + "onFailure");
                Log.e("", "" + call.toString());
            }
        });

    }


    /**
     * 同步Retrofit Post JsonObject返回格式
     */
    private void onRetrofitJsonPostThree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())//解析方法
                        .baseUrl(DOMAIN_NAME)//主机地址
                        .build();
                //2.创建访问API的请求
                GitHubService service = retrofit.create(GitHubService.class);
                Map<String, String> us = new HashMap<>();
                us.put("MobilePhone", "18618490056");
                us.put("Password", "654321");
                Call<JsonObject> call = service.post(us);
                //3.发送请求
                try {
                    retrofit2.Response<JsonObject> response = call.execute();
                    Log.e(TAG, "" + response.message() + "---" + response.body() + "" + call.clone().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 同步Retrofit Post
     */
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
                Map<String, String> us = new HashMap<>();
                us.put("MobilePhone", "18618490056");
                us.put("Password", "654321");
                Call<User> call = service.login(us);
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

    /**
     * 异步Retrofit Post
     */
    private void onRetrofitJsonPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DOMAIN_NAME)
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Map<String, String> us = new HashMap<>();
        us.put("MobilePhone", "18618490056");
        us.put("Password", "654321");
        Call<User> call = service.login(us);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                response.isSuccessful();
                if (response.isSuccessful()) {
                    Log.i(TAG, "success!!!");
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
