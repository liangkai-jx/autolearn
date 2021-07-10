package com.learn.mapper;

import com.learn.bean.mainvideoInfo;
import com.learn.bean.userInfo;
import com.learn.dao.userInfo1;
import com.learn.dao.userregister;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
public interface userInfoMapper {


    //用户登录
    @Select("select * from userinfo where account=#{account} and password=#{password} and jurisdiction='2'")
    userInfo userlogin(@Param("account") String account,@Param("password") String password);

    //管理员登录
    @Select("select * from userinfo where account=#{account} and password=#{password} and jurisdiction='1'")
    userInfo adminlogin(@Param("account") String account,@Param("password") String password);

    //修改个人信息
    @Update("UPDATE userinfo SET sex = #{sex},age=#{age},nickname=#{nickname},mailbox=#{mailbox} WHERE account = #{account}")
    int upUser(userInfo userInfo);
    //按手机号查找
    @Select("select *from userinfo where account=#{account}")
    userInfo getaccountUser(@Param("account") String account);
    //查找密码
    @Select("select password from userinfo where account=#{account}")
    String getpass(@Param("account") String account);

    //修改密码
    @Update("UPDATE userinfo SET password = #{password1} WHERE account = #{account}")
    int uppass(userregister userregister);


    //获取用户数据
    @Select("select *from userinfo where jurisdiction=2")
    public List<userInfo1> userdate();

    //冻结
    @Update("update userinfo set state=2 where account=#{account}")
    int donjie(@Param("account") String account);

    //解冻
    @Update("update userinfo set state=1 where account=#{account}")
    int jiedon(@Param("account")String account);
}
