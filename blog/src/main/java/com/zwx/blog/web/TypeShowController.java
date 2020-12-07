package com.zwx.blog.web;

import com.github.pagehelper.PageInfo;
import com.zwx.blog.pojo.Blog;
import com.zwx.blog.pojo.Type;
import com.zwx.blog.pojo.vo.BlogQuery;
import com.zwx.blog.service.BlogService;
import com.zwx.blog.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TypeShowController {

    @Resource
    private TypeService typeService;

    @Resource
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id , Model model, BlogQuery blogQuery , @RequestParam(defaultValue = "1") int page){
        blogQuery.setPageSize(2);
        //默认在第一页 可以传数据
        blogQuery.setPageNum(page);
        List<Type> types = typeService.listTypeTop(1000);
        for(Type type : types){
            type.setBlogList(blogService.queryBlogList(type.getId()));
        }
        if(id == -1){
            id = types.get(0).getId();
        }
        blogQuery.setTypeId(id);
        PageInfo<Blog> pageInfo = blogService.listBlog(blogQuery);
        model.addAttribute("types",types);
        model.addAttribute("types1",typeService.listType());
        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
