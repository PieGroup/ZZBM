package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.Dao.commentDao;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class IssueCommentServiceImpl {
    @Resource
    commentDao commentDao;

}
