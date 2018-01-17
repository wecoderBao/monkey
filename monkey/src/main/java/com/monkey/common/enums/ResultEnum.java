package com.monkey.common.enums;

public enum ResultEnum {
	SUCCESS(0, "成功"),
	
	UNLOGIN(-201, "您没有登录，请先登录"),
	
	UNKNOW_ERROR(-100, "系统异常,请联系管理员"),

    FAILURE(-101, "失败"),

    DUPLICATEKEY(-102, "该记录已存在"),

    PARAMS_ERROR(-103, "参数错误"),

    NONRECORD(-104, "该记录不存在"),

    NOTEXISTUSER(-106, "账号不存在，请联系管理员"),

    BIGUPLOADFILE(-107, "上传文件太大"),

    LOGINERROR(-108, "登录失败，请重新登录"),

    NICKNAME_ERROR(-110, "昵称非法"),

    NICKNAME_IS_USED(-111, "昵称被占用"),

    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
