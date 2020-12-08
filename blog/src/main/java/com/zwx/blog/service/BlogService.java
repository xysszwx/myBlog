package com.zwx.blog.service;

import com.github.pagehelper.PageInfo;
import com.zwx.blog.pojo.Blog;
import com.zwx.blog.pojo.vo.BlogQuery;
import com.zwx.blog.pojo.vo.SelectPage;

import java.util.List;
import java.util.Map;


public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndTran(Long id);

    PageInfo<Blog> listBlog(BlogQuery blogQuery);

    PageInfo<Blog> listBlog(String query,BlogQuery blogQuery);

    PageInfo<Blog> listBlog(SelectPage selectPage);

    int saveBlog(Blog blog);

    int updateBlog(Long id ,Blog blog);

    Map<String,List<Blog>> archiveBlog();

    void deleteBlog(Long id);

    List<Blog> selectRecommend(Integer size);

    List<Blog> queryBlogList(Long id);

    Long countBlog();

    PageInfo<Blog> listByTag(SelectPage selectPage);
    List<Blog> queryBlogs(Long id);
}
