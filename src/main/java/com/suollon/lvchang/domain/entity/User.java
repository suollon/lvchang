package com.suollon.lvchang.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
public class User implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static User build() {
        return new User();
    }

    public User userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public User userName(String userName) {
        this.userName = userName;
        return this;
    }

    public User address(String address) {
        this.address = address;
        return this;
    }

    public User phone(String phone) {
        this.phone = phone;
        return this;
    }

    public User createTime(Date userName) {
        this.createTime = createTime;
        return this;
    }

    public User updateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}