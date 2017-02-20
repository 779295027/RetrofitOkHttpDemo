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

package demo.materialdesign.sss.com.retrofit2okhttp3;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by sunshaoshuai on 16/9/5.
 */
public interface RetrofitService {
    /**
     * json数据格式 post 方式请求
     *
     * @param path 服务器相对路径
     * @param map  参数列表
     * @return 返回已经格式化好的数据，格式化成User类型
     */
    @POST("{path}")
    Call<ResopnseData> jsonPost(@Path("path") String path, @Body Map map);

    /**
     * Get 方式请求
     * 调用方法与post请求一样
     *
     * @param path 服务器相对路径
     * @param map  参数列表
     * @return 返回jsonobject的数据对象
     */
    @GET("{path}")
    Call<ResopnseData> get(@Path("path") String path, @QueryMap Map<String, String> map);

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
    Call<ResopnseData> fromPost(@Path("path") String path, @Part MultipartBody.Part file, @PartMap Map<String, RequestBody> map);

    /**
     * json数据格式 Get 方式请求 用于单个文件下载
     *
     * @param path 服务器相对路径
     * @return 返回数据流
     */
    @GET("{path}")
    Call<ResponseBody> downloadForGet(@Path("path") String path);

    /**
     * json数据格式 Get 方式请求 用于单个文件下载(带参数)
     *
     * @param path 服务器相对路径
     * @return 返回数据流
     */
    @GET("{path}")
    Call<ResponseBody> downloadForGet(@Path("path") String path, @QueryMap Map<String, String> map);

    /**
     * json数据格式 Post 方式请求
     *
     * @param path 服务器相对路径
     * @param map  参数列表
     * @return 返回已经格式化好的数据，格式化成User类型
     */
    @POST("{path}")
    Call<ResponseBody> downloadForPost(@Path("path") String path, @Body Map map);

}
