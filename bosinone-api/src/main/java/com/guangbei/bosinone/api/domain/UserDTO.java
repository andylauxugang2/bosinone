package com.guangbei.bosinone.api.domain;

/**
 * 数据传输对象 返回给前端
 * Created by xugang on 16/12/26.
 */
public class UserDTO {

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
