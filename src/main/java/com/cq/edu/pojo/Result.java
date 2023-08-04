package com.cq.edu.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 统一返回类
 *
 * @author kangkang
 */
@Data
@ToString
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(Integer code, String message, T data) {
        Result<T> result = new Result<>(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}
