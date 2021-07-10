package com.learn.bean;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/29 17:06
 **/
@Data
public class propositioninfo {
    private int id;
    private String account;
    private String author;
    private String propositionname;
    private String propositioncontent;
    private Date publishtime;
}
