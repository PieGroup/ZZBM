package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.Impl.FileServiceImpl;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName FileController
 * @Description TODO 文件操作接口
 * @Author DDLD
 * @Date 2019/3/20 0:37
 * @ModifyDate 2019/3/20 0:37
 * @Version 1.0
 */
@Controller
@RequestMapping("/file")
@CrossOrigin
@Slf4j
public class FileController {


    @Autowired
    private FileServiceImpl fileService;

    //单个文件上传
    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    @ResponseBody
    @Authorization
    @ApiOperation("单文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
    public DataVO upload(@CurrentUser UserEntity userEntity, MultipartFile file, HttpServletRequest request , int type){
        Assert.notNull(type,"类型不能为空");

        return ResultUtil.success( new DataPageSubc<>(),fileService.upload(userEntity.getUser_Id(),file,request,type));
    }


}