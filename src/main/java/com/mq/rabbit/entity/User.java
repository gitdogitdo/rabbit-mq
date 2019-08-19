package com.mq.rabbit.entity;

import lombok.Data;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author ipfs
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8407096288290128493L;
    private String userName;

    private String password;

    private String sex;

    private String level;
}
