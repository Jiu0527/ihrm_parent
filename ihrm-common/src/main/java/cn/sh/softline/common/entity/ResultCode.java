package cn.sh.softline.common.entity;

/**
 * 返回码定义类
 * @author rz-045
 */
public enum ResultCode {

    SUCCESS(true, 10000, "操作成功"),
    //系统错误返回码
    FAIL(false, 10001, "操作失败"),
    UNAUTHENTICATED(false, 10002, "您还未登录"),
    UNAUTHORISE(false, 10003, "权限不足"),
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),
    MOBILEANDPASSWORDERROR(false,10004,"用户名或密码错误！");
    //---用户操作返回码----
    //---企业操作返回码---
    //---权限操作返回码----
    //---其他操作返回码----

    //操作是否成功
    boolean success;
    //状态码
    Integer code;
    //提示信息
    String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
