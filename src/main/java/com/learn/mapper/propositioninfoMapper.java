package com.learn.mapper;

import com.learn.bean.subvideoinfo;
import com.learn.dao.propositioninfo1;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface propositioninfoMapper {

    //获取最新全部论题
    @Select("select *from propositioninfo ORDER BY publishtime DESC")
     List<propositioninfo1> getpropositioninfo1();

    //添加论题
    @Insert("INSERT INTO propositioninfo(account, author, propositionname, propositioncontent,publishtime) VALUES(#{account}, #{author}, #{propositionname}, #{propositioncontent},#{publishtime})")
    int addpropositioninfo(@Param("account")String account,@Param("author")String author,@Param("propositionname")String propositionname,@Param("propositioncontent")String propositioncontent,@Param("publishtime")Date publishtime);

    //根据id查询论题
    @Select("select * from propositioninfo where id=#{id}")
    propositioninfo1 getpropropositioninfoid(@Param("id")String id);

    //关键词查找
    @Select("select * from propositioninfo where propositionname like concat('%',#{ForAntistop},'%') ")
    List<propositioninfo1> getForAntistop(@Param("ForAntistop") String ForAntistop);

    //我的论题
    @Select("select * from propositioninfo where account=#{account} ORDER BY publishtime DESC")
    List<propositioninfo1> getmyForum(@Param("account")String account);

    //删除论题
    @Delete("DELETE FROM propositioninfo WHERE id = #{id}")
    int delForum(@Param("id") String id);

    //获取分组排序全部论题
    @Select("select *from propositioninfo group by account,id DESC")
    List<propositioninfo1> getflpropositioninfo1();
}
