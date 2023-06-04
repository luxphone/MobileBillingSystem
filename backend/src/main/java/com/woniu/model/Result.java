package com.woniu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result response(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }
}
