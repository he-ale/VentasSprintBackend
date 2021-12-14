package com.mito.facturacionAppbackend.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private LocalDateTime date;
    private String msg;
    private String details;

    //Esta clase es un molde de que contendra error

    public ExceptionResponse(){}

    public ExceptionResponse(LocalDateTime date, String msg, String details) {
        this.date = date;
        this.msg = msg;
        this.details = details;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
