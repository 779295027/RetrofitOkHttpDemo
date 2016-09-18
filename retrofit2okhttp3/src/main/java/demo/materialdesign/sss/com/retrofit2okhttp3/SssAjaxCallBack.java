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


import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunshaoshuai on 16/9/5.
 */
public abstract class SssAjaxCallBack extends NoActionAjaxCallBack implements Callback {
    /**
     * 请求成功
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call call, Response response) {
        ResopnseData rd = (ResopnseData) response.body();
        if (rd.getData() == null) {
            rd.setData("");
        }
        try {
            JSONObject json = new JSONObject(JsonUtils.toJson(rd));
            android.util.Log.e("onSuccess", ":" + json.toString());
            int code = json.getInt("StatesCode");
            if (json.getBoolean("IsSuccess")) {
                onReceiveData(json.getString("Data"), json.getString("Message"));
            } else {
                onReceiveError(code, json.getString("Message"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            onReceiveError(0, "解析数据失败");
        }
    }

    /**
     * 连接服务器失败
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call call, Throwable t) {
        onConnectServerFailed(call, t);
    }


    /**
     * 执行成功
     *
     * @param data
     * @param msg
     */
    public abstract void onReceiveData(String data, String msg);

    /**
     * 执行失败
     *
     * @param errorCode
     * @param msg
     */
    public abstract void onReceiveError(int errorCode, String msg);

    /**
     * 未连接到服务器
     *
     * @param call
     * @param t
     */
    public abstract void onConnectServerFailed(Call call, Throwable t);

}
