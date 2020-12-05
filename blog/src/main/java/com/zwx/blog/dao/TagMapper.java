package com.zwx.blog.dao;

import com.zwx.blog.pojo.Tag;
import com.zwx.blog.pojo.TagExample;
import java.util.List;

import com.zwx.blog.pojo.vo.SelectPage;
import org.apache.ibatis.annotations.Param;

public interface TagMapper {
    int countByExample(TagExample example);

    int deleteByExample(TagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    List<Tag> selectByExample(TagExample example);

    List<Tag> selectAll();

    List<Long> selectId(Long id);

    Tag selectByPrimaryKey(Long id);

    //查询全部 自定义
    List<Tag> selectByParam(SelectPage selectPage);

    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> selectTagTop(SelectPage SelectPage);

    List<Tag> selectByBlogId(Long id);
}