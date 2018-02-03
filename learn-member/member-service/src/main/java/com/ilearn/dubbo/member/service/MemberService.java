package com.ilearn.dubbo.member.service;

/**
 * Created by George on 2018/2/2 0002.
 */
public interface MemberService {
    public boolean updateMemberRank(int memberId, float rank) throws Exception;
}
