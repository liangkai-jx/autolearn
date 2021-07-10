package com.learn.bean;

import lombok.Data;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/25 22:42
 * 主视频信息表
 **/
@Data
public class mainvideoInfo {
    private int id;
    private int classid;
    private String mainvideoname;
    private String coveraddress;
    private String videointroduction;
    private int gzNum;
}
