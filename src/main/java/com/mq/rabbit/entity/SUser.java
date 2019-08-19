package com.mq.rabbit.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jack
 * @since 2019-07-17
 */
@TableName("s_user")
public class SUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码盐
     */
    private String salt;
    /**
     * 用户状态
     */
    private BigDecimal status;
    /**
     * 上一次登录的ip地址
     */
    @TableField("last_login_ip")
    private String lastLoginIp;
    /**
     * 上一次登录时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
    /**
     * 创建者ID
     */
    @TableField("creator_id")
    private String creatorId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 当前节点hash
     */
    @TableField("point_hash")
    private String pointHash;
    private String telephone;
    private LocalDateTime birthday;
    private String address;
    private String avatar;
    @TableField("e_mail")
    private String eMail;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getPointHash() {
        return pointHash;
    }

    public void setPointHash(String pointHash) {
        this.pointHash = pointHash;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "SUser{" +
        ", id=" + id +
        ", name=" + name +
        ", username=" + username +
        ", password=" + password +
        ", salt=" + salt +
        ", status=" + status +
        ", lastLoginIp=" + lastLoginIp +
        ", updateTime=" + updateTime +
        ", creatorId=" + creatorId +
        ", createTime=" + createTime +
        ", pointHash=" + pointHash +
        ", telephone=" + telephone +
        ", birthday=" + birthday +
        ", address=" + address +
        ", avatar=" + avatar +
        ", eMail=" + eMail +
        "}";
    }
}
