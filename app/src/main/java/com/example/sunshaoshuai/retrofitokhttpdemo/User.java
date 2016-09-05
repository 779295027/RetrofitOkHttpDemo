package com.example.sunshaoshuai.retrofitokhttpdemo;

/**
 * Created by sunshaoshuai on 16/8/18.
 */
public class User {
    private boolean IsSuccess;
    private int StatesCode;
    private String StatesDesc;
    private String Message;


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
                '}';
    }
}
