package com.learn.mapper;

import com.learn.bean.classinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface classinfoMapper {

    //查询所有分类
    @Select("select * from classinfo")
    List<classinfo> getclassinfo();

    //添加类别
    @Insert("insert into classinfo(classname) VALUES(#{classname})")
    int addclass(@Param("classname") String classname);

    //删除类别
    @Delete("delete from classinfo where id=#{id}")
    int delclass(@Param("id") String id);
}
