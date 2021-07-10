package com.learn.dao;

import lombok.Data;

import java.util.Date;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/29 17:06
 **/
@Data
public class propositioninfo1 {
    private int id;
    private String account;
    private String author;
    private String propositionname;
    private String propositioncontent;
    private Date publishtime;
    private String publishtime1;
}
