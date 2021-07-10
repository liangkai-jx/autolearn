package com.learn.service.impl;

import com.learn.bean.*;
import com.learn.dao.commentinfo1;
import com.learn.dao.mainvideoInfo1;
import com.learn.dao.propositioninfo1;
import com.learn.dao.userregister;
import com.learn.mapper.*;
import com.learn.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/17 21:45
 **/
@Service
public class UserInfoServiceImpl implements userInfoService {

    @Autowired
    userInfoMapper userInfoMapper;

    @Autowired
    classinfoMapper classinfoMapper;

    @Autowired
    subvideoinfoMapper subvideoinfoMapper;

    @Autowired
    mainvideoinfoMapper mainvideoinfoMapper;

    @Autowired
    propositioninfoMapper propositioninfoMapper;

    @Autowired
    commentinfoMapper commentinfoMapper;

    @Autowired
    attentioninfoMapper attentioninfoMapper;

    @Autowired
    slideshowinfoMapper slideshowinfoMapper;

    @Autowired
    copyrightinfoMapper copyrightinfoMapper;
    @Override
    public userInfo userlogin(String account, String password) {
        return userInfoMapper.userlogin(account,password);
    }

    @Override
    public List<mainvideoInfo> allCourses(String classid) {
        return mainvideoinfoMapper.allCourses(classid);
    }

    @Override
    public List<mainvideoInfo> getmainvideoinfo() {
        return mainvideoinfoMapper.getmainvideoinfo();
    }

    @Override
    public List<classinfo> getclassinfo() {
        return classinfoMapper.getclassinfo();
    }

    @Override
    public mainvideoInfo getmainvideoInfoid(String id) {
        return mainvideoinfoMapper.getmainvideoInfoid(id);
    }

    @Override
    public List<subvideoinfo> getsubvideoid(String videoid) {
        return subvideoinfoMapper.getsubvideoid(videoid);
    }


    @Override
    public List<propositioninfo1> getpropositioninfo1() {
        return propositioninfoMapper.getpropositioninfo1();
    }

    @Override
    public int addpropositioninfo(String account, String author, String propositionname, String propositioncontent,Date publishtime) {
        return propositioninfoMapper.addpropositioninfo(account,author,propositionname,propositioncontent,publishtime);
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
    public int addcomment(String propositionid, String nickname, String comment, Date commenttime) {
        return commentinfoMapper.addcomment(propositionid,nickname,comment,commenttime);
    }

    @Override
    public List<propositioninfo1> getForAntistop(String ForAntistop) {
        return propositioninfoMapper.getForAntistop(ForAntistop);
    }

    @Override
    public List<propositioninfo1> getmyForum(String account) {
        return propositioninfoMapper.getmyForum(account);
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
    public List<mainvideoInfo1> getmyvideo(String account) {
        return attentioninfoMapper.getmyvideo(account);
    }

    @Override
    public attentioninfo getattentioninfo(String account, String mainvideoid) {
        return attentioninfoMapper.getattentioninfo(account,mainvideoid);
    }

    @Override
    public int Ucount(String antistop) {
        return mainvideoinfoMapper.Ucount(antistop);
    }

    @Override
    public List<mainvideoInfo> Useek(String antistop) {
        return mainvideoinfoMapper.Useek(antistop);
    }

    @Override
    public int addattentioninfo(attentioninfo attentioninfo) {
        return attentioninfoMapper.addattentioninfo(attentioninfo);
    }

    @Override
    public int delattentioninfo(int id) {
        return attentioninfoMapper.delattentioninfo(id);
    }

    @Override
    public int addgz(int id) {
        return mainvideoinfoMapper.addgz(id);
    }

    @Override
    public int delgz(int id) {
        return mainvideoinfoMapper.delgz(id);
    }

    @Override
    public List<slideshowinfo> slideshow() {
        return slideshowinfoMapper.slideshow();
    }

    @Override
    public copyrightinfo copyright() {
        return copyrightinfoMapper.copyright();
    }

    @Override
    public List<mainvideoInfo> rmmainvideoInfo() {
        return mainvideoinfoMapper.rmmainvideoInfo();
    }


}
