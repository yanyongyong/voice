package com.lxjk.datacollection.data.entity;

import lombok.Data;

/**
 * @Author: ziv
 * @Date: 2018/5/18 16:16
 * @Description:
 */
@Data
public class User {

    public String username;
    public String password;

    public User() {
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
