package com.monkey.common.dto;

import java.io.Serializable;

import com.monkey.common.enums.ResultEnum;

public class Result implements Serializable {
	private static final long serialVersionUID = 2L;
	/** 错误码 */
	private Integer code = -1;

	/** 提示信息 */
	private String message;

	/** 具体的内容 */
	private Object info;

	public Result(Integer code, String message, Object info) {
		this.code = code;
		this.message = message;
		this.info = info;
	}

	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result(ResultEnum resultEnum, Object object) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMsg();
		this.info = object;
	}

	public Result(ResultEnum resultEnum) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMsg();
	}

	public Result(String message) {
		this.message = message;
	}

	public Result() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Result{" + "code=" + code + ", message='" + message + '\'' + ", info=" + info + '}';
	}
}
