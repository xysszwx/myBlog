package com.zwx.blog.dao;

import com.zwx.blog.pojo.BlogTags;
import com.zwx.blog.pojo.BlogTagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogTagsMapper {
    int countByExample(BlogTagsExample example);

    int deleteByExample(BlogTagsExample example);

    int insert(BlogTags record);

    int insertSelective(BlogTags record);

    List<BlogTags> selectByExample(BlogTagsExample example);

    int updateByExampleSelective(@Param("record") BlogTags record, @Param("example") BlogTagsExample example);

    int updateByExample(@Param("record") BlogTags record, @Param("example") BlogTagsExample example);

    int insertBatch(List<BlogTags> list);

    int deleteByBlogId(Long id);

    Integer countBlogTag(Long id);
}