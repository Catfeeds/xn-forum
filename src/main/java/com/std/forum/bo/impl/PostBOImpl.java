/**
 * @Title PostBOImp.java 
 * @Package com.std.forum.bo.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年8月29日 下午4:24:47 
 * @version V1.0   
 */
package com.std.forum.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.forum.bo.IPostBO;
import com.std.forum.bo.base.PaginableBOImpl;
import com.std.forum.core.OrderNoGenerater;
import com.std.forum.dao.IPostDAO;
import com.std.forum.domain.Post;
import com.std.forum.enums.EBoolean;
import com.std.forum.enums.EPostStatus;
import com.std.forum.enums.EPrefixCode;
import com.std.forum.exception.BizException;

/** 
 * 帖子BO
 * @author: xieyj 
 * @since: 2016年8月29日 下午4:24:47 
 * @history:
 */
@Component
public class PostBOImpl extends PaginableBOImpl<Post> implements IPostBO {

    @Autowired
    private IPostDAO postDAO;

    /**
     * @see com.std.forum.bo.IPostBO#savePost(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String savePost(String title, String content, String pic,
            String plateCode, String publisher, String status) {
        Post data = new Post();
        String code = OrderNoGenerater.generate(EPrefixCode.POST.getCode());
        data.setCode(code);
        data.setTitle(title);
        data.setContent(content);
        data.setPic(pic);
        data.setPlateCode(plateCode);
        data.setPublisher(publisher);
        data.setStatus(status);
        data.setPublishDatetime(new Date());
        postDAO.insert(data);
        return code;
    }

    @Override
    public void refreshPost(String code, String title, String content,
            String pic, String plateCode, String publisher, String status) {
        Post data = new Post();
        data.setCode(code);
        data.setTitle(title);
        data.setContent(content);
        data.setPic(pic);
        data.setPlateCode(plateCode);
        data.setStatus(status);
        data.setPublisher(publisher);
        data.setPublishDatetime(new Date());
        postDAO.update(data);
    }

    /** 
     * @see com.std.forum.bo.IPostBO#removePost(java.lang.String)
     */
    @Override
    public int removePost(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            count = postDAO.delete(data);
        }
        return count;
    }

    /**
     * @see com.std.forum.bo.IPostBO#refreshPostApprove(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshPostApprove(String code, String approver,
            String approveResult, String approveNote) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            if (EBoolean.YES.getCode().equals(approveResult)) {
                data.setStatus(EPostStatus.APPROVE_YES.getCode());
            } else {
                data.setStatus(EPostStatus.APPROVE_NO.getCode());
            }
            data.setApprover(approver);
            data.setApproveDatetime(new Date());
            data.setApproveNote(approveNote);
            count = postDAO.updateApprove(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#refreshPostReturn(java.lang.String)
     */
    @Override
    public int refreshPostReturn(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setStatus(EPostStatus.APPROVE_YES.getCode());
            count = postDAO.updateStatus(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#getPost(java.lang.String)
     */
    @Override
    public Post getPost(String code) {
        Post result = null;
        if (StringUtils.isNotBlank(code)) {
            Post condition = new Post();
            condition.setCode(code);
            result = postDAO.select(condition);
            if (result == null) {
                throw new BizException("xn000000", "帖子编号不存在");
            }
        }
        return result;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#queryPostList(com.std.forum.domain.Post)
     */
    @Override
    public List<Post> queryPostList(Post condition) {
        return postDAO.selectList(condition);
    }

    @Override
    public long getPostNum(Post condition) {
        return postDAO.selectPostNum(condition);
    }

    @Override
    public int refreshPostLocation(String code, String location,
            Date endDatetime) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setLocation(location);
            if (null == endDatetime) {
                data.setValidDatetimeStart(endDatetime);
            } else {
                data.setValidDatetimeStart(new Date());
            }
            data.setValidDatetimeEnd(endDatetime);
            count = postDAO.updateLocation(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#refreshPostHeadlines(java.lang.String)
     */
    @Override
    public int refreshPostLock(String code, String isLock) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setIsLock(isLock);
            count = postDAO.updateLock(data);
        }
        return count;
    }

    @Override
    public int refreshPostPlate(String code, String plateCode) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setPlateCode(plateCode);
            count = postDAO.updatePlate(data);
        }
        return count;
    }

    /** 
     * @see com.std.forum.bo.IPostBO#refreshPostReport(java.lang.String, java.lang.String)
     */
    @Override
    public int refreshPostReport(String code, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setStatus(EPostStatus.toReportAPPROVE.getCode());
            data.setRemark(remark);
            count = postDAO.updateStatus(data);
        }
        return count;
    }

}
