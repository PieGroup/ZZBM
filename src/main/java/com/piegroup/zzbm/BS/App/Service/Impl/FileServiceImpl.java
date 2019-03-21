package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.FileServiceIF;
import com.piegroup.zzbm.BS.App.Service.Units.FileUnit;
import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.Configs.Constants;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FileServiceImpl
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/20 0:55
 * @ModifyDate 2019/3/20 0:55
 * @Version 1.0
 */

@Service
@Slf4j
public class FileServiceImpl implements FileServiceIF {

    @Autowired
    private FileUnit fileUnit;

    @Override
    public DataPageSubc upload(String user_id, MultipartFile file, HttpServletRequest request, int type) {

        DataPageSubc dataPageSubc = new DataPageSubc();
        //用户头像上传
        if (type == 1) {

            log.info("开始上传");
            dataPageSubc = fileUnit.uploadByIcon(user_id, file, request);
            return dataPageSubc;

        } else  {

            return null;
        }
    }

    //多文件
    @Override
    public DataPageSubc batchUpload(String user_id, HttpServletRequest request, int type) {

        String url = "";
        if (type == 1) {
            url = Constants.questionUrl;
        } else if (type == 2) {
            url = Constants.demandUrl;
        } else if (type == 3) {
            url = Constants.consultUrl;
        } else if (type == 4) {
            url = Constants.programUrl;
        } else {
            url = Constants.questionUrl;
        }

        try {

            return fileUnit.batchUpload(user_id, request, url);
        } catch (Exception e) {
            throw new Exceptions(ExceptionEnum.Upload_Fail);
        }

    }


}
