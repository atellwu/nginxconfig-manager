package com.yeahmobi.loadbalance_manager.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.PrePersist;

/**
 */
@Entity(noClassnameStored = true)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8121775127353895008L;

    @Indexed
    private Long              version;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date              gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date              gmtModified;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        if (this.gmtCreate == null) {
            this.gmtCreate = now;
        }
        this.gmtModified = now;
    }

    // @Id
    // @JSONField(serialize = false, deserialize = false)
    // private ID id;
    // public ID getId() {
    // return this.id;
    // }
    //
    // public void setId(ID id) {
    // this.id = id;
    // }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return this.gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
