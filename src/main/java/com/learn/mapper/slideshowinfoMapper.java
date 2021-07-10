package com.learn.mapper;

import com.learn.bean.slideshowinfo;
import com.learn.dao.slideshowinfo1;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface slideshowinfoMapper {

    //查找可用轮播
    @Select("select *from slideshowinfo where slideshowreveal=1")
    List<slideshowinfo> slideshow();

    //所有轮播
    @Select("select *from slideshowinfo")
    List<slideshowinfo1> slideshowall();

    //显示轮播
    @Update("update slideshowinfo set slideshowreveal=1 where id=#{id}")
    int slshow(@Param("id") String id);

    //不显示轮播
    @Update("update slideshowinfo set slideshowreveal=2 where id=#{id}")
    int slsnoshow(@Param("id") String id);

    //添加轮播
    @Insert("INSERT INTO slideshowinfo(slideshowname,slideshowaddress) VALUES(#{slideshowname},#{slideshowaddress})")
    int addslideshow(slideshowinfo slideshowinfo);

    //删除轮播
    @Delete("delete from slideshowinfo where id=#{id}")
    int delslideshow(@Param("id") String id);

}
