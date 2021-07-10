package com.learn.controller;
import com.learn.bean.attentioninfo;
import com.learn.bean.mainvideoInfo;
import com.learn.bean.userInfo;
import com.learn.dao.commentinfo1;
import com.learn.dao.propositioninfo1;
import com.learn.dao.userregister;
import com.learn.service.touristService;
import com.learn.service.userInfoService;
import com.learn.utlis.Timedata;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/17 21:50
 **/
@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    userInfoService userInfoService;

    //登录
    @ResponseBody
    @RequestMapping("/login")
    public int  userlogin(HttpSession session, userInfo userInfo){
        int b=0;
        userInfo userInfo1= userInfoService.userlogin(userInfo.getAccount(),userInfo.getPassword());
        if (userInfo1!=null){
            b=1;
            if (userInfo1.getState()==2){
                b=2;
            }
            session.setAttribute("user",userInfo1);
        }
        return b;
    }
    //用户首页
    @RequestMapping("/userMain")
    public String userMain(Model model){
        //轮播图
        model.addAttribute("slides",userInfoService.slideshow());
        //热门课程
        model.addAttribute("rm",userInfoService.rmmainvideoInfo());
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/userMain";
    }

    //全部课程
    @RequestMapping("/allCourses")
    public String allCourses(String classid, String classname,Model model){
        if (classid.equals("0")){
            model.addAttribute("classname","全部");
        }
        else {
            model.addAttribute("classname",classname);
        }
        model.addAttribute("classid",classid);
        model.addAttribute("uclass",userInfoService.getclassinfo());
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/allCourses";
    }
     //全部课程分页数据
     @ResponseBody
     @RequestMapping("/upage")
     public List<mainvideoInfo> upage(String classid, String classname){
         List<mainvideoInfo> list;
        if (classid.equals("0")){
            list=userInfoService.getmainvideoinfo();
        }
        else {
            list=userInfoService.allCourses(classid);
        }

         return list;
     }

     //课程详细页面
    @RequestMapping("/detailpage")
    public String detailpage(String videoid,Model model,HttpSession session){
         userInfo userInfo=(userInfo)session.getAttribute("user");
         model.addAttribute("detaPd",userInfoService.getattentioninfo(userInfo.getAccount(),videoid));
         model.addAttribute("mainvideo",userInfoService.getmainvideoInfoid(videoid));
         model.addAttribute("subvideo",userInfoService.getsubvideoid(videoid));
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
         return "user/detailPage";
    }

    //视频播放
    @RequestMapping("/video")
    public String video(String videoaddress ,Model model,String videoid,String mainvideoname){
        model.addAttribute("videoaddress",videoaddress);
        model.addAttribute("videoid",videoid);
        model.addAttribute("mainvideoname",mainvideoname);
        return "user/video";

    }

    //学习路线页面
    @RequestMapping("/thought")
    public String thought(Model model){
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/Thought";
    }

    //讨论区界面
    @RequestMapping("/Forum")
    public String forum(Model model){
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/Forum";
    }

    //获取最新全部论题
    @ResponseBody
    @RequestMapping("/zxForum")
    public List<propositioninfo1> zxForum(){
        List<propositioninfo1> list=userInfoService.getpropositioninfo1();

        //时间格式转换
        for (propositioninfo1 b:list){
            b.setPublishtime1(Timedata.Stringconversion(b.getPublishtime()));
        }
        return list;
    }

    //发表论题页
    @RequestMapping("/publish")
    public String publish(){
        return "user/publish";
    }

     //富文本的图片上传
     @ResponseBody
     @RequestMapping(value="/uploadconimage",method=RequestMethod.POST)
     public Map<String,Object> uploadconimage(HttpServletRequest request, @RequestParam MultipartFile file) {
         Map<String,Object> mv=new HashMap<String, Object>();
         Map<String,String> mvv=new HashMap<String, String>();
         try {
             //项目源路径在有src/main/webApp时源路径才是项目路径、不加着在C盘创建临时路径
             String rootPath = request.getSession().getServletContext().getRealPath("/image/");
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
             String urlpat= "/image/"+ newfilename;
             mvv.put("src", urlpat);
             mvv.put("title",newfilename);
             mv.put("code", 0);
             mv.put("msg", "上传成功");
             mv.put("data", mvv);
             return mv;
         } catch (Exception e) {
             e.printStackTrace();
             mv.put("success", 1);
             return mv;
         }
     }

     //发表论题富文本上传
    @ResponseBody
    @RequestMapping("/addpropositioninfo")
    public int addpropositioninfo(String account, String author, String propositionname, String propositioncontent){
        Date publishtime=Timedata.hqdate();
        return userInfoService.addpropositioninfo(account,author,propositionname,propositioncontent,publishtime);
    }

    //论题详情显示
    @RequestMapping("/showProposition")
    public String showProposition(String id,Model model){
        propositioninfo1 propositioninfo1=userInfoService.getpropropositioninfoid(id);
        //时间转换
        propositioninfo1.setPublishtime1(Timedata.Stringconversion(propositioninfo1.getPublishtime()));
        model.addAttribute("proposition",propositioninfo1);
        List<commentinfo1> list=userInfoService.getcommentpid(id);
        //时间转换
        for (commentinfo1 b:list){
            b.setCommenttime1(Timedata.Stringconversion(b.getCommenttime()));
        }
        model.addAttribute("comment",list);
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/showProposition";
    }

    //发表评论
    @ResponseBody
    @RequestMapping("/addcomment")
    public int addcomment(String propositionid,String nickname,String comment){
        Date date=Timedata.hqdate();
        return userInfoService.addcomment(propositionid,nickname,comment,date);
    }

    //论题搜索界面
    @RequestMapping("/ForumSeek")
    public String ForumSeek(String ForAntistop,Model model){
        model.addAttribute("ForAntistop",ForAntistop);
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/ForumSeek";
    }
    //获取论题搜索数据
    @ResponseBody
    @RequestMapping("/AzxForum")
    public List<propositioninfo1> AzxForum(String ForAntistop){
        List<propositioninfo1> list=userInfoService.getForAntistop(ForAntistop);
        //时间格式转换
        for (propositioninfo1 b:list){
            b.setPublishtime1(Timedata.Stringconversion(b.getPublishtime()));
        }

        return list;
    }
    //我的论题
    @RequestMapping("/myForum")
    public String myForum(String account,Model model){
        List<propositioninfo1> list=userInfoService.getmyForum(account);
        //时间格式转换
        for (propositioninfo1 b:list){
            b.setPublishtime1(Timedata.Stringconversion(b.getPublishtime()));
        }
        model.addAttribute("myForum1",list);
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/myForum";
    }

    //用户删除论题（评论一起删除）
    @ResponseBody
    @RequestMapping("delForum")
    public int delForum(String id){
        int pd=0;
        if (userInfoService.delForum(id)==1){
            userInfoService.delcomment(id);
            pd=1;
        }
        return pd;
    }

    //个人信息界面
    @RequestMapping("/perInfo")
    public String perInfo(){
        return "user/perInfo";
    }
    //个人信息修改
    @ResponseBody
    @RequestMapping("/upUser")
    public int upUser(userInfo userInfo,HttpSession session){
        int n=userInfoService.upUser(userInfo);
        if (n>0){
           session.setAttribute("user",userInfoService.getaccountUser(userInfo.getAccount()));
        }
        return n;
    }

    //密码修改界面
    @RequestMapping("/upPass")
    public String upPass(){
        return "user/upPass";
    }
    //密码修改
    @ResponseBody
    @RequestMapping("/upPass1")
    public int upPass1(userregister userregister){
        int n=0;
         String pass=userInfoService.getpass(userregister.getAccount());
         if(pass.equals(userregister.getPassword())){
            n=1;
            userInfoService.uppass(userregister);
         }
        return n;
    }
    //学习任务
    @RequestMapping("/learnTasks")
    public String learnTasks(String account ,Model model){
        model.addAttribute("mylearn",userInfoService.getmyvideo(account));
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/learnTasks";
    }

    //搜索
    @RequestMapping("/Useek")
    public  String Useek(String antistop,Model model){
        int sum= userInfoService.Ucount(antistop);
        model.addAttribute("Ucount",sum);
        if(sum>0){
            List<mainvideoInfo> list=userInfoService.Useek(antistop);
            model.addAttribute("Useek",list);
        }
        //网站信息
        model.addAttribute("copy",userInfoService.copyright());
        return "user/Useek";
    }

    //关注课程
    @ResponseBody
    @RequestMapping("/addatt")
    public int addatt(attentioninfo attentioninfo){
        int n=userInfoService.addattentioninfo(attentioninfo);
        if (n==1){
            userInfoService.addgz(attentioninfo.getMainvideoid());
        }
        return n;
    }
    //取消关注
    @ResponseBody
    @RequestMapping("/delatt")
    public int delatt(int id){
        int n=userInfoService.delattentioninfo(id);
        if (n==1){
            userInfoService.delgz(id);
        }
        return n;
    }
    //退出
   @ResponseBody
   @RequestMapping("/signOut")
   public int signOut(HttpSession session){
        session.setAttribute("user",null);
        return 1;
   }

    //测试全部课程
    @RequestMapping("/test")
    @ResponseBody
    public List<mainvideoInfo> testpage(){
        List<mainvideoInfo> list=userInfoService.getmainvideoinfo();
        return list;
    }
}
