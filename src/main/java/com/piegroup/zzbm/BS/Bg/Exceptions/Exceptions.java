package com.piegroup.zzbm.BS.Bg.Exceptions;

import com.piegroup.zzbm.Enums.ExceptionEnum;

public class Exceptions extends RuntimeException {

   private String code;
   private String message;

    public Exceptions(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
