package com.learn.service.impl;

import com.learn.bean.copyrightinfo;
import com.learn.bean.mainvideoInfo;
import com.learn.bean.slideshowinfo;
import com.learn.bean.userInfo;
import com.learn.dao.userregister;
import com.learn.mapper.touristMapper;
import com.learn.service.touristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/25 20:16
 **/
@Service
public class touristServiceImpl implements touristService {
    @Autowired
    touristMapper touristMapper;

    @Override
    public int register(userregister userregister) {
        return touristMapper.register(userregister);
    }

    @Override
    public int Tcount(String antistop) {
        return touristMapper.Tcount(antistop);
    }

    @Override
    public List<mainvideoInfo> Tseek(String antistop) {
        return touristMapper.Tseek(antistop);
    }

    @Override
    public List<mainvideoInfo> rmmainvideoInfo() {
        return touristMapper.rmmainvideoInfo();
    }

    @Override
    public List<slideshowinfo> slideshow() {
        return touristMapper.slideshow();
    }

    @Override
    public copyrightinfo copyright() {
        return touristMapper.copyright();
    }

    @Override
    public userInfo regaccount(String account) {
        return touristMapper.regaccount(account);
    }

    @Override
    public userInfo regmailbox(String mailbox) {
        return touristMapper.regmailbox(mailbox);
    }
}
