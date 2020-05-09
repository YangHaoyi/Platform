package com.yanghaoyi.project.util;

import com.github.pagehelper.PageInfo;
import com.yanghaoyi.project.pojo.parameter.ProjectPageParams;
import com.yanghaoyi.project.pojo.result.PageResult;

/**
 * @author : YangHaoYi on 2020/5/9.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/9.
 * Version : V 1.0
 */
public class PageUtil {
    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @param pageRequest
     * @return
     */
    public static PageResult getPageResult(ProjectPageParams pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
