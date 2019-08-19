package com.mq.rabbit.utils;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResponseMessage<T> implements Serializable {

    private static final long serialVersionUID = 8992436576262574064L;

    protected String message = "";

    protected T data;

    protected int code;

    private Long timestamp = System.currentTimeMillis();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public static <T> ResponseMessage<T> error(String message) {
        return error(500, message);
    }

    public static <T> ResponseMessage<T> error(int code, String message) {
        ResponseMessage<T> msg = new ResponseMessage<T>();
        if (message != null && !"".equals(message)){
            msg.message = message;
        } else{
            msg.message = "success";
        }
        msg.code(code);
        return msg.putTimeStamp();
    }

    public static <T> ResponseMessage<T> ok() {
        return ok(null);
    }

    private ResponseMessage<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public static <T> ResponseMessage<T> ok(T data) {
        return new ResponseMessage<T>()
                .data(data)
                .putTimeStamp()
                .code(200);
    }

    public ResponseMessage<T> data(T data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", this.getData());
        map.put("message", this.getMessage());
        map.put("code", this.getCode());
        map.put("timestamp", this.getTimestamp());
        return map;
    }

    /**
     * 过滤字段：指定需要序列化的字段
     */
    @JsonIgnore
    private transient Map<Class<?>, Set<String>> includes;

    /**
     * 过滤字段：指定不需要序列化的字段
     */
    @JsonIgnore
    private transient Map<Class<?>, Set<String>> excludes;

    public ResponseMessage() {

    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ResponseMessage<T> code(int code) {
        this.code = code;
        return this;
    }

    public Map<Class<?>, Set<String>> getExcludes() {
        return excludes;
    }

    public Map<Class<?>, Set<String>> getIncludes() {
        return includes;
    }

    public ResponseMessage<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseMessage<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseMessage<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


}