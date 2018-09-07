package com.shine.seckill.result;

public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data=data;
    }

    public Result(CodeMsg msg) {
        if (msg==null) {
            return;
        }
        this.code=msg.getCode();
        this.msg = msg.getMsg();
    }

    /**
     * success
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }

    /**
     * error
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg msg){
        return new Result<T>(msg);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
