package com.learn.mapper;

import com.learn.bean.copyrightinfo;
import com.learn.bean.mainvideoInfo;
import com.learn.bean.slideshowinfo;
import com.learn.bean.userInfo;
import com.learn.dao.userregister;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface touristMapper {
    //查看账号是否存在
    @Select("select * from userinfo where account=#{account}")
    userInfo regaccount(@Param("account")String account);

    //查看邮箱是否存在
    @Select("select * from userinfo where mailbox=#{mailbox}")
    userInfo regmailbox(@Param("mailbox")String mailbox);

    //注册
    @Insert("INSERT INTO userinfo(account, password, sex, age, nickname,mailbox,jurisdiction) VALUES(#{account}, #{password}, #{sex}, #{age}, #{nickname}, #{mailbox}, #{jurisdiction})")
    int register(userregister userregister);

    //查找相关的个数
    @Select("select count(*) from mainvideoinfo where mainvideoname like concat('%',#{antistop},'%') ")
    int Tcount(@Param("antistop") String antistop);

    //迷糊查询
    @Select("select * from mainvideoinfo where mainvideoname like concat('%',#{antistop},'%') ")
    List<mainvideoInfo> Tseek(@Param("antistop") String antistop);

    //首页热门课程
    @Select("select * from mainvideoinfo ORDER BY gzNum DESC limit 0,9")
    List<mainvideoInfo> rmmainvideoInfo();

    //查找可用轮播
    @Select("select *from slideshowinfo where slideshowreveal=1")
    List<slideshowinfo> slideshow();

    //查找网站信息
    @Select("select *from copyrightinfo")
    copyrightinfo copyright();
}
