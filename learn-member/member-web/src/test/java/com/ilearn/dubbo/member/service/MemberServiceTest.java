package com.ilearn.dubbo.member.service;

import com.ilearn.dubbo.member.LocalTest;
import com.ilearn.dubbo.member.service.MemberService;

/**
 * Created by George on 2018/2/2 0002.
 */
public class MemberServiceTest  extends LocalTest{

    private MemberService memberService;

    public static void main(String[] args) throws Exception {
        MemberServiceTest memberServiceTest = new MemberServiceTest();
        memberServiceTest.execute(args);

        memberServiceTest.updateMemberRank();
    }

    public void updateMemberRank() throws Exception {
        int memberId = 1000;
        float rank = 10.00f;
        System.out.print(memberService.updateMemberRank(memberId, rank));
    }

    @Override
    public void execute(String[] args) {
        this.initBean();
        memberService = this.getService("memberService");
    }
}
