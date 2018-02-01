package com.ilearn.dubbo.member.data;

import java.io.Serializable;


/**
 * 
 * @author George 2018-02-01 下午 08:23:13
 * 
 */
public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**  */
	private int id;
	/** 用户名 */
	private String userName;

	public Member() {
		super();
	}

	public Member(int id, String userName) {
		this.id = id;
		this.userName = userName;
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

	@Override
	public String toString() {
		return "Member [id = " + id + ", userName = " + userName + "]";
	}

}
