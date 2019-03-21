package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueLikeServiceImpl;
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
@RequestMapping("/IssueLike")
@CrossOrigin
@Slf4j
public class IssueLikeController {

    @Autowired
    IssueLikeServiceImpl issueLikeService;

    @RequestMapping("/likeList")
    @ResponseBody
    public DataVO itemLikeUser(@RequestParam(value = "itemid",required = false,defaultValue = "0000")String itemid,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "15")int pageSize,
                               @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum){
        DataPageSubc dataPageSubc = issueLikeService.allLikeUser(itemid, pageNum, pageSize);
        return ResultUtil.success(dataPageSubc);
    }
}
