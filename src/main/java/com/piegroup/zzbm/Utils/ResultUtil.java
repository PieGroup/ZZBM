package com.piegroup.zzbm.Utils;

import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

//统一给前端显示数据的工具类


public class ResultUtil {

    //成功返回
    public static DataVO success(Object object)  {
        try {
            return R_save(object, ExceptionEnum.Success);
        }catch (Exception e){
            //抛出数据存储出现问题
            throw new Exceptions(ExceptionEnum.Black_Data_Exception);
        }

    }

//    失败返回
    public static DataVO error(Object object, ExceptionEnum exceptionEnum) {
        return R_save(object,exceptionEnum);
    }

    //成功返回的对象是空的
    public static DataVO success(Object o,ExceptionEnum exceptionEnum) {
        return R_save(o,exceptionEnum);
    }

    //保存数据
    private static DataVO R_save(Object object, ExceptionEnum exceptionEnum) {
        DataVO DataVO = (DataVO) EnjoyUtil.getObject(exceptionEnum.getCode(), new DataVO<>());
        DataVO.setCode(exceptionEnum.getCode());
        DataVO.setMessage(exceptionEnum.getMessage());
        DataVO.setData((DataPageSubc) object);
        return DataVO;
    }

}
