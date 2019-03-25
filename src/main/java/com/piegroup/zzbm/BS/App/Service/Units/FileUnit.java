package com.piegroup.zzbm.BS.App.Service.Units;

import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.Configs.Constants;
import com.piegroup.zzbm.Dao.UserDao;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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
    public DataPageSubc uploadByIcon(String user_id, MultipartFile file, HttpServletRequest request, String url) throws Exception {

//        url = "D:\\zz\\head";

        DataPageSubc dataPageSubc = new DataPageSubc();

        Map map = new HashMap();

        if (null != file) {
            //项目路径tomcat/ webapp/zzbm
            String realPath = request.getServletContext().getRealPath("/");

            //.getParent()  tomcat /webApp 路径下
            String uploadImagePath = (new File(realPath)).getParent() +url;

//            uploadImagePath = url;

            System.out.println("保存路径：" + uploadImagePath);

            File uploadImageDir = new File(uploadImagePath);
            if (!uploadImageDir.exists()) {
                boolean success = uploadImageDir.mkdir();
                if (success) {
                    System.out.println("文件夹创建成功");
                } else {
                    System.out.println("文件夹创建失败");
                }
            }
            String num = RandomNumberUtil.createRandom(false, 8);

            String fileUrlName = uploadImagePath + "/" + user_id + "_Icon.png";
            // 要保存的图片
            String fileName = url +"/"+ user_id + "_Icon.png";
            map.put("url", Constants.httpUrl+fileName);
            File saveToServerImage = new File(fileUrlName);
            try {
                // 将要上传的图片信息写入要保存的图片中
                file.transferTo(saveToServerImage);
//                 将图片信息存储到数据库
//               boolean b = userDao.updateIcon(user_id,fileName);
//                if (b) {
                log.info("结束上传");
                dataPageSubc.setData(map);
                return dataPageSubc;
//                }
            } catch (IOException e) {
                log.info("保存失败");
               throw e;
            }
        }
        log.info("文件为空");
       return dataPageSubc;

    }

    public DataPageSubc batchUpload(String user_id, HttpServletRequest request, String url) throws IOException {
        DataPageSubc dataPageSubc = new DataPageSubc();

        //定义处理流对象,处理文件上传
        BufferedOutputStream stream = null;
        //定义map存储返回结果集
        List returnfileMap = new ArrayList();

        //获取前端上传的文件列表
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;


        //判断上传文件类型并设置前置路径
        File uploadPath = null;
        String realPath = request.getServletContext().getRealPath("/");
        String uploadImagePath = (new File(realPath)).getParent() + url;
        uploadPath = new File(uploadImagePath);
        //判断服务器上传文件夹是否存在
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        //遍历客户端上传文件列表
        for (int i = 0; i < files.size(); ++i) {
            //获取到每个文件
            file = files.get(i);
            try {
                //获取上传文件后缀
                String houzhui = file.getOriginalFilename().split("\\.")[1];
                String fileName = user_id + new Date().getTime() + "." + houzhui;
                //拼接上传文件保存在服务器的路径(当前用户id+设备id+时间戳.后缀名)
                File fil = new File(uploadPath + "/" + fileName);
                //将上传文件保存到服务器上传文件夹目录下
                byte[] bytes = file.getBytes();
                stream = new BufferedOutputStream(new FileOutputStream(fil));
                stream.write(bytes);
                stream.close();
                //每成功上传一个文件,将上传文件名作为key,服务器保存路径作为value存入returnfileMap中

                returnfileMap.add(Constants.httpUrl+url+"/"+fileName);


            } catch (Exception e) {
                stream = null;
                //保存上传失败的文件信息,将上传文件名作为key,value值为"fail",存入returnfileMap中
//                returnfileMap.put(file.getOriginalFilename(), "fail");
                throw e;
            } finally {
                //关闭处理流
                if (stream != null) {
                    stream.close();
                }
            }
        }
        //返回returnfileMap集合到客户端
        dataPageSubc.setData(returnfileMap);
        return dataPageSubc;
    }

}
