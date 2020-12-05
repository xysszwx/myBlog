package com.zwx.blog.web;


import com.github.pagehelper.PageInfo;
import com.zwx.blog.NotFoundException;
import com.zwx.blog.pojo.Blog;
import com.zwx.blog.pojo.vo.BlogQuery;
import com.zwx.blog.pojo.vo.SelectPage;
import com.zwx.blog.service.BlogService;
import com.zwx.blog.service.TagService;
import com.zwx.blog.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;

    @Resource
    private TagService tagService;




    @GetMapping("/")
    public String index(BlogQuery blogQuery , @RequestParam(defaultValue = "1") int page, Model model){
        //int i= 1/0;
//        String blog = null;
//        if(blog==null){
//            throw new NotFoundException("博客不存在");
//        }
//        System.out.println(id+"."+name);
        //默认4条一页
        blogQuery.setPageSize(6);
        //默认在第一页 可以传数据
        blogQuery.setPageNum(page);
        model.addAttribute("page",blogService.listBlog(blogQuery));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("types1",typeService.listType());
        model.addAttribute("tags" ,tagService.listTagTop(7));
        model.addAttribute("recommendBlogs",blogService.selectRecommend(10));

        return "index";
    }

    @RequestMapping("/search")
    public String search(BlogQuery blogQuery , @RequestParam(defaultValue = "1") int page,@RequestParam String query, Model model){

        blogQuery.setPageSize(6);
        blogQuery.setPageNum(page);
        model.addAttribute("page",blogService.listBlog(query, blogQuery));
        model.addAttribute("types1",typeService.listType());
        model.addAttribute("query",query);
        return "/search";
    }

    @GetMapping("/blog/{id}")
    public String blog(){
        System.out.println("-----方法执行-----");
        return "blog";
    }

//    @GetMapping("/login")
//    public String login(){
//        System.out.println("-----方法执行-----");
//        return "admin/index";
//    }



}