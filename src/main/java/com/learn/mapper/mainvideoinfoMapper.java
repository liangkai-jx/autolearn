package com.learn.mapper;

import com.learn.bean.mainvideoInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface mainvideoinfoMapper {
    //按id查找
    @Select("select * from mainvideoInfo where id=#{id}")
    mainvideoInfo getmainvideoInfoid(@Param("id") String id);

    //全部课程
    @Select("SELECT * FROM mainvideoinfo ")
    List<mainvideoInfo> getmainvideoinfo();

    //全部分类全部课程
    @Select("select * from mainvideoinfo where classid=#{classid}")
    List<mainvideoInfo> allCourses(@Param("classid") String classid);

    //查找相关的个数
    @Select("select count(*) from mainvideoinfo where mainvideoname like concat('%',#{antistop},'%') ")
    int Ucount(@Param("antistop") String antistop);

    //迷糊查询
    @Select("select * from mainvideoinfo where mainvideoname like concat('%',#{antistop},'%') ")
    List<mainvideoInfo> Useek(@Param("antistop") String antistop);

    //关注加1
    @Update("UPDATE mainvideoinfo SET gzNum=gzNum+1 WHERE id=#{id}")
    int addgz(@Param("id") int id);
    //关注减1
    @Update("UPDATE mainvideoinfo SET gzNum=gzNum-1 WHERE id=#{id}")
    int delgz(@Param("id") int id);

    //首页热门课程
    @Select("select * from mainvideoinfo ORDER BY gzNum DESC limit 0,9")
    List<mainvideoInfo> rmmainvideoInfo();

    //添加主视频
    @Insert("insert into mainvideoinfo(classid,mainvideoname,coveraddress,videointroduction) VALUES(#{classid},#{mainvideoname},#{coveraddress},#{videointroduction})")
    int addMainVideo(mainvideoInfo mainvideoInfo);

    //删除主视频
    @Delete("delete from mainvideoinfo where id=#{id}")
    int delmainVideo(@Param("id") String id);

    //按id修改
    @Update("update mainvideoinfo set mainvideoname=#{mainvideoname},videointroduction=#{videointroduction} where id=#{id}")
    int updatemainvideo(mainvideoInfo mainvideoInfo);
}
