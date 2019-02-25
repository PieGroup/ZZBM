package com.piegroup.zzbm.Utils;

import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

//统一给前端显示数据的工具类


public class ResultUtil {

    //成功返回
    public static DataVO success(Object object) {

        return R_save(object, ExceptionEnum.SUCCESS);
    }

//    失败返回
    public static DataVO error(Object object, ExceptionEnum exceptionEnum) {
        return R_save(object,exceptionEnum);
    }

    //成功返回的对象是空的
    public static DataVO success() {
        return success(null);
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
