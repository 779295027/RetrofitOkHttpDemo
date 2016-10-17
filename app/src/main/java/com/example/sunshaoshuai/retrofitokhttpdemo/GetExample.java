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


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sunshaoshuai on 16/8/18.
 */
public class GetExample {
    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        GetExample example = new GetExample();
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }

}


// ┏┓       ┏┓
//┏┛┻━━━━━━━┛┻┓
//┃           ┃ 　
//┃     ━     ┃
//┃  ┳┛   ┗┳  ┃
//┃           ┃
//┃     ┻     ┃
//┃           ┃
//┗━┓     ┏━━━┛
//  ┃     ┃   神兽保佑　　　　　　　　
//  ┃     ┃   代码无BUG！
//  ┃     ┗━━━━━━┓
//  ┃　　　　　　　┣┓
//  ┃　　　　　　　┏┛
//  ┗┓┓┏━━━━┳┓┏━━┛
//   ┃┫┫    ┃┫┫
//   ┗┻┛    ┗┻┛
