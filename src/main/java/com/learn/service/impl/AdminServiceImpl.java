package com.learn.service.impl;

import com.learn.bean.*;
import com.learn.dao.*;
import com.learn.mapper.*;
import com.learn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CY小鱼儿
 * @Date 2021/5/8 15:01
 **/
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    userInfoMapper userInfoMapper;

    @Autowired
    classinfoMapper classinfoMapper;

    @Autowired
    slideshowinfoMapper slideshowinfoMapper;

    @Autowired
    copyrightinfoMapper copyrightinfoMapper;

    @Autowired
    mainvideoinfoMapper mainvideoinfoMapper;

    @Autowired
    subvideoinfoMapper subvideoinfoMapper;

    @Autowired
    propositioninfoMapper propositioninfoMapper;

    @Autowired
    commentinfoMapper commentinfoMapper;

    @Override
    public userInfo adminlogin(String account, String password) {
        return userInfoMapper.adminlogin(account,password);
    }

    @Override
    public List<userInfo1> userdate() {
        return userInfoMapper.userdate();
    }

    @Override
    public int donjie(String account) {
        return userInfoMapper.donjie(account);
    }

    @Override
    public int jiedon(String account) {
        return userInfoMapper.jiedon(account);
    }

    @Override
    public int upUser(userInfo userInfo) {
        return userInfoMapper.upUser(userInfo);
    }

    @Override
    public userInfo getaccountUser(String account) {
        return userInfoMapper.getaccountUser(account);
    }

    @Override
    public String getpass(String account) {
        return userInfoMapper.getpass(account);
    }

    @Override
    public int uppass(userregister userregister) {
        return userInfoMapper.uppass(userregister);
    }

    @Override
    public List<classinfo> getclassinfo() {
        return classinfoMapper.getclassinfo();
    }

    @Override
    public int addclass(String classname) {
        return classinfoMapper.addclass(classname);
    }

    @Override
    public int delclass(String id) {
        return classinfoMapper.delclass(id);
    }

    @Override
    public List<slideshowinfo1> slideshowall() {
        return slideshowinfoMapper.slideshowall();
    }

    @Override
    public int slshow(String id) {
        return slideshowinfoMapper.slshow(id);
    }

    @Override
    public int slnoshow(String id) {
        return slideshowinfoMapper.slsnoshow(id);
    }

    @Override
    public int addslideshow(slideshowinfo slideshowinfo) {
        return slideshowinfoMapper.addslideshow(slideshowinfo);
    }

    @Override
    public int delslideshow(String id) {
        return slideshowinfoMapper.delslideshow(id);
    }

    @Override
    public copyrightinfo copyright() {
        return copyrightinfoMapper.copyright();
    }

    @Override
    public int updatecopy(copyrightinfo copyrightinfo) {
        return copyrightinfoMapper.updatecopy(copyrightinfo);
    }

    @Override
    public List<mainvideoInfo> getmainvideoinfo() {
        return mainvideoinfoMapper.getmainvideoinfo();
    }

    @Override
    public int addMainVideo(mainvideoInfo mainvideoInfo) {
        return mainvideoinfoMapper.addMainVideo(mainvideoInfo);
    }

    @Override
    public int delmainVideo(String id) {
        return mainvideoinfoMapper.delmainVideo(id);
    }

    @Override
    public mainvideoInfo getmainvideoInfoid(String id) {
        return mainvideoinfoMapper.getmainvideoInfoid(id);
    }

    @Override
    public int updatemainvideo(mainvideoInfo mainvideoInfo) {
        return mainvideoinfoMapper.updatemainvideo(mainvideoInfo);
    }

    @Override
    public List<subvideoinfo> getflsubvideoid() {
        return subvideoinfoMapper.getflsubvideoid();
    }

    @Override
    public int addsubvideo(subvideoinfo subvideoinfo) {
        return subvideoinfoMapper.addsubvideo(subvideoinfo);
    }

    @Override
    public int delsubvideo(String id) {
        return subvideoinfoMapper.delsubvideo(id);
    }

    @Override
    public subvideoinfo getsubid(int id) {
        return subvideoinfoMapper.getsubid(id);
    }

    @Override
    public int updatesubid(subvideoinfo subvideoinfo) {
        return subvideoinfoMapper.updatesubid(subvideoinfo);
    }

    @Override
    public int updatesubvideoid(String mainvideoname, int videoid) {
        return subvideoinfoMapper.updatesubvideoid(mainvideoname,videoid);
    }

    @Override
    public List<propositioninfo1> getflpropositioninfo1() {
        return propositioninfoMapper.getflpropositioninfo1();
    }

    @Override
    public propositioninfo1 getpropropositioninfoid(String id) {
        return propositioninfoMapper.getpropropositioninfoid(id);
    }

    @Override
    public List<commentinfo1> getcommentpid(String propositionid) {
        return commentinfoMapper.getcommentpid(propositionid);
    }

    @Override
    public int delForum(String id) {
        return propositioninfoMapper.delForum(id);
    }

    @Override
    public int delcomment(String propositionid) {
        return commentinfoMapper.delcomment(propositionid);
    }

    @Override
    public List<commentinfo1> getflcommentpid() {
        return commentinfoMapper.getflcommentpid();
    }

    @Override
    public int delcommentid(String id) {
        return commentinfoMapper.delcommentid(id);
    }
}
