package com.ilearn.dubbo.member.data;

import java.io.Serializable;


/**
 * @author George 2018-02-01 08:23:13
 */
public class Member implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**  */
    private int id;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 等级
     */
    private float rank;

    public Member() {
        super();
    }

    public Member(int id, String userName, float rank) {
        this.id = id;
        this.userName = userName;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", rank=" + rank +
                '}';
    }
}
