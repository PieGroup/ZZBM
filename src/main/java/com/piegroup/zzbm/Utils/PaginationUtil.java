package com.piegroup.zzbm.Utils;

import com.piegroup.zzbm.VO.SubC.PaginationSubC;

/**
 * 实现分页技术
 * 主要使用 数据库分页
 */

/**
 * pageNum 第几页信息
 * pageSize 选择数据的多少
 * count 要显示哪种信息的总条数
 */

public class PaginationUtil {
    //返回从哪里开始取，取几条
    public static PaginationSubC pagination(int pageNum, int pageSize, int count) {

        int currentPage = 0;//当前第几页数据
        int totalPage = 0;//一共多少页记录
        boolean nextPage = true;

        //输入参数判断
        if (pageNum < 0 || pageNum == 0) {
//            new PageEntity().CreatePage();
            return  new PaginationSubC(0,0,0,false);

        }
        if (count < 0 || count == 0) {
            return new PaginationSubC(0,0,0,false);
        }
        if (pageSize < 0 || pageSize == 0) {
            return new PaginationSubC(0,0,0,false);
        }
        //获取总页数

        totalPage = (count % pageSize == 0) ?  (count / pageSize): (count / pageSize + 1);

        System.out.println("总页数："+totalPage);

        //获取第几页数据
        currentPage = totalPage < pageNum ? totalPage : pageNum;
        //起始索引
        int fromIndex = pageSize * (currentPage - 1);
        //是否还有下一页

       // nextPage = totalPage > pageNum;

        if ( totalPage == pageNum || pageNum > totalPage){
            nextPage = false;
        }

        //从第几页的开始 fromIndex，第几条
        return new PaginationSubC(fromIndex, pageSize, currentPage,nextPage);
    }


}
