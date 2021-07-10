package com.learn.controller;

import com.learn.bean.*;
import com.learn.dao.*;
import com.learn.service.AdminService;
import com.learn.service.userInfoService;
import com.learn.utlis.Timedata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @Author CY小鱼儿
 * @Date 2021/5/8 1:38
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

     //管理员登录界面
    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "admin/adminLogin";
    }
    //登录
    @ResponseBody
    @RequestMapping("/login")
    public int  userlogin(HttpSession session, userInfo userInfo){
        int b=0;
        userInfo userInfo1= adminService.adminlogin(userInfo.getAccount(),userInfo.getPassword());
        if (userInfo1!=null){
            b=1;
            session.setAttribute("admin",userInfo1);
        }
        return b;
    }
    //管理员主页
    @RequestMapping("/adminMain")
    public String adminMain(){
        return "admin/adminMain";
    }

    //后台欢迎页
    @RequestMapping("/WelcomePage")
    public String WelcomePage(){
        return "admin/WelcomePage";
    }

    //用户管理页
    @RequestMapping("/userManagement")
    public String userManagement(){
        return "admin/userManagement";
    }
    //获取用户数据
    @ResponseBody
    @RequestMapping("/userdate")
    public List<userInfo1> userdate(){
        List<userInfo1> userInfo1s=adminService.userdate();
        for (userInfo1 p:userInfo1s){
            if (p.getState()==1){
                p.setState1("正常");
            }else{
                p.setState1("冻结");
            }
        }
        return userInfo1s;
    }
    //冻结
    @ResponseBody
    @RequestMapping("/donjie")
    public int donjie(String account){
        return adminService.donjie(account);
    }
    //解冻
    @ResponseBody
    @RequestMapping("/jiedon")
    public int jiedon(String account){
        return adminService.jiedon(account);
    }
    //个人信息界面
    @RequestMapping("/perInfo")
    public String perInfo(){
        return "admin/perInfo";
    }
    //个人信息修改
    @ResponseBody
    @RequestMapping("/upUser")
    public int upUser(userInfo userInfo,HttpSession session){
        int n=adminService.upUser(userInfo);
        if (n>0){
            session.setAttribute("admin",adminService.getaccountUser(userInfo.getAccount()));
        }
        return n;
    }
    //密码修改界面
    @RequestMapping("/upPass")
    public String upPass(){
        return "admin/upPass";
    }
    //密码修改
    @ResponseBody
    @RequestMapping("/upPass1")
    public int upPass1(userregister userregister){
        int n=0;
        String pass=adminService.getpass(userregister.getAccount());
        if(pass.equals(userregister.getPassword())){
            n=1;
            adminService.uppass(userregister);
        }
        return n;
    }

    //类别管理页
    @RequestMapping("/classManagement")
    public String classManagement(){
        return "admin/classManagement";
    }
    //查询所有分类
    @ResponseBody
    @RequestMapping("/getclassinfo")
    public List<classinfo> getclassinfo(){
        return adminService.getclassinfo();
    }
    //添加类别
    @ResponseBody
    @RequestMapping("/addclass")
    public int addclass(String classname){
        return adminService.addclass(classname);
    }
    //删除类别
    @ResponseBody
    @RequestMapping("/delclass")
    public int delclass(String id){
        return adminService.delclass(id);
    }

    //轮播图管理页
    @RequestMapping("/slideshowManagement")
    public String slideshowManagement(){
        return "admin/slideshowManagement";
    }
    //所有轮播图
    @ResponseBody
    @RequestMapping("/slideshowall")
    public List<slideshowinfo1> slideshowall(){
        List<slideshowinfo1> slideshowinfo1s=adminService.slideshowall();
        for (slideshowinfo1 p:slideshowinfo1s){
            if (p.getSlideshowreveal()==1){
                p.setSlideshowreveal1("显示");
            }else{
                p.setSlideshowreveal1("不显示");
            }
        }
        return slideshowinfo1s;
    }
    //显示轮播
    @ResponseBody
    @RequestMapping("/slshow")
    public int slshow(String id){
        return adminService.slshow(id);
    }
    //不显示轮播
    @ResponseBody
    @RequestMapping("/slnoshow")
    public int slnoshow(String id){
        return adminService.slnoshow(id);
    }
    //添加轮播界面
    @RequestMapping("/slideshowAdd")
    public String slideshowAdd(){
        return  "admin/slideshowAdd";
    }
    //添加轮播
    @ResponseBody
    @RequestMapping("/addslideshow")
    int addslideshow(String slideshowname, HttpServletRequest request,@RequestParam(name = "file") MultipartFile file){
        slideshowinfo slideshowinfo=new slideshowinfo();
        slideshowinfo.setSlideshowname(slideshowname);
        try {
            //项目源路径在有src/main/webApp时源路径才是项目路径、不加着在C盘创建临时路径
            String rootPath = request.getSession().getServletContext().getRealPath("/img/");
            //获得文件最初的路径
            String originalFile = file.getOriginalFilename();
            //Calendar.getInstance()是获取一个Calendar对象并可以进行时间的计算，时区的指定
            Calendar date = Calendar.getInstance();
            //UUID转化为String对象
            String uuid = UUID.randomUUID().toString();
            String newfilename=date.get(Calendar.YEAR)+""+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DATE)+uuid.replace("-", "")+originalFile;
            //得到完整路径名
            File newFile = new File(rootPath+newfilename);
             /*文件不存在就创建*/
            if(!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            //上传文件
            file.transferTo(newFile);
            //文件相对路径
            String urlpat= "/img/"+ newfilename;
            slideshowinfo.setSlideshowaddress(urlpat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminService.addslideshow(slideshowinfo);
    }
    //删除轮播
    @ResponseBody
    @RequestMapping("/delslideshow")
    int delslideshow(String id, String slideshowaddress, HttpServletRequest request){
        try {
            //项目源路径在有src/main/webApp时源路径才是项目路径、不加着在C盘创建临时路径
            String rootPath = request.getSession().getServletContext().getRealPath("");
            //获得文件最初的路径
            File file = new File(rootPath+slideshowaddress);
               file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminService.delslideshow(id);
    }

    //网站信息管理页
    @RequestMapping("/copyManagement")
    public String copyManagement(){
        return "admin/copyManagement";
    }
    //网站信息显示
    @ResponseBody
    @RequestMapping("/getcopyright")
    public copyrightinfo getcopyright(){
        return adminService.copyright();
    }
    //网站信息修改
    @ResponseBody
    @RequestMapping("/updatecopy")
    public int updatecopy(copyrightinfo copyrightinfo){
        return adminService.updatecopy(copyrightinfo);
    }

    //视频管理
    //主视频管理页
    @RequestMapping("/MainVideoManagement")
    public String MainVideoManagement(){
        return "admin/MainVideoManagement";
    }
    //获取所有主视频
    @ResponseBody
    @RequestMapping("/getmainvideoinfo")
    public List<mainvideoInfo> getmainvideoinfo(){
      return adminService.getmainvideoinfo();
    }
    //添加主视频页
    @RequestMapping("/MainVideoAdd")
    public String MainVideoAdd(){
        return "admin/MainVideoAdd";
    }
    //添加主视频
    @ResponseBody
    @RequestMapping("/addMainVideo")
    int addMainVideo(String mainvideoname ,String videointroduction,int classid , HttpServletRequest request,@RequestParam(name = "file") MultipartFile file){
        mainvideoInfo mainvideoInfo=new mainvideoInfo();
        mainvideoInfo.setMainvideoname(mainvideoname);
        mainvideoInfo.setVideointroduction(videointroduction);
        mainvideoInfo.setClassid(classid);
        try {
            //项目源路径在有src/main/webApp时源路径才是项目路径、不加着在C盘创建临时路径
            String rootPath = request.getSession().getServletContext().getRealPath("/img/");
            //获得文件最初的路径
            String originalFile = file.getOriginalFilename();
            //Calendar.getInstance()是获取一个Calendar对象并可以进行时间的计算，时区的指定
            Calendar date = Calendar.getInstance();
            //UUID转化为String对象
            String uuid = UUID.randomUUID().toString();
            String newfilename=date.get(Calendar.YEAR)+""+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DATE)+uuid.replace("-", "")+originalFile;
            //得到完整路径名
            File newFile = new File(rootPath+newfilename);
             /*文件不存在就创建*/
            if(!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            //上传文件
            file.transferTo(newFile);
            //文件相对路径
            String urlpat= "/img/"+ newfilename;
           mainvideoInfo.setCoveraddress(urlpat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminService.addMainVideo(mainvideoInfo);
    }
    //删除主视频
    @ResponseBody
    @RequestMapping("/delmainVideo")
    int delmainVideo(String id, String coveraddress, HttpServletRequest request){
        try {
            //项目源路径在有src/main/webApp时源路径才是项目路径、不加着在C盘创建临时路径
            String rootPath = request.getSession().getServletContext().getRealPath("");
            //获得文件最初的路径
            File file = new File(rootPath+coveraddress);
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminService.delmainVideo(id);
    }
    //视频信息修改页
    @RequestMapping("/MainVideoUpdate")
    public String MainVideoUpdate(String id,Model model){
        model.addAttribute("mainvideo",adminService.getmainvideoInfoid(id));
        return "admin/MainVideoUpdate";
    }
    //修改视频名称
    @ResponseBody
    @RequestMapping("/updaMainVideo")
    int updaMainVideo(mainvideoInfo mainvideoInfo,Model model){
        int n=adminService.updatemainvideo(mainvideoInfo);
        model.addAttribute("mainvideo",adminService.getmainvideoInfoid(mainvideoInfo.getId()+""));
        adminService.updatesubvideoid(mainvideoInfo.getMainvideoname(),mainvideoInfo.getId());
        return n;
    }


    //子视频管理
    //子视频页面
    @RequestMapping("/subvideoManagement")
    public String subvideoManagement(){
        return "admin/subvideoManagement";
    }
    //分类查找所有视频
    @ResponseBody
    @RequestMapping("/getflsubvideoid")
    public List<subvideoinfo> getflsubvideoid(){
        return adminService.getflsubvideoid();
    }
    //添加子视频页
    @RequestMapping("/subvideoAdd")
    public String subvideoAdd(){
        return "admin/subvideoAdd";
    }
    //添加子视频
    @ResponseBody
    @RequestMapping("/addsubvideo")
    int addsubvideo(String mainvideoname ,String subvideoname,int videoid , HttpServletRequest request,@RequestParam(name = "file") MultipartFile file){
        subvideoinfo subvideoinfo=new subvideoinfo();
        subvideoinfo.setMainvideoname(mainvideoname);
        subvideoinfo.setSubvideoname(subvideoname);
        subvideoinfo.setVideoid(videoid);
        try {
            //项目源路径在有src/main/webApp时源路径才是项目路径、不加着在C盘创建临时路径
            String rootPath = request.getSession().getServletContext().getRealPath("/video/");
            //获得文件最初的路径
            String originalFile = file.getOriginalFilename();
            //Calendar.getInstance()是获取一个Calendar对象并可以进行时间的计算，时区的指定
            Calendar date = Calendar.getInstance();
            //UUID转化为String对象
            String uuid = UUID.randomUUID().toString();
            String newfilename=date.get(Calendar.YEAR)+""+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DATE)+uuid.replace("-", "")+originalFile;
            //得到完整路径名
            File newFile = new File(rootPath+newfilename);
             /*文件不存在就创建*/
            if(!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            //上传文件
            file.transferTo(newFile);
            //文件相对路径
            String urlpat= "/video/"+ newfilename;
            subvideoinfo.setVideoaddress(urlpat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminService.addsubvideo(subvideoinfo);
    }
    //删除子视频
    @ResponseBody
    @RequestMapping("/delsubvideo")
    int delsubvideo(String id, String videoaddress, HttpServletRequest request){
        try {
            //项目源路径在有src/main/webApp时源路径才是项目路径、不加着在C盘创建临时路径
            String rootPath = request.getSession().getServletContext().getRealPath("");
            //获得文件最初的路径
            File file = new File(rootPath+videoaddress);
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminService.delsubvideo(id);
    }
    //视频播放
    @RequestMapping("/video")
    public String video(String videoaddress ,String mainvideoname, Model model){
        model.addAttribute("videoaddress",videoaddress);
        model.addAttribute("mainvideoname",mainvideoname);
        return "admin/video";
    }
    //视频信息修改页
    @RequestMapping("/subvideoupdate")
    public String subvideoupdate(int id,Model model){
        model.addAttribute("subvideo",adminService.getsubid(id));
        return "admin/subvideoupdate";
    }
    //修改视频名称
    @ResponseBody
    @RequestMapping("/updasubvideo")
    int updatesubid(subvideoinfo subvideoinfo,Model model){
        int n=adminService.updatesubid(subvideoinfo);
        model.addAttribute("subvideo",adminService.getsubid(subvideoinfo.getId()));
        return n;
    }

    //讨论区管理
    //论题管理界面
    @RequestMapping("/propositionManagement")
    public String propositionManagement(){
        return "admin/propositionManagement";
    }
    //获取分类全部论题
    @ResponseBody
    @RequestMapping("/getflpropositioninfo1")
    public List<propositioninfo1> getflpropositioninfo1(){
        List<propositioninfo1> list= adminService.getflpropositioninfo1();
        //时间格式转换
        for (propositioninfo1 b:list){
            b.setPublishtime1(Timedata.Stringconversion(b.getPublishtime()));
        }
        return list;
    }
    //查看论题详情
    @RequestMapping("/propositionshow")
    public String propositionshow(String id,Model model){
        propositioninfo1 propositioninfo1=adminService.getpropropositioninfoid(id);
        //时间转换
        propositioninfo1.setPublishtime1(Timedata.Stringconversion(propositioninfo1.getPublishtime()));
        model.addAttribute("proposition",propositioninfo1);
        List<commentinfo1> list=adminService.getcommentpid(id);
        //时间转换
        for (commentinfo1 b:list){
            b.setCommenttime1(Timedata.Stringconversion(b.getCommenttime()));
        }
        model.addAttribute("comment",list);
        return "admin/propositionshow";
    }
    //管理员删除论题（评论一起删除）
    @ResponseBody
    @RequestMapping("delpropos")
    public int delpropos(String id){
        int pd=0;
        if (adminService.delForum(id)==1){
            adminService.delcomment(id);
            pd=1;
        }
        return pd;
    }
    //评论页
    @RequestMapping("/commentinfoManagement")
    public String commentinfoManagement(){
        return "admin/commentinfoManagement";
    }
    //获取全部评论
    @ResponseBody
    @RequestMapping("/getflcommentpid")
    public List<commentinfo1> getflcommentpid(String id) {
        List<commentinfo1> list = adminService.getflcommentpid();
        //时间转换
        for (commentinfo1 b : list) {
            b.setCommenttime1(Timedata.Stringconversion(b.getCommenttime()));
        }
        return list;
    }
    //删除评论
    @ResponseBody
    @RequestMapping("/delcomm")
    public int delcomm(String id){
        return adminService.delcommentid(id);
    }


    //退出
    @RequestMapping("/signOut")
    public String signOut(HttpSession session){
        session.setAttribute("admin",null);
        return "admin/adminLogin";
    }
}
