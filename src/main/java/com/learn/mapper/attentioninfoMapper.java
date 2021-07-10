package com.learn.mapper;

import com.learn.bean.attentioninfo;
import com.learn.bean.mainvideoInfo;
import com.learn.dao.mainvideoInfo1;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface attentioninfoMapper {
    //按用户账号查找关注课程
    @Select("select *from mainvideoinfo left JOIN attentioninfo ON  mainvideoinfo.id=attentioninfo.mainvideoid where attentioninfo.account=#{account}")
    List<mainvideoInfo1> getmyvideo(@Param("account") String account);

    //查找是否关注
    @Select("select *from attentioninfo where account=#{account} and mainvideoid=#{mainvideoid}")
    attentioninfo getattentioninfo(@Param("account")String account,@Param("mainvideoid") String mainvideoid);

    //关注课程
    @Insert("insert into attentioninfo(account,mainvideoid) VALUES(#{account},#{mainvideoid})")
    int addattentioninfo(attentioninfo attentioninfo);

    //取消关注
    @Delete("delete from attentioninfo where mainvideoid=#{mainvideoid}")
    int delattentioninfo(@Param("mainvideoid") int mainvideoid);
}
