package com.guangbei.bosinone.client.domain;

/**
 * 数据传输对象 返回给前端
 * Created by xugang on 16/12/26.
 */
public class User {

	private String account;
	private String headUrl;
	private String headUrlOriginal;
	private String isRealName;
	private String realName;
	private String sex;
	private String userID;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getHeadUrlOriginal() {
		return headUrlOriginal;
	}

	public void setHeadUrlOriginal(String headUrlOriginal) {
		this.headUrlOriginal = headUrlOriginal;
	}

	public String getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
