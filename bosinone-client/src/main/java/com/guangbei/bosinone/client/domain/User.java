package com.guangbei.bosinone.client.domain;

/**
 * 数据传输对象 返回给前端
 * Created by xugang on 16/12/26.
 */
public class User extends BaseDomain {
    private static final long serialVersionUID = 4840657329479046986L;

    private Long id;
    private String loginId; //唯一登录名(手机号/email/用户名)
    private String mobile; //手机号
    private String email; //邮箱
    private String username; //用户名
    private String password; //密码
    private String nickname; //昵称
    private Long memberInfoId; //会员信息id
    private Long personalInfoId; //个人详细信息id

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getMemberInfoId() {
        return memberInfoId;
    }

    public void setMemberInfoId(Long memberInfoId) {
        this.memberInfoId = memberInfoId;
    }

    public Long getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(Long personalInfoId) {
        this.personalInfoId = personalInfoId;
    }
}
