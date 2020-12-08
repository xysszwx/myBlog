package com.zwx.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwx.blog.NotFoundException;
import com.zwx.blog.dao.BlogMapper;
import com.zwx.blog.dao.BlogTagsMapper;
import com.zwx.blog.dao.TagMapper;
import com.zwx.blog.pojo.Blog;
import com.zwx.blog.pojo.BlogExample;
import com.zwx.blog.pojo.BlogTags;
import com.zwx.blog.pojo.vo.BlogQuery;
import com.zwx.blog.pojo.vo.SelectPage;
import com.zwx.blog.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService{

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private BlogTagsMapper blogTagsMapper;

    @Resource
    private TagMapper tagMapper;

    @Override
    public Blog getBlog(Long id) {

        return blogMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public Blog getAndTran(Long id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);
        if(blog==null){
            throw new NotFoundException("该博客不存在");
        }
        Blog b1 = new Blog();
        BeanUtils.copyProperties(blog,b1);
        String content = b1.getContent();
        String s = MarkdownUtils.markdownToHtmlExtensions(content);
        b1.setContent(s);

        blogMapper.updateViews(id);
        return b1;
    }

    @Override
    public PageInfo<Blog> listBlog(BlogQuery blogQuery) {
        PageHelper.startPage(blogQuery.getPageNum(),blogQuery.getPageSize());
        List<Blog> blogs = blogMapper.selectByParams(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> listBlog(String query, BlogQuery blogQuery) {
        PageHelper.startPage(blogQuery.getPageNum(),blogQuery.getPageSize());
        List<Blog> blogs = blogMapper.selectByText(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> listBlog(SelectPage selectPage) {
        PageHelper.startPage(selectPage.getPageNum(),selectPage.getPageSize());
        List<Blog> blogs = blogMapper.selectByParams(selectPage);
        PageInfo<Blog> pageInfo = new PageInfo(blogs);
        return pageInfo;
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        int i = blogMapper.insertReKey(blog);
        connectionBlogTag(blog.getId(),blog.getTagIds());
        return i;

    }

    private void connectionBlogTag(Long bId, String tagIds) {
        Integer i = blogTagsMapper.countBlogTag(bId);
        if(i!=null){
            blogTagsMapper.deleteByBlogId(bId);
        }
        if(tagIds != null){
            List<BlogTags> list = new ArrayList<>();
            for(String id : tagIds.split(",")){
                BlogTags blogTags = new BlogTags();
                blogTags.setBlogsId(bId);
                blogTags.setTagsId(Long.parseLong(id));
                list.add(blogTags);
            }
            blogTagsMapper.insertBatch(list);
        }

    }





    @Transactional
    @Override
    public int updateBlog(Long id, Blog blog) {
        Blog blog1 = blogMapper.selectByPrimaryKey(id);
        blog.setUpdateTime(new Date());
        blog.setCreateTime(blog1.getCreateTime());
        blog.setViews(blog1.getViews());
        if(blog1==null){
            throw new NotFoundException("用户不存在");
        }
        connectionBlogTag(id,blog.getTagIds());
        return blogMapper.updateByPrimaryKey(blog);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        TreeMap<String,List<Blog>> map = new TreeMap<>(Comparator.reverseOrder());
        List<String> years = blogMapper.queryYears();
        System.out.println(years.toString());
        for(String year : years){
            map.put(year,blogMapper.queryBList(year));
        }

        return map;
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogTagsMapper.deleteByBlogId(id);
        blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Blog> selectRecommend(Integer size) {
        SelectPage selectPage = new SelectPage();
        selectPage.setPageSize(size);
        return blogMapper.selectRecommend(selectPage);
    }

    public List<Blog> queryBlogList(Long id){
        BlogExample blogExample = new BlogExample();
        BlogExample.Criteria criteria = blogExample.createCriteria();
        criteria.andTypeIdEqualTo(id);
        List<Blog> blogs = blogMapper.selectByExample(blogExample);
        return blogs;
    }

    @Override
    public Long countBlog() {
        Long count = blogMapper.countBlog();
        return count;
    }

    @Override
    public PageInfo<Blog> listByTag(SelectPage selectPage) {
        PageHelper.startPage(selectPage.getPageNum(),selectPage.getPageSize());
        List<Blog> blogs = blogMapper.selectByTId(selectPage.getId());
        for(Blog blog : blogs){
            blog.setTagss(tagMapper.selectByBlogId(blog.getId()));
        }
        PageInfo<Blog> pageInfo = new PageInfo(blogs);
        return pageInfo;
    }

    public List<Blog> queryBlogs(Long id){
        return blogMapper.selectByTId(id);
    }
}
