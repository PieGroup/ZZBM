package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Enums.ExceptionEnum;

/**
 * @ClassName MessageServiceIF
 * @Description TODO 消息通知
 * @Author DDLD
 * @Date 2019/3/17 20:44
 * @ModifyDate 2019/3/17 20:44
 * @Version 1.0
 */
public interface MessageServiceIF {

    ExceptionEnum send(String phone);
}
