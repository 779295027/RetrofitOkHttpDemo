package com.example.sunshaoshuai.retrofitokhttpdemo;

import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by sunshaoshuai on 16/8/18.
 */
public interface GitHubService {


    /**
     * json数据格式 post 方式请求
     *
     * @param userApi 参数集
     * @return 返回已经格式化好的数据，格式化成User类型
     */
    @POST("/API/UserManage/AppLogin")
    Call<User> login(@Body Map userApi);

    /**
     * json数据格式 post 方式请求
     *
     * @param userApi 参数集
     * @return 返回jsonobject的数据对象
     */
    @POST("/API/UserManage/AppLogin")
    Call<JsonObject> post(@Body Map userApi);

    /**
     * Get 方式请求
     * 调用方法与post请求一样
     *
     * @param path 服务器相对路径
     * @param map
     * @return 返回jsonobject的数据对象
     */
    @GET("{path}")
    Call<JsonObject> get(@Path("path") String path, @QueryMap Map<String, String> map);

    /**
     * from表单格式post请求
     * 用于上传资源
     *
     * @param path 相对路径
     * @param file 资源文件
     * @param map  参数列表
     * @return 返回jsonobject的数据对象
     */
    @Multipart
    @POST("{path}")
    Call<JsonObject> fromPost(@Path("path") String path, @Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

}
