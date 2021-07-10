package com.learn.service;

import com.learn.bean.copyrightinfo;
import com.learn.bean.mainvideoInfo;
import com.learn.bean.slideshowinfo;
import com.learn.bean.userInfo;
import com.learn.dao.userregister;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface touristService {
    //注册
    int register(userregister userregister);
    //查找搜索个数
    int Tcount(String antistop);
    //查找搜索集合
   List<mainvideoInfo> Tseek(String antistop);
    //首页热门课程
    List<mainvideoInfo> rmmainvideoInfo();
    //查找可用轮播
    List<slideshowinfo> slideshow();
    //查找网站信息
    copyrightinfo copyright();
    //查看账号是否存在
    userInfo regaccount(String account);

    //查看邮箱是否存在
    userInfo regmailbox(String mailbox);
}
