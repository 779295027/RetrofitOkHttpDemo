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

/**
 * Created by sunshaoshuai on 16/8/18.
 */
public class User {
    private boolean IsSuccess;
    private int StatesCode;
    private String StatesDesc;
    private String Message;
    private Object Data;

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public boolean isIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public int getStatesCode() {
        return StatesCode;
    }

    public void setStatesCode(int StatesCode) {
        this.StatesCode = StatesCode;
    }

    public String getStatesDesc() {
        return StatesDesc;
    }

    public void setStatesDesc(String StatesDesc) {
        this.StatesDesc = StatesDesc;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }


    @Override
    public String toString() {
        return "User{" +
                "IsSuccess=" + IsSuccess +
                ", StatesCode=" + StatesCode +
                ", StatesDesc='" + StatesDesc + '\'' +
                ", Message='" + Message + '\'' +
                ", Data=" + Data +
                '}';
    }
}
