package com.mq.rabbit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jack
 * @since 2019-07-17
 */
@TableName("sys_log")
@Data
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String ip;
    private String type;
    @TableField("user_id")
    private Integer userId;
    private String description;
    @TableField("exception_code")
    private String exceptionCode;
    @TableField("exception_detail")
    private String exceptionDetail;
    @TableField("action_method")
    private String actionMethod;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(String actionMethod) {
        this.actionMethod = actionMethod;
    }

    @Override
    public String toString() {
        return "SysLog{" +
        ", id=" + id +
        ", ip=" + ip +
        ", type=" + type +
        ", userId=" + userId +
        ", description=" + description +
        ", exceptionCode=" + exceptionCode +
        ", exceptionDetail=" + exceptionDetail +
        ", actionMethod=" + actionMethod +
        "}";
    }
}
