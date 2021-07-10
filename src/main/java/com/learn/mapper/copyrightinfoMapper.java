package com.learn.mapper;

import com.learn.bean.copyrightinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface copyrightinfoMapper {
    //查找网站信息
    @Select("select *from copyrightinfo")
    copyrightinfo copyright();

    //网站信息修改
    @Update("update copyrightinfo set copyright=#{copyright},zsInfo=#{zsInfo} where id=#{id}")
    int updatecopy(copyrightinfo copyrightinfo);
}
