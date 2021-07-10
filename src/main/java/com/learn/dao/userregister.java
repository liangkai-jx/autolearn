package com.learn.dao;

import lombok.Data;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/20 18:38
 **/
@Data
public class userregister {
        private String account;
        private String password;
        private String password1;
        private int sex;
        private int age;
        private String nickname;
        private String mailbox;
        private int jurisdiction;
}