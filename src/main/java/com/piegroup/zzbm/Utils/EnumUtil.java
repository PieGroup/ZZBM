package com.piegroup.zzbm.Utils;

import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DDLD
 * 2018-12-19
 */
@Slf4j
public class EnumUtil {

    public static <T extends CodeIF> T getByCode(String code, Class<T> enumClass) {
        try {
            for (T each: enumClass.getEnumConstants()) {
                if (code.equals(each.getCode())) {
                    return each;
                }
            }

        }catch (Exception e){
            log.error("枚举转换出现问题");
            throw new Exceptions(ExceptionEnum.Transform_Enum_Exception);
        }
        return null;
    }
}
