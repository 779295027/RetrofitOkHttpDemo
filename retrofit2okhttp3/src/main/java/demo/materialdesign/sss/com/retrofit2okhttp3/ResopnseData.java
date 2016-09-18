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
 * 返回数据格式
 */
public class ResopnseData {
    private boolean IsSuccess;
    private int StatesCode;
    private String StatesDesc;
    private String Message;
    private Object Data;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public int getStatesCode() {
        return StatesCode;
    }

    public void setStatesCode(int statesCode) {
        StatesCode = statesCode;
    }

    public String getStatesDesc() {
        return StatesDesc;
    }

    public void setStatesDesc(String statesDesc) {
        StatesDesc = statesDesc;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "User{" +
                "IsSuccess=" + IsSuccess +
                ", StatesCode=" + StatesCode +
                ", StatesDesc='" + StatesDesc + '\'' +
                ", Message='" + Message + '\'' +
                ", Data='" + Data + '\'' +
                '}';
    }
}
