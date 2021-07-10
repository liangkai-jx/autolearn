package com.learn.service;

import com.learn.bean.*;
import com.learn.dao.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AdminService {
    //管理员登录
    userInfo adminlogin(String account, String password);
    //获取用户数据
    public List<userInfo1> userdate();
    //冻结
    int donjie(String account);
    //解冻
    int jiedon(String account);
    //个人信息修改
    int upUser(userInfo userInfo);
    //按手机号查找user
    userInfo getaccountUser(String account);
    //查找密码
    String getpass(String account);
    //修改密码
    int uppass(userregister userregister);
    //查询所有分类
    List<classinfo> getclassinfo();
    //添加类别
    int addclass(String classname);
    //删除类别
    int delclass( String id);
    //所有轮播
    List<slideshowinfo1> slideshowall();
    //显示轮播
    int slshow( String id);
    //不显示轮播
    int slnoshow( String id);
    //添加轮播
    int addslideshow(slideshowinfo slideshowinfo);
    //删除轮播
    int delslideshow(String id);
    //查找网站信息
    copyrightinfo copyright();
    //网站信息修改
    int updatecopy(copyrightinfo copyrightinfo);
    //全部主视频
    List<mainvideoInfo> getmainvideoinfo();
    //添加主视频
    int addMainVideo(mainvideoInfo mainvideoInfo);
    //删除主视频
    int delmainVideo(String id);
    //按id查找主视频
    mainvideoInfo getmainvideoInfoid(String id);
    //按id修改
    int updatemainvideo(mainvideoInfo mainvideoInfo);
    //分类查找所有子视频
    List<subvideoinfo> getflsubvideoid();
    //添加子视频
    int addsubvideo(subvideoinfo subvideoinfo);
    //删除子视频
    int delsubvideo(String id);
    //按id查找子视频
    subvideoinfo getsubid( int id);
    //按id修改子视频
    int updatesubid(subvideoinfo subvideoinfo);
    //按videoid修改子视频
    int updatesubvideoid(@Param("mainvideoname") String mainvideoname,@Param("videoid") int videoid);
    //获取分组排序全部论题
    List<propositioninfo1> getflpropositioninfo1();
    ////根据id查询论题
    propositioninfo1 getpropropositioninfoid(String id);
    //根据论题propositionid查询评论
    List<commentinfo1> getcommentpid(String propositionid);
    //删除论题
    int delForum(String id);
    //删除评论
    int delcomment(String propositionid);
    //获取分组排序全部评论
    List<commentinfo1> getflcommentpid();
    //删除评论id
    int delcommentid(@Param("id")String id);

}
