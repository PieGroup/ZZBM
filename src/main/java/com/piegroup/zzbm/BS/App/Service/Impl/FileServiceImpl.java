package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.BS.App.Service.FileServiceIF;
import com.piegroup.zzbm.BS.App.Service.Units.FileUnit;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
*@ClassName     FileServiceImpl
*@Description   TODO
*@Author        DDLD
*@Date          2019/3/20 0:55
*@ModifyDate    2019/3/20 0:55
*@Version       1.0
*/

@Service
@Slf4j
public class FileServiceImpl implements FileServiceIF {

    @Autowired
    private FileUnit fileUnit;

    @Override
    public ExceptionEnum upload(String user_id, MultipartFile file, HttpServletRequest request, int type) {


        //用户头像上传
        if (type == 1){
            log.info("开始上传");
           return  fileUnit.uploadByIcon(user_id,file,request);
        }else {

            return ExceptionEnum.Param_Exception;
        }
    }


}
