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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sunshaoshuai on 16/9/5.
 */
public class RetrofitInstanceImpl extends RetrofitInstance {
    private Retrofit retrofit;
    private OkHttpClient client;
    private RetrofitService service;
    private Call<ResopnseData> call;
    private Call<ResponseBody> bodyCall;

    public RetrofitInstanceImpl() {
        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DOMAIN_NAME)
                .client(client)
                .build();
        service = retrofit.create(RetrofitService.class);
    }


    @Override
    public void jsonPost(String path, Map<String, Object> map, NoActionAjaxCallBack callBack) {
        call = service.jsonPost(path, map != null ? map : new HashMap<String, Object>());
        enqueue(callBack);
    }

    @Override
    public void addToken(String Token) {
        this.Token = Token;
    }

    @Override
    public void jsonPost(String path, String json, NoActionAjaxCallBack callBack) {
        Map<String, Object> map = JsonUtils.parse(json, Map.class);
        call = service.jsonPost(path, map);
        enqueue(callBack);
    }

    @Override
    public void get(String path, Map<String, String> map, NoActionAjaxCallBack callBack) {
        call = service.get(path, map != null ? map : new HashMap<String, String>());
        enqueue(callBack);
    }

    @Override
    public void fromPost(String path, MultipartBody.Part file, Map<String, RequestBody> map, NoActionAjaxCallBack callBack) {
        call = service.fromPost(path, file, map != null ? map : new HashMap<String, RequestBody>());
        enqueue(callBack);
    }

    @Override
    public void download(String path, Map<String, Object> map, DownloadCallBack callBack) {
        try {
            bodyCall = service.download(path, map);
            bodyCall.enqueue(callBack != null ? callBack : new NoActionAjaxCallBack());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步请求
     *
     * @param callBack
     */
    private void enqueue(NoActionAjaxCallBack callBack) {
        try {
            call.enqueue(callBack != null ? callBack : new NoActionAjaxCallBack());
        } catch (Exception e) {
        }
    }

    class NetworkInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request requestOrigin = chain.request();
            if (Token == null) {
                return chain.proceed(requestOrigin);
            }
            Request authorised = requestOrigin.newBuilder()
                    .header(HttpUrl.TOKEN_NAME, Token)
                    .build();
            Response response = chain.proceed(authorised);
            return response;
        }
    }
}
