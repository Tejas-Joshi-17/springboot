package com.sarvatra.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    @JsonProperty("success")
    private boolean success;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamp;

    private T data;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.success = true;
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.success = false;
        this.error = error;
    }
}
