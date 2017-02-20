/*
 * Copyright (c) 2016.   Sss
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sunshaoshuai.retrofitokhttpdemo;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.materialdesign.sss.com.retrofit2okhttp3.DownloadCallBack;
import demo.materialdesign.sss.com.retrofit2okhttp3.HttpUrl;
import demo.materialdesign.sss.com.retrofit2okhttp3.NoActionAjaxCallBack;
import demo.materialdesign.sss.com.retrofit2okhttp3.RetrofitInstance;
import demo.materialdesign.sss.com.retrofit2okhttp3.RetrofitService;
import demo.materialdesign.sss.com.retrofit2okhttp3.SssAjaxCallBack;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String 要上传的文件的路径;
    private String 额外的参数对象;
    private String 额外的参数名;
    private String 你的接口相对路径;
    private String Get方式请求文件路径;
    private String Post方式请求文件的方法;

    private String DOMAIN_NAME = HttpUrl.DOMAIN_NAME;
    private String TAG = "MainActivity";
    private RetrofitInstance retrofit = RetrofitInstance.getInstance();

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
        findViewById(R.id.Retrofit_okhttp_post).setOnClickListener(this);
        findViewById(R.id.Retrofit_okhttp_get_download).setOnClickListener(this);
        findViewById(R.id.Retrofit_okhttp_get_download_parameter).setOnClickListener(this);
        findViewById(R.id.Retrofit_okhttp_post_download).setOnClickListener(this);
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
            case R.id.Retrofit_okhttp_post:
                onRetrofitOkhttpPost();
                break;
            case R.id.Retrofit_okhttp_get_download:
                onRetrofitGetDownload();
                break;
            case R.id.Retrofit_okhttp_get_download_parameter:
                onRetrofitGetDownload2();
                break;
            case R.id.Retrofit_okhttp_post_download:
                onRetrofitPostDownload();
                break;
        }
    }

    /**
     * 异步Retrofit Post 下载
     */
    private void onRetrofitPostDownload() {
        Map<String, Object> 传递的参数 = new HashMap<>();
        String 参数名 = "";
        String 参数值 = "";
        传递的参数.put(参数名, 参数值);
        retrofit.downloadForPost(Post方式请求文件的方法, 传递的参数, new DownloadCallBack() {

            @Override
            public void onReceiveData(InputStream inputStream) {
                // TODO: 2017/2/20 下载成功
                Log.e("下载", "下载成功");
            }

            @Override
            public void onConnectServerFailed(Call call, Throwable t) {
                // TODO: 2017/2/20 下载失败
                Log.e("下载", "下载失败");
            }
        });

    }

    /**
     * 异步Retrofit Get 下载
     */
    private void onRetrofitGetDownload2() {
        Retrofit retrofit;
        OkHttpClient client;
        RetrofitService service;
        Call<ResponseBody> bodyCall;
        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request requestOrigin = chain.request();
                        Request authorised = requestOrigin.newBuilder()
                                .header("EEXUU-Token", "4327B96D91AC3875D47237CC3F9824653F7C4D380CA4899146BD20870DA31F2DF06F08D5E90DB4665C91F359E7330479AA7181C2D710A5F0E7A112BA5EA9E65101B3575570671EFA63FFB5FEB53F76F92AF2564723EDB1490D916FFB8C27DC543D3B5D47EAC5162F30F68A25EC16AECE43B7F3380A7317D358E705F96FDB6F77DFE49B3C57B8578FC5EEA1BCC0D4EE442A529686175322F07BF12F07972FD722D59C17D9F0DCB2374772F4FB0A6F348CE4A3C7CA44BB152F84A5B544C419D59F421790524381730F5C279A5F9206CE40")
                                .build();
                        Response response = chain.proceed(authorised);
                        return response;
                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.eexuu.com")
                .client(client)
                .build();
        service = retrofit.create(RetrofitService.class);
        Map<String, String> map = new HashMap<>();
        map.put("md5", "d8cb9e819f3f711b7beed1f56db6c31f");
        bodyCall = service.downloadForGet("api/Resource/DownloadResource", map);
        bodyCall.enqueue(new DownloadCallBack() {


            @Override
            public void onReceiveData(InputStream inputStream) {
                try {
                    //获取文件总长度
                    long totalLength = inputStream.available();

                    String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "/eexuu";
                    File file = new File(path, "download.jpg");
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, length);
                        //此处进行更新操作
                        //length为文件的已下载的字节数
                        //如 onDownloading(totalLength,length);

                    }
                    fos.flush();
                    fos.close();
                    bis.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConnectServerFailed(Call call, Throwable t) {
                // TODO: 2017/2/20 下载失败
                Log.e("下载", "下载失败");
            }
        });

//        Map<String, String> 传递的参数 = new HashMap<>();
//        String 参数名 = "";
//        String 参数值 = "";
//        传递的参数.put(参数名, 参数值);
//        this.retrofit.downloadForGet(Get方式请求文件路径, 传递的参数, new DownloadCallBack() {
//
//            @Override
//            public void onReceiveData(InputStream inputStream) {
//                // TODO: 2017/2/20 下载成功
//                Log.e("下载", "下载成功");
//            }
//
//            @Override
//            public void onConnectServerFailed(Call call, Throwable t) {
//                // TODO: 2017/2/20 下载失败
//                Log.e("下载", "下载失败");
//            }
//        });


    }

    /**
     * 异步Retrofit Get 下载
     */
    private void onRetrofitGetDownload() {
        retrofit.downloadForGet(Get方式请求文件路径, new DownloadCallBack() {

            @Override
            public void onReceiveData(InputStream inputStream) {
                // TODO: 2017/2/20 下载成功
                Log.e("下载", "下载成功");
            }

            @Override
            public void onConnectServerFailed(Call call, Throwable t) {
                // TODO: 2017/2/20 下载失败
                Log.e("下载", "下载失败");
            }
        });
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
                    String response = example.post(DOMAIN_NAME + 你的接口相对路径, json);
                    Log.e(TAG, "" + response.toString());//不知道为何就是不打印，当数据是有的
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 异步Retrofit2.0+okhtttp3.0 post请求
     */
    private void onRetrofitOkhttpPost() {
        Map<String, Object> map = new HashMap<>();
        map.put("MobilePhone", "18618490056");
        map.put("Password", "654321");
      /*
        数据返回后需要进行操作时使用SssAjaxCallBack对象
        不需要操作用NoActionAjaxCallBack对象
        如：
        doJsonPost("你的接口相对路径", json, new NoActionAjaxCallBack());
       */
        doJsonPost(你的接口相对路径, map, new SssAjaxCallBack() {
            @Override
            public void onReceiveData(String data, String msg) {
                // TODO: 16/9/18  成功时的回调
                Log.e(TAG, "成功时的回调");
            }

            @Override
            public void onReceiveError(int errorCode, String msg) {
                // TODO: 16/9/18 失败时的回调
                Log.e(TAG, "失败时的回调");
            }

            @Override
            public void onConnectServerFailed(Call call, Throwable t) {
                // TODO: 16/9/18 请求失败的回调
                Log.e(TAG, "请求失败的回调");
            }
        });


    }

    /**
     * 以json数据格式post方式提交数据
     *
     * @param path     路径
     * @param data     转成json字符串的参数集
     * @param callBack 回调
     * @return
     */
    private void doJsonPost(String path, @NonNull String data, NoActionAjaxCallBack callBack) {
        retrofit.jsonPost(path, data, callBack);
    }

    /**
     * 以json数据格式post方式提交数据
     *
     * @param path     路径
     * @param map      参数集
     * @param callBack 回调
     * @return
     */
    private void doJsonPost(String path, Map<String, Object> map, NoActionAjaxCallBack callBack) {
        retrofit.jsonPost(path, map, callBack);
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
        File file = new File(要上传的文件的路径);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        //创建额外的参数列表
        Map<String, RequestBody> params = new HashMap<>();
        requestBody = RequestBody.create(MediaType.parse("text/plain"), 额外的参数对象);
        params.put(额外的参数名, requestBody);

        Call<JsonObject> call = service.fromPost(你的接口相对路径, part, params);

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
