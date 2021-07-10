package com.learn.mapper;

import com.learn.bean.mainvideoInfo;
import com.learn.bean.subvideoinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface subvideoinfoMapper {

    //按主视频查找
    @Select("select * from subvideoinfo where videoid=#{videoid}")
    List<subvideoinfo> getsubvideoid(@Param("videoid") String videoid);

    //分类查找所有视频
    @Select("select * from subvideoinfo group by mainvideoname,id")
    List<subvideoinfo> getflsubvideoid();

    //添加子视频
    @Insert("insert into subvideoinfo(videoid,videoaddress,subvideoname,mainvideoname) VALUES(#{videoid},#{videoaddress},#{subvideoname},#{mainvideoname})")
    int addsubvideo(subvideoinfo subvideoinfo);

    //删除子视频
    @Delete("delete from subvideoinfo where id=#{id}")
    int delsubvideo(@Param("id") String id);

    //按id查找
    @Select("select * from subvideoinfo where id=#{id}")
    subvideoinfo getsubid(@Param("id") int id);

    //按id修改
    @Update("update subvideoinfo set subvideoname=#{subvideoname} where id=#{id}")
    int updatesubid(subvideoinfo subvideoinfo);

    //按videoid修改
    @Update("update subvideoinfo set mainvideoname=#{mainvideoname} where videoid=#{videoid}")
    int updatesubvideoid(@Param("mainvideoname") String mainvideoname,@Param("videoid") int videoid);

}
