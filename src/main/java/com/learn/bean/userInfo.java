package com.learn.bean;

import lombok.Data;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/17 21:24
 * 用户表
 **/
@Data
public class userInfo {
    private int id;
    private String account;
    private String password;
    private int sex;
    private int age;
    private String nickname;
    private String mailbox;
    private int jurisdiction;
    private int state;
}
