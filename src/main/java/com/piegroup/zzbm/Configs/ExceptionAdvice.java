package com.piegroup.zzbm.Configs;


import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DataVO ExceptionAuthority(Exception e) {


        if (e instanceof Exceptions) {
            Exceptions exceptions = (Exceptions) e;
            logger.info("报错原因={}", exceptions.getMessage());
            return ResultUtil.error(null, ExceptionEnum.valueOf(exceptions.getCode()));
        } else {
            logger.info("【系统异常】={}", e);
            return ResultUtil.error(null, ExceptionEnum.Unknown_Exception);
        }
    }
}
