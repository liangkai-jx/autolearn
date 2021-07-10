package com.learn.dao;

import lombok.Data;

import java.util.Date;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/30 2:30
 **/
@Data
public class commentinfo1 {
    private int id;
    private int propositionid;
    private String nickname;
    private String comment;
    private Date commenttime;
    private String commenttime1;
}
