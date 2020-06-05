package cn.sh.softline.common.entity;

import lombok.Data;

@Data
public class Result {

    private boolean success;

    private Integer code;

    private String message;

    private Object data;

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code, Object data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result SUCCESS(Object data){
        return new Result(ResultCode.SUCCESS,data);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }

    public static Result ERROR(){
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result UNAUTHENTICATED(){
        return  new Result(ResultCode.UNAUTHENTICATED);
    }

    public static Result UNAUTHORISE(){
        return  new Result(ResultCode.UNAUTHORISE);
    }
}
