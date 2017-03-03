package com.guangbei.bosinone.client.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础数据对象
 */
public abstract class BaseDomain implements Serializable {

    private Long id;

    private Date createTime = new Date(System.currentTimeMillis());

    private Date updateTime;

    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}