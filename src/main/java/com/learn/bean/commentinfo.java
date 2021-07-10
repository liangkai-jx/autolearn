package com.learn.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/30 2:30
 **/
@Data
public class commentinfo {
    private int id;
    private int propositionid;
    private String nickname;
    private String comment;
    private Date commenttime;
}
