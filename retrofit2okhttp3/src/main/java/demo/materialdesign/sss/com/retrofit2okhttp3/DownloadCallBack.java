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

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sunshaoshuai on 16/10/13.
 */

public abstract class DownloadCallBack extends NoActionAjaxCallBack<ResponseBody> {

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            onReceiveData(response.body().byteStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 连接服务器失败
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        onConnectServerFailed(call, t);
    }

    /**
     * 执行成功
     *
     * @param inputStream 下载的文件的数据流
     */
    public abstract void onReceiveData(InputStream inputStream);

    /**
     * 下载中
     *
     * @param totalLength 文件总大小
     * @param length      已下载大小
     */
    public void onDownloading(long totalLength, long length) {
    }

    /**
     * 未连接到服务器
     *
     * @param call
     * @param t
     */
    public abstract void onConnectServerFailed(Call call, Throwable t);
}
