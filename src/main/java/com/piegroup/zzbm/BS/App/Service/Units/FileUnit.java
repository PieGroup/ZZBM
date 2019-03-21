package com.piegroup.zzbm.BS.App.Service.Units;

import com.piegroup.zzbm.Configs.Constants;
import com.piegroup.zzbm.Dao.UserDao;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileUnit
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/20 1:01
 * @ModifyDate 2019/3/20 1:01
 * @Version 1.0
 */
@Component
@Slf4j
public class FileUnit {

    @Resource
    UserDao userDao;

    //用户头像
    @Transactional
    public ExceptionEnum uploadByIcon(String user_id , MultipartFile file, HttpServletRequest request){

        if (null != file) {
            String realPath = request.getServletContext().getRealPath("/");

            String uploadImagePath=(new File(realPath)).getParent()+Constants.IconUrl;

            System.out.println("保存路径："+uploadImagePath);

            File uploadImageDir = new File(uploadImagePath);
            if (!uploadImageDir.exists()) {
                boolean success = uploadImageDir.mkdir();
                if (success) {
                    System.out.println("文件夹创建成功");
                } else {
                    System.out.println("文件夹创建失败");
                }
            }
            String fileUrlName = uploadImagePath+ "/"+user_id+"_Icon.png";
            // 要保存的图片
            File saveToServerImage = new File(fileUrlName);
            try {
                // 将要上传的图片信息写入要保存的图片中
                file.transferTo(saveToServerImage);
                // 将图片信息存储到数据库
               boolean b = userDao.updateIcon(user_id,fileUrlName);

                if (b) {
                    log.info("结束上传");
                    return ExceptionEnum.Upload_Success;
                }
            } catch (IOException e) {
                log.info("保存失败");
               return ExceptionEnum.Upload_Fail;
            }
        }
        log.info("文件为空");
        return ExceptionEnum.Upload_Fail;

    }

}
