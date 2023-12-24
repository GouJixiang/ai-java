package com.occn.ai.exception.handler;

import com.occn.ai.common.bean.RestResult;
import com.occn.ai.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public RestResult<?> businessExceptionHandler(HttpServletRequest request, BusinessException exception) {
        log.error("BusinessException异常：{}", exception.getMessage());
        if (exception.getCode() != null) {
            return new RestResult<>(exception.getCode(), exception.getMessage(), null);
        }
        return RestResult.fail(exception.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public RestResult<?> exceptionHandler(HttpServletRequest request, Exception exception) {
        log.error(exception.getMessage());
        return RestResult.FAIL;
    }

}
