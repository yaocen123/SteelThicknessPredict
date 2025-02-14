package jishe.steelthicknesspredict.utils;

import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String message;
    private T data;

    // 成功返回
    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setMessage("Success");
        response.setData(data);
        return response;
    }

    // 错误返回
    public static <T> Response<T> error(String message) {
        Response<T> response = new Response<>();
        response.setCode(400);
        response.setMessage(message);
        return response;
    }
}
