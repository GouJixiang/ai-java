package com.occn.ai.common.bean;

import com.occn.ai.common.enums.ReponseCodeEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * 通用Rest接口结果类
 */
public class RestResult<T> {

    /* Getter */
    @Getter
    private final Integer code;
    @Getter
    private final String msg;
    private final T data;
    @Getter
    private final LocalDateTime timestamp = LocalDateTime.now();

    /* 常用静态字段 */
    public static final RestResult<?> OK = new RestResult<>(HttpStatus.OK);
    public static final RestResult<?> FAIL = new RestResult<>(ReponseCodeEnum.BUSINESS_EXCEPTION);

    /* 常用静态方法 */
    public static <R> RestResult<R> ok(R data) {
        return new RestResult<>(HttpStatus.OK, data);
    }

    public static RestResult<?> fail(String msg) {
        return new RestResult<>(10010, msg);
    }

    /* 构造方法 */
    public RestResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public RestResult(ReponseCodeEnum reponseCodeEnum) {
        this(ReponseCodeEnum.BUSINESS_EXCEPTION.getCode(), ReponseCodeEnum.BUSINESS_EXCEPTION.getMessage());
    }

    public RestResult(HttpStatus httpStatus) {
        this(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public RestResult(HttpStatus httpStatus, T data) {
        this(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}

