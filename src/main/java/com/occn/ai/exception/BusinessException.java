package com.occn.ai.exception;

import com.occn.ai.common.enums.ReponseCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException{

    private Integer code;
    private String message;

    public BusinessException(ReponseCodeEnum reponseCodeEnum, String message) {
        super(message);
        this.message = message;
        this.code = reponseCodeEnum.getCode();
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
        this.code = ReponseCodeEnum.BUSINESS_EXCEPTION.getCode();
    }

    public BusinessException(ReponseCodeEnum reponseCodeEnum) {
        this.code = reponseCodeEnum.getCode();
        this.message = reponseCodeEnum.getMessage();
    }

    public BusinessException() {
        this.code = ReponseCodeEnum.BUSINESS_EXCEPTION.getCode();
        this.message = ReponseCodeEnum.BUSINESS_EXCEPTION.getMessage();
    }
}
