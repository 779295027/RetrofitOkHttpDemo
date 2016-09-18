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



/**
 * Created by sunshaoshuai on 16/9/5.
 */
public abstract class RetrofitInstance implements ReteofitInterface {

    protected String DOMAIN_NAME = HttpUrl.DOMAIN_NAME;
    protected static RetrofitInstance retrofitInstance;

    protected String Token;

    /**
     * 获得单例
     *
     * @return
     */
    public static RetrofitInstance getInstance() {
        return retrofitInstance != null ? retrofitInstance : (retrofitInstance = new RetrofitInstanceImpl());
    }



}
