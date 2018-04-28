package com.ilearn.dubbo.member.service;

/**
 * Created by Administrator on 2018/4/24 0024.
 */
public class MemberServiceTest extends LocalTest {

    private MemberService memberService;

    public static void main(String[] args) throws Exception {
        MemberServiceTest memberServiceTest = new MemberServiceTest();
        memberServiceTest.execute(args);

        memberServiceTest.createOrder();
    }

    public void createOrder() throws Exception {
        int shopId = 2000;
        int sellerId = 1000;
        int buyerId = 1001;
        int productId = 1000;
        int quantity = 2;
        System.out.println(memberService.createOrder(shopId, sellerId, buyerId, productId, quantity));
    }

    @Override
    public void execute(String[] args) {
        this.initBean();
        memberService = getService("memberService");
    }
}
