package com.learn.mapper;

import com.learn.dao.commentinfo1;
import com.learn.dao.propositioninfo1;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface commentinfoMapper {

    //根据论题propositionid查询评论
    @Select("select *from commentinfo where propositionid=#{propositionid} ORDER BY commenttime DESC")
    List<commentinfo1> getcommentpid(@Param("propositionid")String propositionid);

    //添加评论
    @Insert("INSERT INTO commentinfo(propositionid, nickname, comment, commenttime) VALUES(#{propositionid}, #{nickname}, #{comment}, #{commenttime})")
    int addcomment(@Param("propositionid")String propositionid, @Param("nickname")String nickname, @Param("comment")String comment,@Param("commenttime") Date commenttime);

    //删除评论
    @Delete("DELETE FROM commentinfo WHERE propositionid = #{propositionid}")
    int delcomment(@Param("propositionid")String propositionid);

    //获取分组排序全部评论
    @Select("select *from commentinfo group by nickname,id DESC")
    List<commentinfo1> getflcommentpid();

    //删除评论id
    @Delete("DELETE FROM commentinfo WHERE id = #{id}")
    int delcommentid(@Param("id")String id);
}
