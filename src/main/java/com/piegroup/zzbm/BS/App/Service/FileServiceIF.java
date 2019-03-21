package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FileServiceIF
 * @Description TODO 文件上传
 * @Author DDLD
 * @Date 2019/3/20 0:53
 * @ModifyDate 2019/3/20 0:53
 * @Version 1.0
 */
public interface FileServiceIF {

        public DataPageSubc upload(String user_id, MultipartFile file, HttpServletRequest request, int type);

        public DataPageSubc batchUpload(String user_id, HttpServletRequest request, int type);
}
