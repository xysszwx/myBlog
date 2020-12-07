package com.zwx.blog.dao;

import com.zwx.blog.pojo.Blog;
import com.zwx.blog.pojo.BlogExample;
import java.util.List;

import com.zwx.blog.pojo.vo.SelectPage;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper {
    int countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    Long selectMax();

    List<Blog> selectByParams(SelectPage selectPage);

    List<Blog> selectRecommend(SelectPage selectPage);

    List<Blog> selectByText(String query);

    int insertReKey(Blog blog);

    int updateViews(Long id);

    List<Blog> selectByTId(Long id);

}