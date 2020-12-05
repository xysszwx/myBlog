package com.zwx.blog.service;

import com.github.pagehelper.PageInfo;
import com.zwx.blog.pojo.Blog;
import com.zwx.blog.pojo.vo.BlogQuery;
import com.zwx.blog.pojo.vo.SelectPage;

import java.util.List;


public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndTran(Long id);

    PageInfo<Blog> listBlog(BlogQuery blogQuery);
    PageInfo<Blog> listBlog(String query,BlogQuery blogQuery);

    PageInfo<Blog> listBlog(SelectPage selectPage);

    int saveBlog(Blog blog);

    int updateBlog(Long id ,Blog blog);



    void deleteBlog(Long id);

    List<Blog> selectRecommend(Integer size);
}
