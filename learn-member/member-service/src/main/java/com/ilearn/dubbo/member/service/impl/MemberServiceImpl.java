package com.ilearn.dubbo.member.service.impl;

import com.ilearn.dubbo.appframework.dao.BaseDao;
import com.ilearn.dubbo.appframework.support.FrameUtils;
import com.ilearn.dubbo.member.service.ext.MemberExtService;
import com.ilearn.dubbo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by George on 2018/2/2 0002.
 */
public class MemberServiceImpl implements MemberService, MemberExtService {

    @Autowired
    private BaseDao baseDao;

    private Integer result = 0;

    @Override
    public boolean updateMemberRank(int memberId, float rank) throws Exception {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("memberId", memberId);
            params.put("rank", rank);
            result = baseDao.update(params, "MemberMapper.updateMemberRank");
        } catch (Exception e) {
            throw e;
        }
        return FrameUtils.isIntegerAndGt0(result);
    }
}
