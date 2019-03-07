package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueProgramServiceImpl;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/IssueProgram")
@CrossOrigin
@Slf4j
public class IssueProgramController {

    @Autowired
    IssueProgramServiceImpl issueProgramService;

    /**
     * 首页请求问题列表
     * @param pageSize
     * @param pageNum
     * @param all  1表示显示全部问题，其他值代表 系统推荐（非必需）
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataVO programList(@RequestParam(value = "pageSize",required = true,defaultValue = "10")int pageSize,
                                    @RequestParam(value = "pageNum",required = true,defaultValue = "1")int pageNum,
                              @RequestParam(value="all",required = false,defaultValue = "1")int all) throws  Exception{
        DataPageSubc list = issueProgramService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/")
    @ResponseBody
    public String test(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                              @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum) throws  Exception{

        return "pageSize:"+pageSize+" & pageNum:"+pageNum;
    }
}
