package com.ilearn.dubbo.member.service.ext;

/**
 * Created by George on 2018/2/3 0003.
 */
public interface MemberExtService {
    public boolean updateMemberRank(int memberId, float rank) throws Exception;
}
