package com.zwx.blog.service;

import com.github.pagehelper.PageInfo;
import com.zwx.blog.pojo.Tag;
import com.zwx.blog.pojo.vo.SelectPage;

import java.util.List;


public interface TagService {
    int saveTag(Tag tag);

    Tag getTagByid(Long id);

    List<Tag> getTagByName(String name);

    List<Tag> listTag();

    List<Tag> listByIds(String ids);

    List<Long> listTagId(Long id);

    List<Tag> listTagTop(Integer size);

    PageInfo listTag(SelectPage selectPage);

    int  updateTag(Long id, Tag tag);

    void deleteTag(Long id);

    List<Tag> queryByBlogId(Long id);
}
