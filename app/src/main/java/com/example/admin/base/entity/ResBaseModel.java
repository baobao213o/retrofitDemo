package com.example.admin.base.entity;

import com.google.gson.annotations.SerializedName;

public class ResBaseModel<T> {

    @SerializedName("error_code")
    private int errorCode;
    private String reason;
    private T result;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public void setResult(T result) {
        this.result = result;
    }
    public T getResult() {
        return result;
    }
}

