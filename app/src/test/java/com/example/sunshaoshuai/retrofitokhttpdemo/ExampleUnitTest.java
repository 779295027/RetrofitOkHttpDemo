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

import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void runPost() throws IOException {
        PostExample example = new PostExample();
        Map<String, Object> map = new HashMap<>();
        map.put("MobilePhone", "18618490056");
        map.put("Password", "654321");
        String json = new Gson().toJson(map);
        String response = example.post("你的接口路径", json);
        System.out.println(response);
    }

    @Test
    public void runGet() throws IOException {
        GetExample example = new GetExample();
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }

    @Test
    public void jsonMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("商家ID", "AAA");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("商品ID", 1);

        map.put("商品列表", map1);
        Gson gson = new Gson();
        System.out.print(gson.toJson(map));

    }

}