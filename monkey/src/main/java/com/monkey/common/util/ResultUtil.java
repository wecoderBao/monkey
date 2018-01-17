package com.monkey.common.util;

import com.monkey.common.dto.Result;
import com.monkey.common.enums.ResultEnum;

public class ResultUtil  {
	public static Result success(Object object) {
        return new Result(ResultEnum.SUCCESS, object);
    }

    public static Result success() {
        return new Result(ResultEnum.SUCCESS);
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum);
    }

    public static Result error(String message) {
        return new Result(message);
    }

    public static Result error() {
        return new Result(ResultEnum.FAILURE);
    }
}
