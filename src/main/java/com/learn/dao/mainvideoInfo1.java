package com.learn.dao;

import com.learn.bean.attentioninfo;
import lombok.Data;

/**
 * @Author CY小鱼儿
 * @Date 2021/5/7 3:04
 **/
@Data
public class mainvideoInfo1 {
    private int id;
    private int classid;
    private String mainvideoname;
    private String coveraddress;
    private String videointroduction;
    private int gzNum;
    private attentioninfo attentioninfo;
}
