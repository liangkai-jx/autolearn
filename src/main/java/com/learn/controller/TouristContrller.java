package com.learn.controller;

import com.learn.bean.copyrightinfo;
import com.learn.bean.mainvideoInfo;
import com.learn.bean.slideshowinfo;
import com.learn.dao.userregister;
import com.learn.service.touristService;
import com.learn.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/25 20:07
 **/
@Controller
@RequestMapping("/tourist")
public class TouristContrller {
    @Autowired
    touristService touristService;

    //注册
    @ResponseBody
    @RequestMapping("/register")
    public  int userregister(userregister userregister){

        if (touristService.regaccount(userregister.getAccount())!=null){
            return 1;
        }else if(touristService.regmailbox(userregister.getMailbox())!=null){
            return 2;
        }else {
            touristService.register(userregister);
            return 3;
        }

    }

    //搜索
    @RequestMapping("/seek")
    public  String seek(String antistop,Model model){
       int sum=touristService.Tcount(antistop);
       model.addAttribute("Tcount",sum);
       if(sum>0){
        List<mainvideoInfo> list=touristService.Tseek(antistop);
           model.addAttribute("Tseek",list);
       }
        //网站信息
        model.addAttribute("copy",touristService.copyright());
        return "seek";
    }

    //热门课程
    @ResponseBody
    @RequestMapping("/rmkc")
    public List<mainvideoInfo> rmkc(){
        return touristService.rmmainvideoInfo();
    }
    //轮播图
    @ResponseBody
    @RequestMapping("/slideshow")
    public List<slideshowinfo> slideshow(){
        return touristService.slideshow();
    }
    //网站信息
    @ResponseBody
    @RequestMapping("/copyright")
    public copyrightinfo copyright(){
        return touristService.copyright();
    }

}
