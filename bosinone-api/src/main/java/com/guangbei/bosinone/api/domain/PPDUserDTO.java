package com.guangbei.bosinone.api.domain;

/**
 * 拍拍贷 接口返回 用户数据对象
 * Created by xugang on 16/12/26.
 */
public class PPDUserDTO {

	private String userID;
	private String username;
	private String mail;
	private String userToken;//token

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
}
