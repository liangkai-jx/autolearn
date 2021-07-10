package com.learn.service;

import com.learn.bean.*;
import com.learn.dao.commentinfo1;
import com.learn.dao.mainvideoInfo1;
import com.learn.dao.propositioninfo1;
import com.learn.dao.userregister;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface userInfoService {
    //登录
    userInfo userlogin(String account,String password);

    //分类全部课程
    List<mainvideoInfo> allCourses(String classid);

    //全部课程
    List<mainvideoInfo> getmainvideoinfo();

    //查询所有类别
    List<classinfo> getclassinfo();

    //按id查询主视频
    mainvideoInfo getmainvideoInfoid(String id);

    //课程详细
    List<subvideoinfo> getsubvideoid(String videoid);

    //按最新发表获取最新论题
    public List<propositioninfo1> getpropositioninfo1();

    //发表论题
    public int addpropositioninfo(String account, String author, String propositionname, String propositioncontent,Date publishtime);

    ////根据id查询论题
    propositioninfo1 getpropropositioninfoid(String id);

    //根据论题propositionid查询评论
    List<commentinfo1> getcommentpid(String propositionid);

    //添加评论
    int addcomment(String propositionid,String nickname,String comment,Date commenttime);

    //关键词查找论题
    List<propositioninfo1> getForAntistop(String ForAntistop);

    //我的论题
    List<propositioninfo1> getmyForum(@Param("account")String account);

    //删除论题
    int delForum(String id);

    //删除评论
    int delcomment(String propositionid);

    //个人信息修改
    int upUser(userInfo userInfo);

    //按手机号查找user
    userInfo getaccountUser(String account);

    //查找密码
    String getpass(String account);

    //修改密码
    int uppass(userregister userregister);

    //按手机号查找我的课程
    List<mainvideoInfo1> getmyvideo(String account);

    //查找是否关注
    attentioninfo getattentioninfo(String account,String mainvideoid);

    //查找相关的个数
    int Ucount( String antistop);

    //迷糊查询
    List<mainvideoInfo> Useek(String antistop);

    //关注课程
    int addattentioninfo(attentioninfo attentioninfo);

    //取消关注
    int delattentioninfo(int id);

    //关注加1
    int addgz(int id);

    //关注减1
    int delgz(int id);

    //查找可用轮播
    List<slideshowinfo> slideshow();

    //查找网站信息
    copyrightinfo copyright();

    //首页热门课程
    List<mainvideoInfo> rmmainvideoInfo();

}
