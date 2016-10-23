package com.std.forum.api.impl;

import com.std.forum.ao.IPostAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN6100522Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/** 
 * 版主删除帖子
 * @author: zuixian 
 * @since: 2016年9月19日 下午4:44:53 
 * @history:
 */
public class XN6100522 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN6100522Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        postAO.removePost(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN6100522Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}