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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.materialdesign.sss.com.retrofit2okhttp3.DownloadCallBack;
import demo.materialdesign.sss.com.retrofit2okhttp3.FileResponseBody;
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
     * 异步Retrofit Get 下载 2
     */
    private void onRetrofitGetDownload2() {
        //初始化當前長度
        當前長度=new BigDecimal(0);
        // TODO: 2017/2/20  顯示下載進度有兩種方法，onRetrofitGetDownload同理，也有兩種，方法是一樣，所以就不一一贅述了
//        方法一();
        方法二();


    }
    private void 方法一(){
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
                                .header(HttpUrl.TOKEN_NAME, "")
                                .build();
                        Response response = chain.proceed(authorised);
                        return response;
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        //将ResponseBody转换成我们需要的FileResponseBody
                        return response.newBuilder().body(new FileResponseBody(response.body(), new DownloadCallBack() {
                            @Override
                            public void onDownloading(long totalLength, long length) {
                                // TODO: 2017/2/20 在此进行文件下载进度的更新（不推荐使用此方法，因爲這裏還需要在重寫一遍請求，或者直接在這裏進行判斷是否為下載）

                            }
                        })).build();

                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HttpUrl.DOMAIN_NAME)
                .client(client)
                .build();
        service = retrofit.create(RetrofitService.class);
        Map<String, String> map = new HashMap<>();
        String 參數名="";
        String 參數值="";
        map.put(參數名, 參數值);
        bodyCall = service.downloadForGet(你的接口相对路径, map);
        bodyCall.enqueue(new DownloadCallBack() {

            @Override
            public void onReceiveData(InputStream inputStream) {
                try {
                    Log.e("下载", "下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConnectServerFailed(Call call, Throwable t) {
                // TODO: 2017/2/20 下载失败
                Log.e("下载", "下载失败");
            }
        });
    }

    private void 方法二(){
        Map<String, String> 传递的参数 = new HashMap<>();
        String 参数名 = "";
        String 参数值 = "";
        传递的参数.put(参数名, 参数值);
        this.retrofit.downloadForGet(你的接口相对路径, 传递的参数, new DownloadCallBack() {

            @Override
            public void onReceiveData(InputStream inputStream) {
                // TODO: 2017/2/20 下载成功
                try {
                    saveFile(inputStream);
                    Log.e("下载", "下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConnectServerFailed(Call call, Throwable t) {
                // TODO: 2017/2/20 下载失败
                Log.e("下载", "下载失败");
            }
        });
    }

    /**
     * 保存文件到本地
     * @param inputStream 數據流
     * @throws Exception 保存時可能出現的異常 一般為IOException，為防止可能會出現NullPointerException，所以這裏直接抛出Exception
     */
    private void  saveFile(InputStream inputStream) throws Exception{
        //获取文件总长度
        long totalLength = inputStream.available();

        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "";
        File file = new File(path, "download.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = bis.read(buffer)) != -1) {
            fos.write(buffer, 0, length);
            //此处进行更新操作
            //length为文件的已下载的字节数
            MainActivity.this.onDownloading(totalLength,length);
        }
        fos.flush();
        fos.close();
        bis.close();
        inputStream.close();
    }

    private BigDecimal 當前長度=new BigDecimal(0);
    /**
     * 下載進度
     * todo 這裏進行進度條的更新(我就偷個懒，log日志輸出一下算了)
     *
     *
     * @param totalLength
     * @param length
     */
    private void onDownloading(long totalLength, int length){
        BigDecimal 總長度=new BigDecimal(totalLength);
        BigDecimal 叠加長度=new BigDecimal(length);
        當前長度=當前長度.add(叠加長度);
        Double 百分比=  當前長度.divide(總長度,4,BigDecimal.ROUND_HALF_UP).doubleValue();
        Log.d("下載中","總長度"+總長度+"\n"+ "當前長度"+當前長度+"\n"+"百分比"+ (百分比*100)  +"%");
    }

    /**
     * 异步Retrofit Get 下载
     */
    private void onRetrofitGetDownload() {
        retrofit.downloadForGet(Get方式请求文件路径, new DownloadCallBack() {

            @Override
            public void onReceiveData(InputStream inputStream) {
                // TODO: 2017/2/20 下载成功
                try {
                    saveFile(inputStream);
                    Log.e("下载", "下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
