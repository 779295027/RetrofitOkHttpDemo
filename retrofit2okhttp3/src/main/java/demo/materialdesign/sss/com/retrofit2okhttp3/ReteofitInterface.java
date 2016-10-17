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

/**
 * Created by sunshaoshuai on 16/9/5.
 */
public interface ReteofitInterface {
    /**
     * 添加TOKEN值
     *
     * @param token
     */
    void addToken(String token);

    /**
     * json数据格式的post请求
     *
     * @param path     相对路径
     * @param map      参数列表
     * @param callBack 回调
     */
    void jsonPost(String path, Map<String, Object> map, NoActionAjaxCallBack callBack);

    /**
     * json数据格式的post请求
     *
     * @param path     相对路径
     * @param json     参数列表的json格式字符串
     * @param callBack 回调
     */
    void jsonPost(String path, String json, NoActionAjaxCallBack callBack);

    /**
     * Get请求
     *
     * @param path     相对路径
     * @param map      参数列表
     * @param callBack 回调
     */
    void get(String path, Map<String, String> map, NoActionAjaxCallBack callBack);


    /**
     * from表单格式post请求
     * 用于上传资源
     *
     * @param path     相对路径
     * @param file     资源文件
     * @param map      参数列表
     * @param callBack 回调
     */
    void fromPost(String path, MultipartBody.Part file, Map<String, RequestBody> map, NoActionAjaxCallBack callBack);


    /**
     * json数据格式的post请求
     * 用于下载单个资源
     *
     * @param path     相对路径
     * @param map      参数列表
     * @param callBack 回调
     */
    void download(String path, Map<String, Object> map, DownloadCallBack callBack);
}
